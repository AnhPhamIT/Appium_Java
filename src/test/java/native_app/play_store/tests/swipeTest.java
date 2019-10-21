package native_app.play_store.tests;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import native_app.play_store.common.ActionSupport;

public class swipeTest {
	private static AndroidDriver<MobileElement> driver;
	public static void main(String[] args) throws MalformedURLException, InterruptedException {

		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("deviceName", "Samsung Galaxy S7fdfdfd");
		capabilities.setCapability("platformVersion", "8.0.0"); // need to be
																// exactly as S7
																// version for
																// AndroidDriver
		capabilities.setCapability("platformName", "Android"); // require for
																// AppiumDriver
		// capabilities.setCapability("UiAutomator2", true);
		capabilities.setCapability("clearSystemFiles", true);
		capabilities.setCapability("appPackage", "com.android.vending");
		capabilities.setCapability("appActivity", "com.android.vending.AssetBrowserActivity");

		driver = new AndroidDriver<MobileElement>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		Thread.sleep(5000);
		ActionSupport actionSupport = new ActionSupport(driver);
		System.out.println("swipe down");
		actionSupport.swipe(0.4, 0.9, 0.4, 0.2);
		
		Thread.sleep(5000);
		System.out.println("swipe up");
		actionSupport.swipe(0.4, 0.2, 0.4, 0.9);
		Thread.sleep(10000);
		driver.quit();
	}
}
