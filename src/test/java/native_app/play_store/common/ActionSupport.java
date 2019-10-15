package native_app.play_store.common;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

public class ActionSupport {
	AndroidDriver<MobileElement> _driver;
	FluentWait<AndroidDriver<MobileElement>> wait;

	public ActionSupport(AndroidDriver<MobileElement> driver) {
		this._driver = driver;
		this.wait = new FluentWait<AndroidDriver<MobileElement>>(this._driver).withTimeout(Duration.ofSeconds(30))
				.pollingEvery(Duration.ofSeconds(1)).ignoring(NoSuchElementException.class);
	}

	public void scrollToElementByID(String resourceID, String title) throws InterruptedException {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(resourceID)));
		MobileElement elementToClick;
		try {
			elementToClick = (MobileElement) this._driver
					.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()"
							+ ".resourceId(\""+resourceID+"\")).scrollIntoView("
							+ "new UiSelector().text(\""+title+"\"));");
		} catch (Exception e) {
			elementToClick = (MobileElement) this._driver
					.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()"
							+ ".resourceId(\""+resourceID+"\")).scrollIntoView("
							+ "new UiSelector().text(\""+title+"\"));");
		} finally {
			elementToClick = (MobileElement) this._driver
					.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()"
							+ ".resourceId(\""+resourceID+"\")).scrollIntoView("
							+ "new UiSelector().text(\""+title+"\"));");
		}
		Thread.sleep(1000);
		wait.until(ExpectedConditions.elementToBeClickable(elementToClick));
		//elementToClick.click();
	}
	
	public void clickOnXPath(String xpath){
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath))).click();
	}
	
	public void sendsKeyOnXpath(String xpath, String value){
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).sendKeys(value);;
	}
	
	public String getElementAttribute(String xpath, String attributeName){
		MobileElement ele = (MobileElement)wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
		return ele.getAttribute(attributeName);
	}
	
	public boolean isElementVisible(String xpath){
		try {
			 wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
			 return true;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
	}
}
