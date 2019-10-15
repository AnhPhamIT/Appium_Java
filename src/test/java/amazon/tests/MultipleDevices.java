package amazon.tests;

import java.net.MalformedURLException;

import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import core.DriverFactory;
import core.TestSuite;
import io.appium.java_client.android.AndroidDriver;

public class MultipleDevices extends TestSuite {
	AndroidDriver<WebElement> galaxyS7;
	AndroidDriver<WebElement> simulator;

	@BeforeMethod
	public void setUp() throws MalformedURLException {
		galaxyS7 = DriverFactory.getAndroidDriver("ad0c1603a0cb1afc2a");
		//simulator = DriverFactory.getAndroidDriver("emulator-5554");
	}

	@Test(enabled = true)
	public void MultipleDevicesTest() throws MalformedURLException, InterruptedException {
		galaxyS7.get("https://www.amazon.com/");
		//simulator.get("https://www.amazon.com/");
		Thread.sleep(5000);
	}

	@AfterMethod
	public void tearDown() {
		 galaxyS7.quit();
		//simulator.quit();
	}
}
