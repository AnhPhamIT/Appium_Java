package lego.pages;

import org.openqa.selenium.WebElement;

import common.WebSupport;
import io.appium.java_client.AppiumDriver;
import lego.selectors.CookiesSelectors;

public class CookiesPage {
	AppiumDriver<WebElement> _driver;
	WebSupport webSupport;
	
	public CookiesPage(AppiumDriver<WebElement> driver){
		this._driver = driver;
		webSupport = new WebSupport(this._driver);
	}
	
	public void selectAcceptCookies(){
		webSupport.clickByXpath(CookiesSelectors.acceptCookies_btn);
	}
	
	public void selectOKCookies(){
		webSupport.clickByXpath(CookiesSelectors.Ok_btn);
	}
	
	public void selectCancelAd(){
		webSupport.clickByXpath(CookiesSelectors.cancelAd);
	}
}
