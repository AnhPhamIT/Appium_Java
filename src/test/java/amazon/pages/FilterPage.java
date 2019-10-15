package amazon.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import common.WebSupport;
import io.appium.java_client.AppiumDriver;
import amazon.selectors.FilterSelectors;
import amazon.selectors.SearchResultSelectors;

public class FilterPage {

	AppiumDriver<WebElement> _driver;
	WebSupport webSupport;

	public FilterPage(AppiumDriver<WebElement> driver) {
		this._driver = driver;
		webSupport = new WebSupport(this._driver);
	}

	public void sortBy(String sortText) throws InterruptedException {
		webSupport.clickByXpath(FilterSelectors.sortBy_lnk);
		Thread.sleep(5000);
		webSupport.clickByXpath(FilterSelectors.getSortOptionXpath(sortText));
	}
}
