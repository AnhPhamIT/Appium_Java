package lego.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import common.WebSupport;
import core.Log;
import io.appium.java_client.AppiumDriver;
import lego.selectors.CookiesSelectors;
import lego.selectors.HomePageSelectors;
import lego.selectors.ProductDetailsSelectors;
import lego.selectors.WishListSelectors;

public class HomePage {
	WebSupport webSupport;
	AppiumDriver<WebElement> _driver;
	CookiesPage cookiesPage;
	LoginPage loginPage;
	WishListPage wishListPage;
	BagPage bagPage;

	public HomePage(AppiumDriver<WebElement> driver) {
		this._driver = driver;
		webSupport = new WebSupport(this._driver);
		cookiesPage = new CookiesPage(this._driver);
		loginPage = new LoginPage(this._driver);
		wishListPage = new WishListPage(this._driver);
		bagPage = new BagPage(this._driver);
	}

	public void selectAccount() {
		webSupport.clickByXpath(HomePageSelectors.account_btn);

	}

	public void loginWithValidAccount(String email, String password) {

		if (webSupport.isElementVisible(CookiesSelectors.Ok_btn)) {
			cookiesPage.selectOKCookies();
			selectExplore();
			cookiesPage.selectAcceptCookies();
			selectAccount();
			selectSignIn();
			loginPage.login(email, password);
		} else {
			selectAccount();
			selectSignIn();
			loginPage.login(email, password);
			cookiesPage.selectOKCookies();
			selectExplore();
			cookiesPage.selectAcceptCookies();
		}
	}

	public void selectSignIn() {
		webSupport.clickByXpath(HomePageSelectors.signIn_btn);
	}

	public void selectExplore() {
		webSupport.clickByXpath(HomePageSelectors.explore_btn);
	}

	public void selectSection(String section) {
		webSupport.clickByXpath(HomePageSelectors.getSectionXpath(section));
	}

	public void selectProductByIndex(String section, int index) {
		webSupport.clickByXpath(HomePageSelectors.getProductBySectionXpath(section, index));
	}

	public String getWishListValue() {
		return webSupport.getElementText(HomePageSelectors.wishList_value);
	}

	public void selectWishListBar() throws InterruptedException {
		webSupport.moveToElement(HomePageSelectors.search_txt);
		// this._driver.navigate().refresh();
		// Thread.sleep(5000);
		webSupport.clickByXpath(HomePageSelectors.wishList_bar);
		if (!wishListPage.isWLVisible())
			webSupport.clickByXpath(HomePageSelectors.wishList_bar);
	}

	public void searchProduct(String productName) {
		if (webSupport.isElementVisible(CookiesSelectors.cancelAd)) {
			webSupport.clickByXpath(CookiesSelectors.cancelAd);
		}
		webSupport.sendKeyByXpath(HomePageSelectors.search_txt, productName);
		webSupport.sendSpecialKeyByXpath(HomePageSelectors.search_txt, Keys.ENTER);
	}

	public void selectBag() throws InterruptedException {
		try {
//			webSupport.moveToElement(HomePageSelectors.search_txt);
			webSupport.clickByXpath(HomePageSelectors.bag_bar);
			if (!bagPage.isMyBagVisible())
				Thread.sleep(2000);
				webSupport.clickByXpath(HomePageSelectors.bag_bar);
		} catch (Exception e) {
			// TODO: handle exception
			webSupport.clickByXpath(HomePageSelectors.bag_bar);
		}
		
	}

	public String getBagValue() {
		return webSupport.getElementText(HomePageSelectors.bag_value);
	}

	public void selectLogo() {
		webSupport.clickByXpath(HomePageSelectors.logo_img);
	}

	public void selectChangeRegion() {
		webSupport.moveToElement(HomePageSelectors.changeRegion_btn);
		webSupport.clickByXpath(HomePageSelectors.changeRegion_btn);
	}

	public void selectRegion(String region) {
		webSupport.clickByXpath(HomePageSelectors.getRegionXpath(region));
	}

	public void selectCountry(String country) {
		webSupport.clickByXpath(HomePageSelectors.getCountryXpath(country));
	}

	public void selectMenu() {
		webSupport.clickByXpath(HomePageSelectors.menu);
	}

	public void selectSubMenu(String subMenu) {
		if (webSupport.isElementVisible(CookiesSelectors.cancelAd)) {
			webSupport.clickByXpath(CookiesSelectors.cancelAd);
		}
		webSupport.clickByXpath(HomePageSelectors.getSubMenuXpath(subMenu));
	}
	
	public void selectLogOut(){
		webSupport.clickByXpath(HomePageSelectors.logOut_btn);
	}
}
