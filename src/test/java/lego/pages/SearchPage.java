package lego.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import common.WebSupport;
import io.appium.java_client.AppiumDriver;
import lego.selectors.SearchPageSelectors;

public class SearchPage {
	AppiumDriver<WebElement> _driver;
	WebSupport webSupport;
	public SearchPage(AppiumDriver<WebElement> driver){
		this._driver = driver;
		webSupport = new WebSupport(this._driver);
	}
	
	public void selectFilter(){
		webSupport.clickByXpath(SearchPageSelectors.filter_btn);
	}
	
	public void sortBy(String option){
		Select drpSortBy = new Select(this._driver.findElement(By.xpath(SearchPageSelectors.sortBy)));
		drpSortBy.selectByVisibleText(option);
		//webSupport.sendKeyByXpath(SearchPageSelectors.sortBy, option);
	}
	
	public void filterByProductType(String option){
		webSupport.clickByXpath(SearchPageSelectors.getFilterXpathByCategory("Product Type", option));
	}
	
	public void filterByAge(String age){
		webSupport.clickByXpath(SearchPageSelectors.getFilterXpathByCategory("Age", age));
	}
	
	public void filterByPrice(String price){
		webSupport.clickByXpath(SearchPageSelectors.getFilterXpathByCategory("Price", price));
	}
	
	public void selectDone(){
		webSupport.clickByXpath(SearchPageSelectors.done_btn);
	}
	
	public void selectProductItemByIndex(int index){
		webSupport.clickByXpath(SearchPageSelectors.getProductItemByIndex(index));
	}
	
	public void selectListProduct(int numberProduct){
		for (int i = 1; i <= numberProduct; i++) {
			webSupport.clickByXpath(SearchPageSelectors.getAddToWishListByIndex(i));
		}
	}
}
