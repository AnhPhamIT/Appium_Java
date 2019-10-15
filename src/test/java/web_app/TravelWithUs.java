package web_app;

import static org.testng.Assert.assertTrue;

import java.net.URL;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException; //fluent wait
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import core.ExcelUtil;
import io.appium.java_client.AppiumDriver;

public class TravelWithUs {

	AppiumDriver<WebElement> driver;
	String tripTitle="";
	String tripPlace="";
	String memberNo = "";
	ArrayList<HashMap<String, String>> dataArr;
	String menu_xpath="//button[translate(normalize-space(text()), ' ', '')='Menu']";
	String signIn_xpath ="//a[translate(normalize-space(text()), ' ', '')='SignIn']";

	@BeforeMethod
	public void setUp() throws Exception{
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setBrowserName(BrowserType.CHROME);
		capabilities.setPlatform(Platform.ANDROID);
		//capabilities.setCapability(MobileCapabilityType.UDID, "emulator-5554");
		capabilities.setCapability("deviceName", "SamSung Galaxy fdkfjdkl");
		URL SeleniumGridURL = new URL("http://localhost:4723/wd/hub");
		driver = new AppiumDriver<WebElement>(SeleniumGridURL, capabilities);
		
	}
	@Test(enabled=false)
	public void loginWithValidAccount() throws Exception{
		dataArr = ExcelUtil.readExcel("D:\\Framework\\Appium\\Demo1\\test-data\\Trips.xlsx", "CreateTrip", "TC01");
		String email=dataArr.get(0).get("Email");
		String password = dataArr.get(0).get("Password");
		WebDriverWait wait = new WebDriverWait(driver, 30000);
		driver.get("http://travelwithus.asia/");

//		JavascriptExecutor executor = (JavascriptExecutor) driver;
//		executor.executeScript("arguments[0].click();", driver.findElement(By.xpath(menu_xpath)));
		Thread.sleep(3000);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(menu_xpath)));
		driver.findElement(By.xpath(menu_xpath)).click();
		Thread.sleep(2000);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(signIn_xpath)));
		driver.findElement(By.xpath(signIn_xpath)).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.id("email")));
		driver.findElement(By.id("email")).sendKeys(email);

		driver.findElement(By.id("pwd")).sendKeys(password);
		driver.findElement(By.xpath("//button[text()='Login']")).click();
		
		Thread.sleep(3000);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(menu_xpath)));
		driver.findElement(By.xpath(menu_xpath)).click();
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//a[translate(normalize-space(.), ' ', '')='Welcome"+email+"']"))));
		Thread.sleep(2000);
		assertTrue(driver.findElement(By.xpath("//a[translate(normalize-space(.), ' ', '')='Welcome"+email+"']")).isDisplayed(), "***Error***: Login unsuccessfully");
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(menu_xpath)));
		driver.findElement(By.xpath(menu_xpath)).click();
	}
	@Test(enabled=true)
	public void a_createTrip() throws Exception{
		loginWithValidAccount();
		dataArr = ExcelUtil.readExcel("D:\\Framework\\Appium\\Demo1\\test-data\\Trips.xlsx", "CreateTrip", "TC01");
		tripTitle = dataArr.get(0).get("Title");
		tripPlace = dataArr.get(0).get("Place");
		memberNo = dataArr.get(0).get("Members");
		WebDriverWait wait = new WebDriverWait(driver, 30000);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(menu_xpath)));
		driver.findElement(By.xpath(menu_xpath)).click();
		WebElement createTrip= wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Create Trip']")));
		createTrip.click();
		wait.until(ExpectedConditions.elementToBeClickable(By.id("title"))).sendKeys(tripTitle);
		wait.until(ExpectedConditions.elementToBeClickable(By.id("place"))).sendKeys(tripPlace);		

		wait.until(ExpectedConditions.elementToBeClickable(By.id("start_date_val"))).click();

		driver.findElement(By.xpath("//div[contains(@class,'datetimepicker ')][1]/descendant::td[@class='day'][text()='15']")).click();

		wait.until(ExpectedConditions.elementToBeClickable(By.id("end_date_val"))).click();
		
		//wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(@class,'datetimepicker')][2]/descendant::td[@class='day'][text()='29']"))).click();
		driver.findElement(By.xpath("//div[contains(@class,'datetimepicker ')][2]/descendant::td[@class='day'][text()='29']")).click();
		WebElement member_dll= (WebElement)driver.findElement(By.id("members"));
		Select member = new Select(member_dll);
		member.selectByVisibleText(memberNo);
		driver.findElement(By.xpath("//button[text()='Submit'][contains(@class,'success')]")).click();		
		
		//exception: alert 
		wait.until(ExpectedConditions.alertIsPresent());
		Alert alertTrip =driver.switchTo().alert();
		System.out.println("-------------------------"+alertTrip.getText());
		assertTrue(alertTrip.getText().equals("A new trip has been created!"));
		alertTrip.accept();
		
		//exception: no element found
		Thread.sleep(3000);
		WebElement newAddedTrip= driver.findElement(By.xpath("//tr[td[1][text()='"+tripTitle+"'] and td[2][text()='"+tripPlace+"'] and td[5][text()='"+memberNo+"']]"));
		assertTrue(newAddedTrip.isDisplayed(), "New added trip has not been displayed in Trip table");
		Thread.sleep(10000);
	}
	@Test(enabled=true)
	public void b_editTrip() throws Exception{
		dataArr = ExcelUtil.readExcel("D:\\Framework\\Appium\\Demo1\\test-data\\Trips.xlsx", "EditTrip", "TC03");
		tripTitle = dataArr.get(0).get("Title");
		tripPlace = dataArr.get(0).get("Place");
		String newTripTitle=dataArr.get(0).get("EditTitle");
		String newTripPlace=dataArr.get(0).get("EditPlace");
		String newMemberNo = dataArr.get(0).get("EditMembers");
		loginWithValidAccount();
		Thread.sleep(5000);
		
		WebDriverWait wait = new WebDriverWait(driver, 30000);
		WebElement toDoList = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//tr[td[1][text()='"+tripTitle+"'] and td[2][text()='"+tripPlace+"']]/descendant::button[@title='Todo List']")));
		//exception: intercept with menu icon and intercept with Go Back Home icon
		//WebElement toDoList = driver.findElement(By.xpath("//tr[td[1][text()='"+tripTitle+"'] and td[2][text()='"+tripPlace+"']]/descendant::button[@title='Todo List']"));
		
		((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView({behavior: 'auto',block: 'center',inline: 'center'});", toDoList);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//tr[td[1][text()='"+tripTitle+"'] and td[2][text()='"+tripPlace+"']]/descendant::button[@title='Edit']"))).click();
		
		wait.until(ExpectedConditions.elementToBeClickable(By.id("title"))).clear();
		wait.until(ExpectedConditions.elementToBeClickable(By.id("title"))).sendKeys(newTripTitle);
		wait.until(ExpectedConditions.elementToBeClickable(By.id("place"))).clear();
		wait.until(ExpectedConditions.elementToBeClickable(By.id("place"))).sendKeys(newTripPlace);		

		wait.until(ExpectedConditions.elementToBeClickable(By.id("start_date_val"))).click();

		driver.findElement(By.xpath("//div[contains(@class,'datetimepicker ')][1]/descendant::td[@class='day'][text()='9']")).click();

		wait.until(ExpectedConditions.elementToBeClickable(By.id("end_date_val"))).click();
		
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(@class,'datetimepicker')][2]/descendant::td[@class='day'][text()='27']"))).click();
		WebElement member_dll= (WebElement)driver.findElement(By.id("members"));
		Select member = new Select(member_dll);
		member.selectByVisibleText(newMemberNo);
		driver.findElement(By.xpath("//button[text()='Submit'][contains(@class,'success')]")).click();		
				
		wait.until(ExpectedConditions.alertIsPresent());
		Alert alertTrip =driver.switchTo().alert();
		System.out.println("-------------------------"+alertTrip.getText());
		assertTrue(alertTrip.getText().equals("The trip has been updated!"));
		alertTrip.accept();
		
		Thread.sleep(3000);
		WebElement newAddedTrip= driver.findElement(By.xpath("//tr[td[1][text()='"+newTripTitle+"'] and td[2][text()='"+newTripPlace+"'] and td[5][text()='"+newMemberNo+"']]"));
		assertTrue(newAddedTrip.isDisplayed(), "Got error when editting trip");
		Thread.sleep(10000);
	}
	@Test(enabled=true)
	public void c_deleteTrip() throws Exception{
		dataArr = ExcelUtil.readExcel("D:\\Framework\\Appium\\Demo1\\test-data\\Trips.xlsx", "DeleteTrip", "TC02");
		String newTripTitle=dataArr.get(0).get("Title");
		String newTripPlace=dataArr.get(0).get("Place");
		String newMemberNo = dataArr.get(0).get("Members");
		loginWithValidAccount();

		final String toDoListXpath ="//tr[td[1][text()='"+newTripTitle+"'] and td[2][text()='"+newTripPlace+"']]/descendant::button[@title='Todo List']";
		
		//explicit wait keep finding element forever with xpath {"using":"xpath","value":"//tr[td[1][text()=''] and td[2][text()='Phan Thiet Edited']]/descendant::button[@title='Todo List']"}
//		WebDriverWait wait = new WebDriverWait(driver, 30000);
//		WebElement actionHeader = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//tr[td[1][text()='"+newTripTitle+"'] and td[2][text()='"+newTripPlace+"']]/descendant::button[@title='Todo List']")));
		
		// fluent wait work well - way 1
//		FluentWait<AppiumDriver<WebElement>>  wait = new FluentWait<AppiumDriver<WebElement>>(driver).withTimeout(Duration.ofSeconds(30)).pollingEvery(Duration.ofSeconds(1)).ignoring(NoSuchElementException.class);

//		WebElement actionHeader = wait.until(new Function<WebDriver, WebElement>() {
//			public WebElement apply(WebDriver driver) {
//				WebElement element = driver.findElement(By.xpath(toDoListXpath));
//				String getTextOnPage = element.getText();
//				if (getTextOnPage.equals("Software Testing Material - DEMO PAGE")) {
//					System.out.println(getTextOnPage);
//					return element;
//				} else {
//					System.out.println("FluentWait Failed");
//					return null;
//				}
//			}
//		});
		
//		way 2
		FluentWait<AppiumDriver<WebElement>> wait = new FluentWait<AppiumDriver<WebElement>>(driver).withTimeout(Duration.ofSeconds(60)).pollingEvery(Duration.ofSeconds(1)).ignoring(NoSuchElementException.class);
		WebElement actionHeader = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//tr[td[1][text()='"+newTripTitle+"'] and td[2][text()='"+newTripPlace+"']]/descendant::button[@title='Todo List']")));
	    
		((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView({behavior: 'auto',block: 'center',inline: 'center'});", actionHeader);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//tr[td[1][text()='"+newTripTitle+"'] and td[2][text()='"+newTripPlace+"']]/descendant::button[@title='Delete']"))).click();	

		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='OK']"))).click();

				
		wait.until(ExpectedConditions.alertIsPresent());
		Alert alertTrip =driver.switchTo().alert();
		System.out.println("-------------------------"+alertTrip.getText());
		assertTrue(alertTrip.getText().equals("The trip has been deleted!"));
		alertTrip.accept();
		
		Thread.sleep(3000);
		java.util.List<WebElement> newAddedTrip= driver.findElements(By.xpath("//tr[td[1][text()='"+newTripTitle+"'] and td[2][text()='"+newTripPlace+"'] and td[5][text()='"+newMemberNo+"']]"));
		if(newAddedTrip.size()<1)
			System.out.println("The trip has been deleted successfully");
		
		Thread.sleep(10000);
	}

	//ToDo testcase
	
	@AfterMethod
	public void tearDown() throws Exception{
		//driver.manage().deleteAllCookies();
		driver.close();

	}
}
