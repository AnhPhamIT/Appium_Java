package lego.pages;

import org.openqa.selenium.WebElement;

import common.WebSupport;
import core.Log;
import io.appium.java_client.AppiumDriver;
import lego.selectors.WishListSelectors;

public class WishListPage {

	AppiumDriver<WebElement> _driver;
	WebSupport webSupport;

	public WishListPage(AppiumDriver<WebElement> driver) {
		this._driver = driver;
		webSupport = new WebSupport(this._driver);
	}
	
	public void deleteWLByIndex(int index){
		webSupport.clickByXpath(WishListSelectors.getWLDeleteByIndex(index));
	}
	
	public String getProductTitleByIndex(int index){
		return webSupport.getElementText(WishListSelectors.getWLProductTitleByIndex(index));
	}
	
	public void selectAddToBag(){
		webSupport.clickByXpath(WishListSelectors.addToBag);
	}
	
	public void printAllProductName(int wishListValue){
		for (int i = 1; i <= wishListValue; i++) {
			String productName = webSupport.getElementText(WishListSelectors.getWLProductTitleByIndex(i));
			Log.info("---Product Item " + i + "--- " + productName);
		}
	}
	
	public void moveListProductToBag(int noOfProduct) throws InterruptedException{
		for (int i = 1; i <= noOfProduct; i++) {
			Thread.sleep(1000);
			webSupport.clickByXpath(WishListSelectors.getWLAddToBagByIndex(i));
		}
	}
	
	public boolean isWLVisible(){
		return webSupport.isElementVisible(WishListSelectors.wishListTitle_lb);
	}
}
