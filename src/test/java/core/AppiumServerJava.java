package core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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

//	private static AppiumDriverLocalService service;
//	private static AppiumServiceBuilder builder;
//	private static DesiredCapabilities cap;

	public static void startServer(int port) {
		AppiumDriverLocalService service;
		AppiumServiceBuilder builder;
		DesiredCapabilities cap;
		// Set Capabilities
		cap = new DesiredCapabilities();
		cap.setCapability("noReset", "false");

		// Build the Appium service
		builder = new AppiumServiceBuilder();
		builder.withIPAddress("127.0.0.1");
		builder.usingPort(port);
		builder.withCapabilities(cap);
		builder.withArgument(GeneralServerFlag.SESSION_OVERRIDE);
		builder.withArgument(GeneralServerFlag.LOG_LEVEL, "error");

		// Start the server with the builder
		service = AppiumDriverLocalService.buildService(builder);
		service.start();
	}

	public static void stopServer(AppiumDriverLocalService service) {
		if (service != null) {
			service.stop();
		}
		System.out.println("Appium Server is stopped ");
	}

	public static boolean checkIfServerIsRunnning(int port) {

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
		AppiumDriverLocalService service =null;
		if (!appiumServer.checkIfServerIsRunnning(port)) {
			appiumServer.startServer(port);
			System.out.println("Appium Server is started on Port - " + port);
		} else {
			System.out.println("Appium Server is already running on Port - " + port);
		}
		appiumServer.stopServer(service);
	}
}