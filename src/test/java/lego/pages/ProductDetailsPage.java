package lego.pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import lego.selectors.ProductDetailsSelectors;
import common.WebSupport;
import io.appium.java_client.AppiumDriver;

public class ProductDetailsPage {
	AppiumDriver<WebElement> _driver;
	WebSupport webSupport;

	public ProductDetailsPage(AppiumDriver<WebElement> driver) {
		this._driver = driver;
		webSupport = new WebSupport(this._driver);
	}
	
	public String getProductName(){
		return webSupport.getElementText(ProductDetailsSelectors.productName_lb);
	}
	
	public void selectAddToWishList(){
		if(webSupport.isElementVisible(ProductDetailsSelectors.addToWishList_btn))
			webSupport.clickByXpath(ProductDetailsSelectors.addToWishList_btn);
	}
	
	public void inputAvailable(int value){
		webSupport.sendKeyByXpath(ProductDetailsSelectors.available_txt, Integer.toString(value));
		webSupport.clickByXpath(ProductDetailsSelectors.productName_lb);
	}
}
