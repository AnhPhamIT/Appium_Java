package native_app.play_store.tests;

import static org.testng.Assert.assertTrue;

import java.time.Duration;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import common.WebSupport;
import core.Log;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import native_app.play_store.common.ActionSupport;

public class PlayStoreEndToEnd extends native_app.play_store.core.TestSuite {

	@Test
	public void PlayStoreEndToEndScript() throws InterruptedException {
		ActionSupport actionSupport = new ActionSupport(driver);
		FluentWait<AndroidDriver<MobileElement>> wait = new FluentWait<AndroidDriver<MobileElement>>(driver)
				.withTimeout(Duration.ofSeconds(60)).pollingEvery(Duration.ofSeconds(1))
				.ignoring(NoSuchElementException.class);
		// Log.info("STEP 1. Navigate to \"Books & reference\" section");
		// actionSupport.scrollToElementByID("com.android.vending:id/viewpager",
		// "Previously installed apps");
		// MobileElement section=
		// driver.findElementByXPath("//android.widget.TextView[@text='Previously
		// installed apps']/../../../..");
		// System.out.println("parent--- "+section.getAttribute("resource-id"));
		// List<MobileElement> childs =
		// section.findElementsById("com.android.vending:id/play_card");
		// System.out.println("child---
		// "+childs.get(0).getAttribute("content-desc"));

		Log.info("STEP 1. Navigate to \"Social networking\" section");
		actionSupport.scrollToElementByText("com.android.vending:id/viewpager", "Social networking");
		actionSupport.swipe(0.4, 0.85, 0.4, 0.7);
		MobileElement section = driver
				.findElementByXPath("//android.widget.TextView[@text='Social networking']/../../../..");
		System.out.println("parent--- " + section.getAttribute("resource-id"));
		Thread.sleep(2000);

		Log.info("STEP 2. select the first item to see the details");
		List<MobileElement> childs = section.findElementsById("com.android.vending:id/play_card");
		String firstProductSummary = childs.get(0).getAttribute("content-desc");
		System.out.println("child--- " + firstProductSummary);
		String productName = firstProductSummary.split("Star rating:")[0];
		String productRating = firstProductSummary.split("Star rating:")[1];

		System.out.println("product title ------------ " + productName);

		childs.get(0).click();
		MobileElement productTitle_detail_ele = driver.findElementById("com.android.vending:id/title_title");
		String productTitle_details = productTitle_detail_ele.getText();
		System.out.println("product title in Product details ------------ " + productTitle_details);
		assertTrue(productTitle_details.trim().equals((productName.split("App: ")[1]).trim()),
				"Product title mismatch");

		Thread.sleep(2000);
		if (actionSupport.isElementVisible("//android.widget.Button[@text='Uninstall']")) {
			MobileElement uninstallBtn_ele = driver.findElementByXPath("//android.widget.Button[@text='Uninstall']");
			uninstallBtn_ele.click();
			Thread.sleep(2000);
			MobileElement confirmBtn_ele = driver.findElementByXPath("//android.widget.Button[@text='OK']");
			confirmBtn_ele.click();
			actionSupport.waitForAttributeReady("//*[@text='Install']", "enabled", "true", 300);
			driver.navigate().back();
			MobileElement product_ele = driver.findElementByXPath(
					"//android.widget.RelativeLayout[contains(@content-desc,'" + productName + "'])");
			product_ele.click();
		}
		MobileElement productRating_ele = driver.findElementById("com.android.vending:id/decide_badge_view");
		String productRating_details = productRating_ele.getAttribute("content-desc");
		productRating_details = StringUtils.substringBetween(productRating_details, "Average rating ", " stars");

		Log.info("STEP 3. print out product rating");
		System.out.println("product rating ------------ " + productRating.trim());
		System.out.println("Product rating details ---- " + productRating_details);
		assertTrue(productRating.trim().equals(productRating_details.trim()), "Product rating mismatch");

		Log.info("STEP 4. select \"Rating and reviews\" details");
		actionSupport.scrollToElementByText("com.android.vending:id/recycler_view", "Ratings and reviews");
		MobileElement ratingReviews = driver.findElementByXPath("//*[@text='Ratings and reviews']");
		ratingReviews.click();

		Thread.sleep(2000);
		MobileElement sort_ele = driver
				.findElementByXPath("//android.widget.TextView[@resource-id='com.android.vending:id/sort_label']");
		sort_ele.click();

		Thread.sleep(2000);
		MobileElement mostRecent_ele = driver.findElementByXPath("//android.widget.RadioButton[@text='Most recent']");
		mostRecent_ele.click();

		MobileElement latestVersion_ele = driver.findElementByXPath(
				"//android.widget.CheckBox[@resource-id='com.android.vending:id/filter_by_version']");
		latestVersion_ele.click();

		MobileElement apply_ele = driver.findElementByXPath("//android.widget.TextView[@text='APPLY']");
		apply_ele.click();

		Thread.sleep(2000);

		Log.info("STEP 4.5.1 print the first positive comment, commenter and date");
		if (actionSupport.isElementVisible("//android.widget.Button[@text='5']")) {
			MobileElement fiveStars_ele = driver.findElementByXPath("//android.widget.Button[@text='5']");
			fiveStars_ele.click();
		} else {
			MobileElement positive_ele = driver.findElementByXPath("//android.widget.Button[@text='Positive']");
			positive_ele.click();
		}
		List<MobileElement> firstPositive_ele = null;
		MobileElement firstAuthor_ele = null;
		MobileElement firstRating_ele = null;
		MobileElement firstReviewDate_ele = null;
		Thread.sleep(5000);
		if (!actionSupport.isElementVisible("//*[@resource-id='com.android.vending:id/no_reviews_indicator']")) {
			firstPositive_ele = driver.findElementsByXPath(
					"//android.widget.LinearLayout[@resource-id='com.android.vending:id/review_item_container']");
			firstAuthor_ele = firstPositive_ele.get(0).findElementById("com.android.vending:id/review_author");
			System.out.println("--- First positive Author ----" + firstAuthor_ele.getText());
			firstRating_ele = firstPositive_ele.get(0).findElementById("com.android.vending:id/review_rating");
			System.out.println("--- First positive Rating ----" + firstRating_ele.getAttribute("content-desc"));
			firstReviewDate_ele = firstPositive_ele.get(0).findElementById("com.android.vending:id/review_date");
			System.out.println("--- First positive Review Date ----" + firstReviewDate_ele.getText());

			try {
				actionSupport.isElementVisible(
						firstPositive_ele.get(0).findElementById("com.android.vending:id/review_content"));
				System.out.println("--- First positive Content ----"
						+ firstPositive_ele.get(0).findElementById("com.android.vending:id/review_content").getText());
			} catch (Exception e) {
				System.out.println("First positive Content: no any comment");
			}
		}

		Log.info("STEP 4.5.2 print the first Critical comment, commenter and date");
		if (actionSupport.isElementVisible("//android.widget.Button[@text='1']")) {
			actionSupport.scrollToElementByText("com.android.vending:id/chip", "1");
			MobileElement oneStars_ele = driver.findElementByXPath("//android.widget.Button[@text='1']");
			oneStars_ele.click();
		} else {
			MobileElement critical_ele = driver.findElementByXPath("//android.widget.Button[@text='Critical']");
			critical_ele.click();
		}
		if (!actionSupport.isElementVisible("//*[@resource-id='com.android.vending:id/no_reviews_indicator']")) {
			Thread.sleep(2000);
			firstPositive_ele = driver.findElementsByXPath(
					"//android.widget.LinearLayout[@resource-id='com.android.vending:id/review_item_container']");
			firstAuthor_ele = firstPositive_ele.get(0).findElementById("com.android.vending:id/review_author");
			System.out.println("--- First Critial Author ----" + firstAuthor_ele.getText());
			firstRating_ele = firstPositive_ele.get(0).findElementById("com.android.vending:id/review_rating");
			System.out.println("--- First Critial Rating ----" + firstRating_ele.getAttribute("content-desc"));
			firstReviewDate_ele = firstPositive_ele.get(0).findElementById("com.android.vending:id/review_date");
			System.out.println("--- First Critial Review Date ----" + firstReviewDate_ele.getText());
			try {
				actionSupport.isElementVisible(
						firstPositive_ele.get(0).findElementById("com.android.vending:id/review_content"));
				System.out.println("--- First negative Content ----"
						+ firstPositive_ele.get(0).findElementById("com.android.vending:id/review_content").getText());
			} catch (Exception e) {
				System.out.println("First negative Content: no any comment");
			}
		}
		

		Log.info("STEP 4.5.3 verify product rating in details is matched with the previous step");
		MobileElement ratingDetails_ele = driver.findElementById("com.android.vending:id/li_rating");
		String ratingDetails = ratingDetails_ele.getAttribute("content-desc").split("Star rating: ")[1];
		System.out.println("---Product details rating in Reviews section ---" + ratingDetails);
		assertTrue(ratingDetails.equals(productRating_details));

		Log.info("STEP 4.6 go back and print Developer contact email");
		MobileElement goBack_ele = driver.findElementByAccessibilityId("Navigate up");
		goBack_ele.click();

		actionSupport.scrollToElementByText("com.android.vending:id/recycler_view", "Developer contact");
		MobileElement developerContact_ele = driver.findElementByXPath("//*[@text='Developer contact']");
		developerContact_ele.click();

		actionSupport.scrollToElementByText("com.android.vending:id/recycler_view", "Email");
		MobileElement email_ele = driver.findElementById("com.android.vending:id/byline_subtitle");
		System.out.println("---Developer email--- " + email_ele.getText());

		Log.info("STEP 4.7 install/open app");
		actionSupport.scrollToElementByText("com.android.vending:id/recycler_view", "Install");
		// if(actionSupport.isElementVisible("//android.widget.Button[@text='Install']")){
		MobileElement install_ele = driver.findElementByXPath("//android.widget.Button[@text='Install']");
		install_ele.click();
		actionSupport.waitForAttributeReady("//*[@text='Open']", "enabled", "true", 300);
		// }else
		// if(actionSupport.isElementVisible("//android.widget.Button[@text='Open']")){
		// MobileElement open_ele =
		// driver.findElementByXPath("//android.widget.Button[@text='Open']");
		// open_ele.click();
		// Thread.sleep(10000);
		// driver.navigate().back();
		//
		// }
		Thread.sleep(2000);
		driver.navigate().back();
		Log.info("STEP 5. search for product name at step 2");
		Thread.sleep(2000);
		actionSupport.swipe(0.4, 0.2, 0.4, 0.9);
		// actionSupport.scrollToElementByText("com.android.vending:id/tab_recycler_view",
		// "Recommended for you");
		MobileElement search_ele = driver.findElementById("com.android.vending:id/search_bar_hint");
		search_ele.click();

		MobileElement searchBox_ele = driver.findElementById("com.android.vending:id/search_bar_text_input");
		Log.info("searching for product name " + productTitle_details);
		searchBox_ele.sendKeys(productTitle_details);

		Log.info("STEP 5.1 verify searched product name should be displayed as the first result");
		Thread.sleep(3000);
		MobileElement firstSearchedResult_ele = driver.findElementByXPath("//android.widget.LinearLayout[@index='1']");
		String firstSearchResult = firstSearchedResult_ele.findElementById("com.android.vending:id/suggest_text")
				.getText();
		assertTrue(firstSearchResult.equalsIgnoreCase(productTitle_details));

		Log.info("STEP 5.2 select the first product, verify product has been installed already as step 2");
		firstSearchedResult_ele.click();
		assertTrue(actionSupport.isElementVisible("//android.widget.Button[@text='Open']"),
				"***FAILED*** Product has been installed but no Open button found");
		MobileElement fiveStars_ele;
		Log.info("STEP 5.3 rate this app with 5 stars");
		try {
			fiveStars_ele = driver.findElement(MobileBy.AndroidUIAutomator(
					"new UiScrollable(new UiSelector().resourceId(\"com.android.vending:id/recycler_view\")).getChildByDescription("
							+ "new UiSelector().className(\"android.widget.ImageView\"), \"5 stars unselected\")"));
			fiveStars_ele.click();
		} catch (Exception e) {
			// actionSupport.scrollToElementByText("com.android.vending:id/recycler_view",
			// "Your Review");
			actionSupport.scrollToElementByText("com.android.vending:id/recycler_view", "Edit your review");
			if (actionSupport.isElementVisible("//*[@resource-id='com.android.vending:id/edit_review_button']")) {
				MobileElement yourReview_ele = driver
						.findElementByXPath("//*[@resource-id='com.android.vending:id/edit_review_button']");
				yourReview_ele.click();
			} else {
				MobileElement productLogo_ele = driver
						.findElementByAccessibilityId("com.android.vending:id/title_thumbnail");
				productLogo_ele.click();

				fiveStars_ele = driver.findElement(MobileBy.AndroidUIAutomator(
						"new UiScrollable(new UiSelector().resourceId(\"com.android.vending:id/recycler_view\")).getChildByDescription("
								+ "new UiSelector().className(\"android.widget.ImageView\"), \"5 stars unselected\")"));
				fiveStars_ele.click();
			}
		}

		Log.info("STEP 5.4 input review comment");
		Thread.sleep(3000);
		MobileElement review_ele = driver.findElementById("com.android.vending:id/review_text");
		review_ele.sendKeys("good");

		MobileElement postReview_ele = driver.findElementById("com.android.vending:id/post_review_button");
		postReview_ele.click();

		MobileElement uninstallBtn_ele = driver.findElement(MobileBy.AndroidUIAutomator(
				"new UiScrollable(new UiSelector().resourceId(\"com.android.vending:id/recycler_view\")).getChildByText("
						+ "new UiSelector().className(\"android.widget.Button\"), \"Uninstall\")"));
		// uninstallBtn_ele =
		// driver.findElementById("//android.widget.Button[@text='Uninstall']");
		Log.info("STEP 5.5 uninstall the app");
		uninstallBtn_ele.click();

		MobileElement OK_ele = driver.findElementByXPath("//android.widget.Button[@text='OK']");
		OK_ele.click();
		actionSupport.waitForAttributeReady("//*[@text='Install']", "enabled", "true", 300);
		driver.navigate().back();

		Log.info("STEP 6. On Playstore home screen, show all \"Recommended for you\"");
		Thread.sleep(3000);
		actionSupport.scrollToElementByText("com.android.vending:id/viewpager", "Recommended for you");
		// actionSupport.swipe(0.4, 0.85, 0.4, 0.7);
		MobileElement recommend_ele = driver.findElementByXPath(
				"//*[@resource-id='com.android.vending:id/header_title_main'][@text='Recommended for you']");
		recommend_ele.click();
		Thread.sleep(3000);
		int highestRatingIndex = 0;
		int lowestRatingIndex = 0;
		float highestRatingRs = 0;
		float lowestRatingRs = 5;
		for (int i = 1; i <= 6; i++) {
			MobileElement item = driver
					.findElementByXPath("//*[@resource-id='com.android.vending:id/play_card'][@index='" + i + "']");
			productName = item.getAttribute("content-desc");
			System.out.println("--- Product " + i + " name --: " + productName);
			float rating = 0;
			if (!(productName.split("Star rating: ")[1]).trim().equals("")) {
				rating = Float.parseFloat(productName.split("Star rating: ")[1]);
			}
			if (rating > highestRatingRs) {
				highestRatingRs = rating;
				highestRatingIndex = i;
			}
			if (rating < lowestRatingRs) {
				lowestRatingRs = rating;
				lowestRatingIndex = i;
			}
		}
		Thread.sleep(3000);
		System.out.println("Highest rating is :" + highestRatingRs);
		MobileElement highestRating_ele = driver.findElementByXPath(
				"//*[@resource-id='com.android.vending:id/play_card'][@index='" + highestRatingIndex + "']");
		highestRating_ele.click();
		Thread.sleep(3000);
		MobileElement reviewsNo_ele = driver
				.findElementByXPath("//*[@resource-id='com.android.vending:id/subtitle_text'][@index='1']");
		String reviewsNo = reviewsNo_ele.getText();
		System.out.println("Number of rating reviews " + reviewsNo);
		Thread.sleep(3000);
		driver.navigate().back();

		System.out.println("Lowest rating is :" + lowestRatingRs);
		MobileElement lowestRating_ele = driver.findElementByXPath(
				"//*[@resource-id='com.android.vending:id/play_card'][@index='" + lowestRatingIndex + "']");
		lowestRating_ele.click();
		Thread.sleep(3000);
		reviewsNo_ele = driver
				.findElementByXPath("//*[@resource-id='com.android.vending:id/subtitle_text'][@index='1']");
		reviewsNo = reviewsNo_ele.getText();
		System.out.println("Number of rating reviews " + reviewsNo);
		Thread.sleep(3000);
		driver.navigate().back();
		driver.navigate().back();
		// actionSupport.swipe(0.4, 0.2, 0.4, 0.9);
		Log.info("STEP 7. Scroll to \"Family\" section");
		MobileElement element = driver.findElement(MobileBy.AndroidUIAutomator(
				"new UiScrollable(new UiSelector().resourceId(\"com.android.vending:id/play_header_list_tab_scroll\")).setAsHorizontalList().scrollIntoView("
						+ "new UiSelector().text(\"Family\"))"));
		element.click();

		MobileElement ages9_ele = driver.findElementByXPath("//android.widget.Button[@text='Ages 9 & Up']");
		ages9_ele.click();

		actionSupport.scrollToElementByText("com.android.vending:id/tab_recycler_view", "Top Charts");
		// actionSupport.swipe(0.4, 0.2, 0.4, 0.9);
		MobileElement thirdTopChart = driver
				.findElementByXPath("//*[@resource-id='com.android.vending:id/play_card'][@index='2']");
		thirdTopChart.click();
		Thread.sleep(10000);
	}

