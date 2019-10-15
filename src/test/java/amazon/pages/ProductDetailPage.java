package amazon.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import common.WebSupport;
import io.appium.java_client.AppiumDriver;
import amazon.selectors.ProductDetailsSelectors;

public class ProductDetailPage {
	AppiumDriver<WebElement> _driver;
	WebSupport webSupport;

	public ProductDetailPage(AppiumDriver<WebElement> driver) {
		this._driver = driver;
		webSupport = new WebSupport(this._driver);
	}

	public String getProductName() {
		return webSupport.getElementText(ProductDetailsSelectors.productName);
	}

	public void selectAddToList() {
		webSupport.clickByXpath(ProductDetailsSelectors.addToList);
	}
	
	public void selectAddToCart(){
		webSupport.clickByXpath(ProductDetailsSelectors.addToCart);
	}
	
	public boolean isAddToList(){
		return webSupport.isElementVisible(ProductDetailsSelectors.addToList);
	}
}
