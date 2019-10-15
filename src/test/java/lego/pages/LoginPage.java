package lego.pages;

import org.openqa.selenium.WebElement;

import common.WebSupport;
import io.appium.java_client.AppiumDriver;
import lego.selectors.HomePageSelectors;
import lego.selectors.LoginSelectors;

public class LoginPage {
	WebSupport webSupport;
	AppiumDriver<WebElement> _driver;
	
	public LoginPage(AppiumDriver<WebElement> driver){
		this._driver = driver;
		webSupport = new WebSupport(this._driver);
	}
	
	public void login(String username, String password){
		webSupport.sendKeyByXpath(LoginSelectors.username_txt, username);
		webSupport.sendKeyByXpath(LoginSelectors.password_txt, password);
		webSupport.clickByXpath(LoginSelectors.logIn_btn);
	}
}
