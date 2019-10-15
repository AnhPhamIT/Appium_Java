package amazon.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import common.WebSupport;
import io.appium.java_client.AppiumDriver;
import amazon.selectors.SearchResultSelectors;

public class SearchResultPage {
	private AppiumDriver<WebElement> _driver;
	WebSupport webSupport;

	public SearchResultPage(AppiumDriver<WebElement> driver) {
		this._driver = driver;
		webSupport = new WebSupport(this._driver);
	}

	public boolean isNoResult() {
		return webSupport.isElementVisible(SearchResultSelectors.noResult);
	}

	public boolean isAllResult() {
		return webSupport.isElementVisible(SearchResultSelectors.seeAllResult);
	}

	public void selectOtherResult() {
		webSupport.clickByXpath(SearchResultSelectors.seeOtherWithNoResult);
	}

	public void selectAllResult() {
		webSupport.moveToElement(SearchResultSelectors.seeAllResult);
		webSupport.clickByXpath(SearchResultSelectors.seeAllResult);
	}

	public void navigateToFilter() {
		webSupport.clickByXpath(SearchResultSelectors.filter_lnk);
	}

	public void selectProductByIndex(int index) {
		webSupport.clickByXpath(SearchResultSelectors.getProductByIndex(index));
	}
}
