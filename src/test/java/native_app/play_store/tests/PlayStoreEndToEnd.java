package native_app.play_store.tests;

import static org.testng.Assert.assertTrue;

import java.time.Duration;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import core.Log;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import native_app.play_store.common.ActionSupport;

public class PlayStoreEndToEnd extends native_app.play_store.core.TestSuite{
	
	@Test
	public void PlayStoreEndToEndScript() throws InterruptedException{
		ActionSupport actionSupport = new ActionSupport(driver);
//		Log.info("STEP 1. Navigate to \"Books & reference\" section");
//		actionSupport.scrollToElementByID("com.android.vending:id/viewpager", "Previously installed apps");
//		MobileElement section= driver.findElementByXPath("//android.widget.TextView[@text='Previously installed apps']/../../../..");
//		System.out.println("parent--- "+section.getAttribute("resource-id"));
//		List<MobileElement> childs = section.findElementsById("com.android.vending:id/play_card");
//		System.out.println("child--- "+childs.get(0).getAttribute("content-desc"));
		
		Log.info("STEP 1. Navigate to \"Social networking\" section");
		actionSupport.scrollToElementByID("com.android.vending:id/viewpager", "Social networking");
		MobileElement section= driver.findElementByXPath("//android.widget.TextView[@text='Social networking']/../../../..");
		System.out.println("parent--- "+section.getAttribute("resource-id"));
		Thread.sleep(2000);
		
		Log.info("STEP 2. select the first item to see the details");
		List<MobileElement> childs = section.findElementsById("com.android.vending:id/play_card");
		String firstProductSummary = childs.get(0).getAttribute("content-desc");
		System.out.println("child--- "+ firstProductSummary);
		String productName =  firstProductSummary.split("Star rating:")[0];
		String productRating = firstProductSummary.split("Star rating:")[1];
		
		System.out.println("product title ------------ " + productName);
		
		childs.get(0).click();
		MobileElement productTitle_detail = driver.findElementById("com.android.vending:id/title_title");
		System.out.println("product title in Product details ------------ " + productTitle_detail.getText());
		assertTrue(productTitle_detail.getText().trim().equals((productName.split("App: ")[1]).trim()), "Product title mismatch");
		
		Thread.sleep(2000);
		MobileElement productRating_ele = driver.findElementById("com.android.vending:id/decide_badge_view");
		String productRating_details = productRating_ele.getAttribute("content-desc");
		productRating_details = StringUtils.substringBetween(productRating_details, "Average rating ", " stars");
		
		Log.info("STEP 3. print out product rating");
		System.out.println("product rating ------------ " + productRating.trim());
		System.out.println("Product rating details ---- " + productRating_details);
		assertTrue(productRating.trim().equals(productRating_details.trim()), "Product rating mismatch");
		
		Log.info("STEP 4. select \"Rating and reviews\" details");
		actionSupport.scrollToElementByID("com.android.vending:id/recycler_view", "Ratings and reviews");
		MobileElement ratingReviews= driver.findElementByXPath("//*[@text='Ratings and reviews']");
		ratingReviews.click();
		
		Thread.sleep(2000);
		MobileElement sort_ele = driver.findElementByXPath("//android.widget.TextView[@resource-id='com.android.vending:id/sort_label']");
		sort_ele.click();
		
		Thread.sleep(2000);
		MobileElement mostRecent_ele = driver.findElementByXPath("//android.widget.RadioButton[@text='Most recent']");
		mostRecent_ele.click();
		
		MobileElement latestVersion_ele = driver.findElementByXPath("//android.widget.CheckBox[@resource-id='com.android.vending:id/filter_by_version']");
		latestVersion_ele.click();
		
		MobileElement apply_ele = driver.findElementByXPath("//android.widget.TextView[@text='APPLY']");
		apply_ele.click();
		
		Thread.sleep(2000);
		
		Log.info("STEP 2.5.1 print the first positive comment, commenter and date");
		if(actionSupport.isElementVisible("//android.widget.Button[@text='5']"))
		{
			MobileElement fiveStars_ele = driver.findElementByXPath("//android.widget.Button[@text='5']");
			fiveStars_ele.click();
		}
		else {
			MobileElement positive_ele = driver.findElementByXPath("//android.widget.Button[@text='Positive']");
			positive_ele.click();
		}
		
		List<MobileElement> firstPositive_ele = driver.findElementsByXPath("//android.widget.LinearLayout[@resource-id='com.android.vending:id/review_item_container']");
		MobileElement firstAuthor_ele = firstPositive_ele.get(0).findElementById("com.android.vending:id/review_author");
		System.out.println("--- First positive Author ----" + firstAuthor_ele.getText());
		MobileElement firstRating_ele = firstPositive_ele.get(0).findElementById("com.android.vending:id/review_rating");
		System.out.println("--- First positive Rating ----" + firstRating_ele.getAttribute("content-desc"));
		MobileElement firstReviewDate_ele = firstPositive_ele.get(0).findElementById("com.android.vending:id/review_date");
		System.out.println("--- First positive Review Date ----" + firstReviewDate_ele.getText());
		Thread.sleep(3000);
		MobileElement firstReviewContent_ele = firstPositive_ele.get(0).findElementById("com.android.vending:id/review_content");
		System.out.println("--- First positive Content ----" + firstReviewContent_ele.getText());
		
		Log.info("STEP 2.5.2 print the first Critical comment, commenter and date");
		if(actionSupport.isElementVisible("//android.widget.Button[@text='1']"))
		{
			actionSupport.scrollToElementByID("com.android.vending:id/chip", "1");
			MobileElement oneStars_ele = driver.findElementByXPath("//android.widget.Button[@text='1']");
			oneStars_ele.click();
		}
		else {
			MobileElement critical_ele = driver.findElementByXPath("//android.widget.Button[@text='Critical']");
			critical_ele.click();
		}
		Thread.sleep(2000);
		firstPositive_ele = driver.findElementsByXPath("//android.widget.LinearLayout[@resource-id='com.android.vending:id/review_item_container']");
		firstAuthor_ele = firstPositive_ele.get(0).findElementById("com.android.vending:id/review_author");
		System.out.println("--- First Critial Author ----" + firstAuthor_ele.getText());
		firstRating_ele = firstPositive_ele.get(0).findElementById("com.android.vending:id/review_rating");
		System.out.println("--- First Critial Rating ----" + firstRating_ele.getAttribute("content-desc"));
		firstReviewDate_ele = firstPositive_ele.get(0).findElementById("com.android.vending:id/review_date");
		System.out.println("--- First Critial Review Date ----" + firstReviewDate_ele.getText());
		Thread.sleep(3000);
		firstReviewContent_ele = firstPositive_ele.get(0).findElementById("com.android.vending:id/review_content");
		System.out.println("--- First Critial Content ----" + firstReviewContent_ele.getText());
		
		Log.info("STEP 6 verify product rating in details is matched with the previous step");
		MobileElement ratingDetails_ele = driver.findElementById("com.android.vending:id/li_rating");
		String ratingDetails = ratingDetails_ele.getAttribute("content-desc").split("Star rating: ")[1];
		System.out.println("---Product details rating in Reviews section ---" + ratingDetails);
		assertTrue(ratingDetails.equals(productRating_details));
		
		Thread.sleep(10000);
	}
	
}
