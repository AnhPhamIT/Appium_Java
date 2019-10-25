package others;

import static org.testng.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.ServerSocket;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;

public class AppiumServerJava {

	private static AppiumDriverLocalService service;
	private static AppiumServiceBuilder builder;
	private static DesiredCapabilities cap;

	public void startServer() {
		// Set Capabilities
		cap = new DesiredCapabilities();
		cap.setCapability("noReset", "false");

		// Build the Appium service
		builder = new AppiumServiceBuilder();
		builder.withIPAddress("127.0.0.1");
		builder.usingPort(4723);
		builder.withCapabilities(cap);
		builder.withArgument(GeneralServerFlag.SESSION_OVERRIDE);
		builder.withArgument(GeneralServerFlag.LOG_LEVEL, "error");

		// Start the server with the builder
		service = AppiumDriverLocalService.buildService(builder);
		service.start();
	}

	public void stopServer() {
		if (service != null) {
			service.stop();
		}
		System.out.println("Appium Server is stopped ");
	}

	public boolean checkIfServerIsRunnning(int port) {

		boolean isServerRunning = false;
		ServerSocket serverSocket;
		try {
			serverSocket = new ServerSocket(port);
			serverSocket.close();
		} catch (IOException e) {
			// If control comes here, then it means that the port is in use
			isServerRunning = true;
		} finally {
			serverSocket = null;
		}
		return isServerRunning;
	}

	public static void main(String[] args) throws InterruptedException, IOException {
		AppiumServerJava appiumServer = new AppiumServerJava();
		AppiumDriver<MobileElement> driver;

		int port = 4723;
		if (!appiumServer.checkIfServerIsRunnning(port)) {
			appiumServer.startServer();
			System.out.println("Appium Server is running on - " + port);
			DesiredCapabilities capabilities = new DesiredCapabilities();
			capabilities.setBrowserName(BrowserType.CHROME);
			capabilities.setPlatform(Platform.ANDROID);
			capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Galaxy S6");
			// capabilities.setCapability("deviceName", "Galaxy S6");
			URL SeleniumGridURL = new URL("http://localhost:4723/wd/hub");
			driver = new AppiumDriver<MobileElement>(SeleniumGridURL, capabilities);
			driver.get("https://google.com.vn");
			driver.findElement(By.name("q")).sendKeys("TMA solutions");
			;
			driver.findElement(By.name("q")).sendKeys(Keys.ENTER);
			Thread.sleep(5000);
			WebElement firstLink = driver.findElement(By.xpath("//div[@id='rso']/div[1]/descendant::a[1]"));
			firstLink.click();
			Thread.sleep(5000);
			driver.quit();
		} else {
			System.out.println("Appium Server is already running on Port - " + port);
		}
		appiumServer.stopServer();
	}

	public static void stopProcessID() throws IOException {
		Process process = Runtime.getRuntime().exec("netstat -aon | find 4723");
		InputStream ips = process.getInputStream();
		InputStreamReader isr = new InputStreamReader(ips);
		BufferedReader br = new BufferedReader(isr);
		BufferedReader output = new BufferedReader(new InputStreamReader(process.getInputStream()));
		String value = null;
		System.out.println(output);
		while ((value = br.readLine()) != null) {
		    System.out.println(value);

		}

	}
}