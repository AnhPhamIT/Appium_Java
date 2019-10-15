package core;

import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.WebElement;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import common.WebSupport;
import core.Log;
import io.appium.java_client.android.AndroidDriver;
public class TestSuite extends DriverFactory {
	public AndroidDriver<WebElement> driver;
	/**
	 * Setup method to set system property for log file name
	 */
	@BeforeSuite
	public void Setup(ITestContext context) {
		// loading log4j.xml file
		//Log.info("SUITE " + this.getClass().getName());
		DOMConfigurator.configure(".\\src\\test\\resources\\log4j.xml");
	}
	
	@BeforeMethod
	public void beforeMethod(Method method) throws MalformedURLException{
		Log.startTestCase(this.getClass().getName()+ ": " + method.getName());
		driver = DriverFactory.getAndroidDriver("ad0c1603a0cb1afc2a");
	}

	/**
	 * @param args
	 */
	@AfterMethod
	public void afterMethod(ITestResult result) {
		//Calendar calendar = Calendar.getInstance();
        //SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
        //String methodName = result.getMethod().getMethodName().toString();
        try {
        	int status = result.getStatus();
            switch (status) {
    		case 1:
    			Log.info("TEST PASSED: " + result.getMethod().getMethodName().toUpperCase());
    			break;
    		case 2:
    			Log.info("TEST FAILED: " + result.getMethod().getMethodName().toUpperCase());
    			String stackTrace = ExceptionUtils.getStackTrace(result.getThrowable());
            	Log.error(stackTrace);
    			break;
    		case 3:
    			Log.info("TEST SKIPPED: "+ result.getMethod().getMethodName().toUpperCase());
    			break;
    		}              
    		driver.quit();
		} catch (Exception e) {
			driver.quit();
		}
        
	}
}
