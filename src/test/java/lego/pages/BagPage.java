package lego.pages;

import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;

import common.WebSupport;
import core.Log;
import io.appium.java_client.AppiumDriver;
import lego.selectors.BagSelectors;
import lego.selectors.ProductDetailsSelectors;

public class BagPage {
	AppiumDriver<WebElement> _driver;
	WebSupport webSupport;
	public BagPage(AppiumDriver<WebElement> driver){
		this._driver = driver;
		webSupport = new WebSupport(this._driver);
	}
	
	public void printAllProductDetails(int bagSize){
		for (int i = 1; i <= bagSize; i++) {
			String productTitle = webSupport.getElementText(BagSelectors.getBagProductTitleByIndex(i));
			String productPrice = webSupport.getElementText(BagSelectors.getBagPriceByIndex(i));
			Log.info("--- Product Title --- "+productTitle + " has price " + productPrice);
			
		}
	}
	
	public String printTotalPrice(){
		return webSupport.getElementText(BagSelectors.orderTotal);
	}
	
	public boolean isMyBagVisible(){
		return webSupport.isElementVisible(BagSelectors.myBag_lb);
	}
	
	public void inputLimit(int value){
		try {
			webSupport.moveToElement(ProductDetailsSelectors.available_txt);
			webSupport.sendKeyByXpath(ProductDetailsSelectors.available_txt, Integer.toString(value));
			//webSupport.clickByXpath(BagSelectors.getBagProductTitleByIndex(1));
		} catch (Exception e) {
			webSupport.sendKeyByXpath(ProductDetailsSelectors.available_txt, Integer.toString(value));
			//webSupport.clickByXpath(BagSelectors.getBagProductTitleByIndex(1));
		}
		
	}
}