	public void TestScript() throws InterruptedException {
		ActionSupport actionSupport = new ActionSupport(driver);
		FluentWait<AndroidDriver<MobileElement>> wait = new FluentWait<AndroidDriver<MobileElement>>(driver)
				.withTimeout(Duration.ofSeconds(60)).pollingEvery(Duration.ofSeconds(1))
				.ignoring(NoSuchElementException.class);
		// actionSupport.scrollToElementByText("com.android.vending:id/play_header_list_tab_scroll",
		// "Family");

		MobileElement element = driver.findElement(MobileBy.AndroidUIAutomator(
				"new UiScrollable(new UiSelector().resourceId(\"com.android.vending:id/play_header_list_tab_scroll\")).setAsHorizontalList().scrollIntoView("
						+ "new UiSelector().text(\"Family\"))"));
		element.click();

		MobileElement ages9_ele = driver.findElementByXPath("//android.widget.Button[@text='Ages 9 & Up']");
		ages9_ele.click();

		actionSupport.scrollToElementByText("com.android.vending:id/tab_recycler_view", "Top Charts");
		actionSupport.swipe(0.4, 0.2, 0.4, 0.9);
		MobileElement thirdTopChart = driver
				.findElementByXPath("//*[@resource-id='com.android.vending:id/play_card'][@index='2']");
		thirdTopChart.click();
		Thread.sleep(10000);
	}

