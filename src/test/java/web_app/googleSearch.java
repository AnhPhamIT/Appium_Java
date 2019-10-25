package web_app;

import static org.testng.Assert.assertTrue;

import java.io.IOException;
import java.net.URL;
import java.util.Set;

import org.aspectj.lang.annotation.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import common.WebSupport;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;

public class googleSearch {

	AndroidDriver<MobileElement> driver;

	@BeforeTest
	public void setUp() throws Exception {
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setBrowserName(BrowserType.CHROME);
		capabilities.setPlatform(Platform.ANDROID);
		capabilities.setCapability(AndroidMobileCapabilityType.AUTO_GRANT_PERMISSIONS, true);
		capabilities.setCapability("autoAcceptAlerts", true); // only for iOS
		capabilities.setCapability("locationServicesEnabled", false);
		capabilities.setCapability("autoDismissAlerts", true);
		capabilities.setCapability("gpsEnabled", true);

		// capabilities.setCapability("noReset", true);
		capabilities.setCapability("clearSystemFiles", true);
		capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Galaxy S6");
		URL SeleniumGridURL = new URL("http://localhost:4723/wd/hub");
		this.driver = new AndroidDriver<MobileElement>(SeleniumGridURL, capabilities);
	}

	@Test
	public void googleSearch() throws Exception {
		driver.get("https://google.com.vn");

		driver.findElement(By.name("q")).sendKeys("TMA solutions");

		driver.findElement(By.name("q")).sendKeys(Keys.ENTER);
		Thread.sleep(5000);
		
		//way 1 to close Location permission popup 
		String webContext = driver.getContext();
		Set<String> contexts = driver.getContextHandles();
		for (String context : contexts) {
			if (context.contains("NATIVE_APP")) {
				System.out.println("switching to " + context);
				driver.context(context);
				break;
			}
		}
		MobileElement ele = driver
				.findElement(By.xpath("//android.widget.Button[@resource-id='com.android.chrome:id/negative_button']"));
		ele.click();

		for (String context : contexts) {

			if (context.contains("CHROMIUM")) {
				System.out.println("switching to " + context);
				driver.context(context);
				break;
			}
		}

//		way 2 to grant Location Permission so the popup will never display again
//		String packageName = ((AndroidDriver) driver).getCurrentPackage();
//		String grantCameraPermission = "adb shell pm grant " + packageName + " android.permission.CAMERA";
//		String grantLocationPermission = "adb shell pm grant " + packageName
//				+ " android.permission.ACCESS_FINE_LOCATION";
//		String revokeCameraPermission = "adb shell pm grant " + packageName + " android.permission.CAMERA";
//		String revokeLocationPermission = "adb shell pm grant " + packageName
//				+ " android.permission.ACCESS_FINE_LOCATION";
//		try {
//			Runtime.getRuntime().exec(grantCameraPermission);
//			Runtime.getRuntime().exec(revokeLocationPermission);
//
//		} catch (IOException e) {
//			e.printStackTrace();
//		}

		WebElement firstLink = driver.findElement(By.xpath("//div[@class='srg'][1]/div[1]/descendant::a[1]"));
		firstLink.click();
		Thread.sleep(5000);
		String expected = "https://www.tmasolutions.com/";
		String actual = driver.getCurrentUrl();
		System.out.println("First link clicked " + actual);
		assertTrue(actual.contains(expected), "Actual: " + actual + " is different from " + expected);

		Actions actions = new Actions(driver);
		actions.moveToElement(driver.findElement(By.xpath("//a[text()='Case Studies']")));
		driver.findElement(By.xpath("//a[text()='Case Studies']")).click();
		Thread.sleep(5000);
		// ((AndroidDriver<MobileElement>) driver).pressKey(new
		// KeyEvent(AndroidKey.HOME));
		String cmd = "adb shell input keyevent 4";
		Runtime.getRuntime().exec(cmd);
		Thread.sleep(5000);

	}

	@AfterTest
	public void tearDown() throws Exception {
		Thread.sleep(10000);
		driver.quit();
	}
}
