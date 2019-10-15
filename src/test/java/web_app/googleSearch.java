package web_app;

import static org.testng.Assert.assertTrue;

import java.net.URL;

import org.aspectj.lang.annotation.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;

public class googleSearch {
	
	AppiumDriver<MobileElement> driver;
	
	@BeforeTest
	public void setUp() throws Exception{
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setBrowserName(BrowserType.CHROME);
		capabilities.setPlatform(Platform.ANDROID);
		capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Galaxy S6");
		//capabilities.setCapability("deviceName", "Galaxy S6");
		URL SeleniumGridURL = new URL("http://localhost:4723/wd/hub");
		this.driver = new AppiumDriver<MobileElement>(SeleniumGridURL, capabilities);
	}
	
	@Test
	public void googleSearch() throws Exception{
		driver.get("https://google.com.vn");
		driver.findElement(By.name("q")).sendKeys("TMA solutions");;
		driver.findElement(By.name("q")).sendKeys(Keys.ENTER);
		Thread.sleep(5000);
		WebElement firstLink =driver.findElement(By.xpath("//div[@id='rso']/div[1]/descendant::a[1]"));
		firstLink.click();
		Thread.sleep(5000);
		String expected ="https://www.tmasolutions.com/";
		String actual = driver.getCurrentUrl();
		assertTrue(actual.equalsIgnoreCase(expected), "Actual: "+ actual + " is different from " + expected);
		
		driver.findElement(By.xpath("//a[text()='Case Studies']")).click();
		Thread.sleep(5000);
		//((AndroidDriver<MobileElement>) driver).pressKey(new KeyEvent(AndroidKey.HOME));
		String cmd = "adb shell input keyevent 4";
		Runtime.getRuntime().exec(cmd);

	}
	
	@AfterTest
	public void tearDown() throws Exception{
		Thread.sleep(10000);
		driver.quit();
	}
}
