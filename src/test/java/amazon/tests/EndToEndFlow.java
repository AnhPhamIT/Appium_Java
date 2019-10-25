package amazon.tests;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.annotations.Test;
import amazon.pages.FilterPage;
import amazon.pages.HomePage;
import amazon.pages.ProductDetailPage;
import amazon.pages.ProductListPage;
import amazon.pages.SearchResultPage;
import amazon.pages.SignInPage;
import amazon.pages.TempMailPage;
import core.Log;
import core.TestSuite;

public class EndToEndFlow extends TestSuite {

	@Test(enabled = true)
	public void EndToEnd() throws MalformedURLException, InterruptedException {
		Log.info("STEP 1: Navigate to URL https://www.amazon.com/");
		driver.get("https://www.amazon.com/");
		HomePage homePage = new HomePage(driver);
		SearchResultPage searchResultPage = new SearchResultPage(driver);
		FilterPage filterPage = new FilterPage(driver);
		ProductDetailPage productPage = new ProductDetailPage(driver);
		SignInPage signInPage = new SignInPage(driver);
		ProductListPage productList = new ProductListPage(driver);
		// homePage.navigateToSignInPage();
		// signInPage.signIn("tefoto@mail-card.net", "123456");
		// Thread.sleep(45000);
		// homePage.scrollToSignOut();
		// Thread.sleep(5000);
		// assertTrue(homePage.getSignOut().isDisplayed(), "Failed to login");
		// Thread.sleep(5000);
		Log.info("STEP 2: Select the first item in Deals recommend for you section");
		homePage.navigateToSection("Deals recommended for you");
		homePage.selectProductByIndex("Deals recommended for you", 2);
		if(!productPage.isProductNameVisible()){
			System.out.println("Select the 1st product in Product List");
			productList.selectProductInProductList(1);
		}
		String productName = productPage.getProductName();
		System.out.println("------------------------------" + productName);
		homePage.navigateToLogo();
		Log.info("STEP 3: search product item");
		homePage.searchByProductName(productName);
		if (searchResultPage.isNoResult()) {
			Log.info("STEP 3.1: If no result, select other result then sort by Avg. Customer Review");
			searchResultPage.selectOtherResult();
			searchResultPage.navigateToFilter();
			filterPage.sortBy("Avg. Customer Review");
		} else if (searchResultPage.isAllResult()) {
			Log.info("STEP 3.2: If any result, select all result then sort by Avg. Customer Review");
			searchResultPage.selectAllResult();
			searchResultPage.navigateToFilter();
			filterPage.sortBy("Avg. Customer Review");
		}
		Log.info("STEP 4: Select the first product then add to list");
		searchResultPage.selectProductByIndex(1);
		if (productPage.isAddToList()) {
			Log.info("STEP 4.1: Select the first product then add to list");
			productPage.selectAddToList();
		} else {
			Log.info("STEP 4.2: Select the first product then add to cart if no Add to List");
			productPage.selectAddToCart();
		}

		Thread.sleep(10000);
	}

	@Test(enabled = false)
	public void swicthToNewTab() throws InterruptedException {
		String baseUrl = "http://discuss.appium.io/";
		driver.get(baseUrl);
		Thread.sleep(5000);
		//webSupport.openNewTab();
		driver.findElement(By.xpath("//body")).sendKeys(Keys.CONTROL +"t");
//		 ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
//		 System.out.println("----------------------"+ tabs.size());
//		 driver.findElement(By.xpath("//body")).sendKeys(Keys.CONTROL +"t");
//		 //webSupport.openNewTab();
//		
//		 tabs = new ArrayList<String> (driver.getWindowHandles());
//		 System.out.println("----------------------"+ tabs.size());
//		 driver.switchTo().window(tabs.get(1)); //switches to new tab
//		 Thread.sleep(10000);
//		 driver.get("https://www.google.com");
		// way 2:
		String subWindowHandler = null;

		Set<String> handles = driver.getWindowHandles();
		Iterator<String> iterator = handles.iterator();
		while (iterator.hasNext()) {
			subWindowHandler = iterator.next();
			System.out.println(subWindowHandler);
		}
		System.out.println("---------------" + subWindowHandler);
		driver.switchTo().window(subWindowHandler);
		driver.get("https://www.news.google.com");

		Thread.sleep(10000);
	}

	@Test(enabled = false)
	public void tempMail() throws InterruptedException {
		driver.get("https://temp-mail.org");

		// kologile
		Thread.sleep(5000);
		TempMailPage tempMailPage = new TempMailPage(driver);
		String emailAddess = tempMailPage.getEmailAddress();

		driver.get("https://www.amazon.com/");
		HomePage homePage = new HomePage(driver);
		SearchResultPage searchResultPage = new SearchResultPage(driver);
		FilterPage filterPage = new FilterPage(driver);
		ProductDetailPage productPage = new ProductDetailPage(driver);
		SignInPage signInPage = new SignInPage(driver);
		homePage.navigateToSignInPage();
		signInPage.signIn(emailAddess, "123456");

	}

	@Test(enabled = false)
	public void login() throws InterruptedException {
		driver.get("https://www.amazon.com/");
		HomePage homePage = new HomePage(driver);
		SignInPage signInPage = new SignInPage(driver);
		homePage.navigateToSignInPage();
		signInPage.signIn("tefoto@mail-card.net", "123456");
	}
	
	@Test(enabled = false)
	public void login1() throws InterruptedException {
		driver.get("https://www.amazon.com/");
		HomePage homePage = new HomePage(driver);
		SignInPage signInPage = new SignInPage(driver);
		homePage.navigateToSignInPage();
		signInPage.signIn("tefoto@mail-card.net", "123456");
	}
}