	public void step4() throws InterruptedException {
		ActionSupport actionSupport = new ActionSupport(driver);
		FluentWait<AndroidDriver<MobileElement>> wait = new FluentWait<AndroidDriver<MobileElement>>(driver)
				.withTimeout(Duration.ofSeconds(60)).pollingEvery(Duration.ofSeconds(1))
				.ignoring(NoSuchElementException.class);
		Log.info("STEP 1. Navigate to \"Social networking\" section");
		actionSupport.scrollToElementByText("com.android.vending:id/viewpager", "Recommended for you");
		// actionSupport.swipe(0.4, 0.85, 0.4, 0.7);
		MobileElement recommend_ele = driver.findElementByXPath(
				"//*[@resource-id='com.android.vending:id/header_title_main'][@text='Recommended for you']");
		recommend_ele.click();
		Thread.sleep(3000);
		int highestRatingIndex = 0;
		int lowestRatingIndex = 0;
		float highestRatingRs = 0;
		float lowestRatingRs = 5;
		for (int i = 1; i <= 10; i++) {
			MobileElement item = driver
					.findElementByXPath("//*[@resource-id='com.android.vending:id/play_card'][@index='" + i + "']");
			String productName = item.getAttribute("content-desc");
			System.out.println("--- Product " + i + " name --: " + productName);
			float rating = 0;
			if (!(productName.split("Star rating: ")[1]).trim().equals("")) {
				rating = Float.parseFloat(productName.split("Star rating: ")[1]);
			}
			if (rating > highestRatingRs) {
				highestRatingRs = rating;
				highestRatingIndex = i;
			}
			if (rating < lowestRatingRs) {
				lowestRatingRs = rating;
				lowestRatingIndex = i;
			}
		}
		System.out.println("Highest rating is :" + highestRatingRs);
		MobileElement highestRating_ele = driver.findElementByXPath(
				"//*[@resource-id='com.android.vending:id/play_card'][@index='" + highestRatingIndex + "']");
		highestRating_ele.click();
		MobileElement reviewsNo_ele = driver
				.findElementByXPath("//*[@resource-id='com.android.vending:id/subtitle_text'][@index='1']");
		String reviewsNo = reviewsNo_ele.getText();
		System.out.println("Number of rating reviews " + reviewsNo);
		driver.navigate().back();

		System.out.println("Lowest rating is :" + lowestRatingRs);
		MobileElement lowestRating_ele = driver.findElementByXPath(
				"//*[@resource-id='com.android.vending:id/play_card'][@index='" + lowestRatingIndex + "']");
		lowestRating_ele.click();
		reviewsNo_ele = driver
				.findElementByXPath("//*[@resource-id='com.android.vending:id/subtitle_text'][@index='1']");
		reviewsNo = reviewsNo_ele.getText();
		System.out.println("Number of rating reviews " + reviewsNo);
		Thread.sleep(10000);

	}

}
