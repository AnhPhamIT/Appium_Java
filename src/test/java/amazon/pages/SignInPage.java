package amazon.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import common.WebSupport;
import io.appium.java_client.AppiumDriver;
import amazon.selectors.SignInSelectors;

public class SignInPage {

	public WebSupport webSupport;

	public SignInPage(AppiumDriver<WebElement> driver) {
		webSupport = new WebSupport(driver);
	}

	public void signIn(String email, String password) throws InterruptedException {
		// Thread.sleep(5000); // remove this sleep cause No such element for
		// email xpath
		webSupport.sendKeyByXpath(SignInSelectors.email_txt, email);
		webSupport.sendKeyByXpath(SignInSelectors.password_txt, password);
		webSupport.clickByXpath(SignInSelectors.submit_btn);

	}
}
