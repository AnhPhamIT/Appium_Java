package amazon.pages;

import org.openqa.selenium.WebElement;

import amazon.selectors.ProductDetailsSelectors;
import amazon.selectors.ProductListSelectors;
import common.WebSupport;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;

public class ProductListPage {
	
	AppiumDriver<WebElement> _driver;
	WebSupport webSupport;
	
	public ProductListPage(AppiumDriver<WebElement> driver){
		this._driver = driver;
		webSupport = new WebSupport(this._driver);
	}
	
	public void selectProductInProductList(int index){
		webSupport.clickByXpath(ProductListSelectors.getProductByIndex(index));
	}
}
