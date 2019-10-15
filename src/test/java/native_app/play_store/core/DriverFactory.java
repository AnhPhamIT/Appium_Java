package native_app.play_store.core;

import java.net.MalformedURLException;
import java.net.URL;

import org.apache.tools.ant.types.selectors.SelectorContainer;
import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.DesiredCapabilities;
import io.appium.java_client.MobileElement;
import io.appium.java_client.MobileSelector;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;

public class DriverFactory {

	public static AndroidDriver<MobileElement> getAppiumDriver(String appPackage, String appActivity) throws MalformedURLException{
		AndroidDriver<MobileElement> driver;
		DesiredCapabilities capabilities = new DesiredCapabilities();
		//capabilities.setPlatform(Platform.ANDROID);
		capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Samsung Galaxy S6fgf");
		capabilities.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, appPackage);
		capabilities.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, appActivity);
//		capabilities.setCapability("automationName", "UiAutomator2");
		capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 1200);
		URL seleniumGridURL =new URL("http://localhost:4723/wd/hub");
		driver = new AndroidDriver<MobileElement>(seleniumGridURL, capabilities);
		return driver;
	}
	

}
