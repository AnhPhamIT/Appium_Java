package amazon.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import core.Log;
import io.appium.java_client.AppiumDriver;
import common.WebSupport;
import amazon.selectors.HomeSelectors;

public class HomePage {
	public WebSupport webSupport;
	public AppiumDriver<WebElement> _driver;
	

	public HomePage(AppiumDriver<WebElement> driver) {
		this._driver = driver;
		webSupport = new WebSupport(this._driver);
	}

	public void navigateToSignInPage() {
		// HomeSelectors.signIn_lnk;
		Log.info("HomePage: Navigating to Home page");
		webSupport.clickByXpath(HomeSelectors.signIn_lnk);

	}

	public WebElement getSignOut() {
		Log.info("HomePage: Check signOut button");
		return this._driver.findElement(By.xpath(HomeSelectors.signOut_lnk));
	}

	public void navigateToSection(String section) {
		// webSupport.scrollToElement(section);
		Log.info("HomePage: navigate to section " + section);
		webSupport.moveToElement(HomeSelectors.getSectionXpath(section));
	}

	public void scrollToSignOut() {
		webSupport.moveToElement(HomeSelectors.signOut_lnk);
	}

	public void selectProductByIndex(String section, int index) {
		webSupport.clickByXpath(HomeSelectors.getProductByIndexAndSection(section, index));
	}

	public void navigateToLogo() {
		// webSupport.moveToElement(HomeSelectors.logo);
		webSupport.clickByXpath(HomeSelectors.logo);
	}

	public void searchByProductName(String productName) {
		webSupport.sendKeyByXpath(HomeSelectors.search_txt, productName);
		webSupport.clickByXpath(HomeSelectors.search_btn);
	}
}
