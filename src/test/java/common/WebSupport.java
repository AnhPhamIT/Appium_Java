package common;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.functions.ExpectedCondition;

public class WebSupport {
	private AppiumDriver<WebElement> _driver;
//	private WebDriverWait wait;
	FluentWait<AppiumDriver<WebElement>> wait;
	public WebSupport(AppiumDriver<WebElement> driver){
		this._driver = driver;
		this.wait = new FluentWait<AppiumDriver<WebElement>>(this._driver).withTimeout(Duration.ofSeconds(20)).pollingEvery(Duration.ofSeconds(1)).ignoring(NoSuchElementException.class);
		//FluentWait<AppiumDriver<WebElement>> wait = new FluentWait<AppiumDriver<WebElement>>(driver).withTimeout(Duration.ofSeconds(60)).pollingEvery(Duration.ofSeconds(1)).ignoring(NoSuchElementException.class);
	}
	
	public void clickByXpath(String xpath){
		WebElement ele = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath))).click();
	}
	
	public void sendKeyByXpath(String xpath, String value){
//		System.out.println("1111111111111111111111111111");
//		WebElement ele =wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
//		System.out.println("222222222222222222222222222222");
//		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
//		ele.sendKeys(value);
		WebElement ele = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
		ele.sendKeys(value);
	}
	
	public void sendSpecialKeyByXpath(String xpath, Keys key){
		WebElement elm = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
		elm.sendKeys(key);
	}
	
//	public void scrollToElement(String visibleText){
//		WebElement element = this._driver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\""+visibleText+"\").instance(0))"));
//		element.click();
//	}
	
	public void moveToElement(String xpath){
		WebElement ele = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
		int getTopLeftY = ((ele.getSize().getHeight()/2) - ele.getSize().getHeight());
		int getTopLeftX =  (ele.getSize().getWidth()/2) - ele.getSize().getWidth();
		Actions action = new Actions(this._driver);
		action.moveToElement(ele);
		action.perform();
	}
	
	public String getElementText(String xpath){
		WebElement ele = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
		return ele.getText();
	}
	
	public String getElementAttribute(String xpath, String attribute){
		WebElement ele = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
		return ele.getAttribute(attribute);
	}
	public void openNewTab() {
		  ((JavascriptExecutor) this._driver).executeScript("window.open('about:blank','_blank');");
	}
	
	public boolean isElementVisible(String xpath){
		try {
			 wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
			 return true;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
	}
	
	
	
}
