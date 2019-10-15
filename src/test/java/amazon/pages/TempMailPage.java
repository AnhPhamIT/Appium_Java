package amazon.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import common.WebSupport;
import io.appium.java_client.AppiumDriver;
import amazon.selectors.TempMailSelectors;

public class TempMailPage {
	public AppiumDriver<WebElement> _driver;
	WebSupport webSupport;

	public TempMailPage(AppiumDriver<WebElement> driver) {
		this._driver = driver;
		webSupport = new WebSupport(this._driver);
	}

	public String getEmailAddress() {
		return webSupport.getElementAttribute(TempMailSelectors.emailAddress, "value");
	}

	public void selectFirstValiditionEmail() {
		webSupport.clickByXpath(TempMailSelectors.firstEmail);
	}

	public String getAmazonValidationCode() {
		selectFirstValiditionEmail();
		return webSupport.getElementText(TempMailSelectors.otpCode);
	}
}
