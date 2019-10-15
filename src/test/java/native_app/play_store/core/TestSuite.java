package native_app.play_store.core;

import java.net.MalformedURLException;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import core.Log;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

public class TestSuite {
	
	public AndroidDriver<MobileElement> driver;
	
	@BeforeSuite
	public void beforeSuite(){
		
	}
	
	@BeforeMethod
	public void beforeTest() throws MalformedURLException{
		Log.info("---Start PlayStore---");
		driver = DriverFactory.getAppiumDriver("com.android.vending", "com.android.vending.AssetBrowserActivity");
	}
	
	@AfterMethod
	public void afterTest(){
		driver.quit();
	}
	
	
}
