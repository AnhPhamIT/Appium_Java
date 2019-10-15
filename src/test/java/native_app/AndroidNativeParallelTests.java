package native_app;

import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;

public class AndroidNativeParallelTests {
	AppiumDriver<MobileElement> driver;
	
	@BeforeTest(alwaysRun = true)
	@Parameters({"platform", "udid", "systemPort"})
	public void setup(String platform, String udid, String systemPort) throws Exception {
	    URL url = new URL("http://localhost:4723/wd/hub");
	    String[] platformInfo = platform.split(" ");
	    System.out.println("------------"+ platformInfo[0] + " " + platformInfo[1]);
	    DesiredCapabilities capabilities = new DesiredCapabilities();
	    capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "uiautomator2"); //app still run if 
	    capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, platformInfo[0]);
	    capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, platformInfo[1]);
	    capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Android Emulator");
	    capabilities.setCapability(MobileCapabilityType.UDID, udid);
	    capabilities.setCapability(AndroidMobileCapabilityType.SYSTEM_PORT, systemPort);
	    //capabilities.setCapability(MobileCapabilityType.APP, "/Users/henrrich/Documents/work/jsta/appium/demo-apps/demo.apk");
		capabilities.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "com.android.vending");
		capabilities.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, "com.android.vending.AssetBrowserActivity");
	    capabilities.setCapability(MobileCapabilityType.ORIENTATION, "PORTRAIT");
	    capabilities.setCapability(MobileCapabilityType.NO_RESET, false); // clear the app data like cache, fullReset uninstalls the app
	    driver = new AndroidDriver<MobileElement>(url, capabilities);
	    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	@Test
	public void test1() throws InterruptedException{
		System.out.println("---------------------sessionID1: "+ driver.getSessionId());
		System.out.println("----------------------Starting PlayStore-------------------------------");
		Thread.sleep(10000);
	}
	
	@Test
	public void test2() throws InterruptedException{
		System.out.println("---------------------sessionID2: "+ driver.getSessionId());
		System.out.println("----------------------Starting PlayStore-------------------------------");
		Thread.sleep(10000);
	}
	
	@AfterTest(alwaysRun = true)
	public void teardown() throws Exception {
	    if (driver != null) {
	        driver.quit();
	    }
	}
}
