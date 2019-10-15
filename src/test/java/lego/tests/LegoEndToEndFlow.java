package lego.tests;

import static org.testng.Assert.assertTrue;

import java.net.MalformedURLException;

import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import core.DriverFactory;
import core.Log;
import core.TestSuite;
import io.appium.java_client.AppiumDriver;
import lego.pages.BagPage;
import lego.pages.CookiesPage;
import lego.pages.HomePage;
import lego.pages.LoginPage;
import lego.pages.ProductDetailsPage;
import lego.pages.SearchPage;
import lego.pages.WishListPage;

public class LegoEndToEndFlow extends TestSuite {

	//AppiumDriver<WebElement> driver;
	
	@Test
	public void LegoEndToEndFlow_script() throws MalformedURLException, InterruptedException{
		//driver = DriverFactory.getAndroidDriver("ad0c1603a0cb1afc2a");
		driver.get("https://www.lego.com/en-us");
		
		HomePage homePage = new HomePage(driver);
//		LoginPage loginPage = new LoginPage(driver);
//		CookiesPage cookiesPage = new CookiesPage(driver);
		ProductDetailsPage productDetails = new ProductDetailsPage(driver);
		WishListPage wishListPage = new WishListPage(driver);
		SearchPage searchPage = new SearchPage(driver);
		BagPage bagPage = new BagPage(driver);
		
		Log.info("STEP 1. Login to Lego website");
		homePage.loginWithValidAccount("vertevaltu@desoz.com", "qwertyui890");
		
		Log.info("STEP 2. scroll down to Our top-selling exclusives");
		homePage.selectSection("Our top-selling exclusives");
		Log.info("STEP 2.1. select Product Index 2 at Our top-selling exclusives");
		homePage.selectProductByIndex("Our top-selling exclusives", 2);
		String productName = productDetails.getProductName();
		Log.info("---Product Name---"+productName);
		Log.info("STEP 2.2. Add current Product to WIshList");
		productDetails.selectAddToWishList();
		Thread.sleep(4000);
		int expectWishListValue = 1;
		Log.info("---Wish List value--- "+homePage.getWishListValue());
		assertTrue(homePage.getWishListValue().equals(Integer.toString(expectWishListValue)), "Actual wishlist value " + homePage.getWishListValue() + " different with expected " + expectWishListValue );
		
		Log.info("STEP 2.3 Verify item has been correctly put into cart ");
		homePage.selectWishListBar();
		assertTrue(wishListPage.getProductTitleByIndex(1).equalsIgnoreCase(productName), "Actual product name " + wishListPage.getProductTitleByIndex(1) +" different with expected " + productName);
		Log.info("STEP 2.4 Click \"Delete\" button to remove the item out of the Wish List");
		wishListPage.deleteWLByIndex(1);
		expectWishListValue = expectWishListValue-1;
		
		Log.info("STEP 2.5 Search the deleted item from search box");
		homePage.searchProduct(productName);
		searchPage.selectFilter();
		searchPage.sortBy("Name");
		searchPage.sortBy("Relevance");
		searchPage.filterByProductType("Sets");
		searchPage.filterByAge("12+");
		searchPage.filterByPrice("$100");
		searchPage.selectDone();
		
		Log.info("STEP 2.6 Click on for 1st item");
		searchPage.selectProductItemByIndex(1);
		Log.info("STEP 2.7 Add to WishList with Limit = 2");
		productDetails.inputAvailable(2);
		productDetails.selectAddToWishList();
		Thread.sleep(2000);
		expectWishListValue = expectWishListValue + 1;
		assertTrue(homePage.getWishListValue().equals(Integer.toString(expectWishListValue)), "Actual wishlist value " + homePage.getWishListValue() + " different with expected " + expectWishListValue );
		
		Log.info("STEP 2.7 On Wish List, click \"Move to Bag\"");
		wishListPage.selectAddToBag();
		int expectedBag = 2;
		Thread.sleep(2000);
		Log.info("Verify item has been correctly added to MY BAG (on the top right corner)");
		assertTrue(homePage.getBagValue().equals(Integer.toString(expectedBag)), "Actual bag is " + homePage.getBagValue() + " different with expected " + expectedBag );
		
		homePage.selectBag();
		Log.info("Update Bag Quantity to 1");
		bagPage.inputLimit(1);
		
		Log.info("STEP 2.8 Changing shipping country");
		homePage.selectLogo();
		homePage.selectChangeRegion();
		homePage.selectRegion("EUROPE");
		homePage.selectCountry("United Kingdom / English");
		expectedBag = 0;
		assertTrue(homePage.getBagValue().equals(Integer.toString(expectedBag)));
		
		Log.info("STEP 2.9 Select \"Animals\" in sub menu");
		homePage.selectMenu();
		homePage.selectSubMenu("interests");
		homePage.selectSubMenu("animals");
		
		Log.info("STEP 2.10 Click \"Add to Wish List\" for first 6 items");
		searchPage.selectListProduct(6);
		expectWishListValue = 6;
		Thread.sleep(2000);
		Log.info("---Wish List value--- "+homePage.getWishListValue());
		assertTrue(homePage.getWishListValue().equals(Integer.toString(expectWishListValue)), "Actual wishlist value " + homePage.getWishListValue() + " different with expected " + expectWishListValue );
		
		Log.info("STEP 2.11 Move all from Wishlist items to Bag by click on \"Move to Bag\" button");
		homePage.selectWishListBar();
		wishListPage.printAllProductName(expectWishListValue);
		wishListPage.moveListProductToBag(expectWishListValue);
		Thread.sleep(4000);
		
		expectedBag =6;
		assertTrue(homePage.getBagValue().equals(Integer.toString(expectedBag)), "Actual bag is " + homePage.getBagValue() + " different with expected " + expectedBag);
		
		Log.info("STEP 3.1 Print out details price and item");
		homePage.selectBag();
		bagPage.printAllProductDetails(expectedBag);
		
		Log.info("STEP 3.2 Print out Order total " + bagPage.printTotalPrice());
		
		Log.info("STEP 4 Log out");
		homePage.selectAccount();
		homePage.selectLogOut();
		Thread.sleep(10000);
	}
}
