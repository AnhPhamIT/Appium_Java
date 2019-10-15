package core;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;

public class DriverFactory {

	public static AndroidDriver<WebElement> getAndroidDriver(String deviceID) throws MalformedURLException {
		AndroidDriver<WebElement> driver;
		DesiredCapabilities capabilities = new DesiredCapabilities();
		// capabilities.setCapability(AndroidMobileCapabilityType.APP_PACKAGE,
		// "");
		// capabilities.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY,
		// "");
		capabilities.setCapability(MobileCapabilityType.UDID, deviceID);
		capabilities.setCapability("deviceName", "SamSung Galaxy S7");
		capabilities.setBrowserName(BrowserType.CHROME);
		capabilities.setPlatform(Platform.ANDROID);
		capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 1200); //10 mins

		URL SeleniumURL = new URL("http://localhost:4723/wd/hub");
		driver = new AndroidDriver<WebElement>(SeleniumURL, capabilities);
		return driver;
	}
}
