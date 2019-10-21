package native_app.play_store.common;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;

public class ActionSupport {
	AndroidDriver<MobileElement> _driver;
	FluentWait<AndroidDriver<MobileElement>> wait;
	TouchAction act;
	int x, y;
	PointOption opt;
	WaitOptions waitOpt;
	
	public ActionSupport(AndroidDriver<MobileElement> driver) {
		this._driver = driver;
		this.wait = new FluentWait<AndroidDriver<MobileElement>>(this._driver).withTimeout(Duration.ofSeconds(30))
				.pollingEvery(Duration.ofSeconds(1)).ignoring(NoSuchElementException.class);
		this.act = new TouchAction(driver);
		this.opt = new PointOption();
		this.waitOpt = new WaitOptions();
		Dimension dimensions = driver.manage().window().getSize();
		this.x = dimensions.getWidth();
		this.y = dimensions.getHeight();
	}
	
	
	public void swipe(double startX, double startY, double endX, double endY) {
		PointOption start = opt.point((int)(x*startX) , (int)(y*startY));
		PointOption end = opt.point((int)(x*endX) , (int)(y*endY));
		act.press(start).waitAction().moveTo(end).release().perform();
	}

	public void scrollToElementByText(String resourceID, String title) throws InterruptedException {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(resourceID)));
		MobileElement elementToClick=null;
		try {
			System.out.println("*** 1st time scroll to " + title);
			elementToClick = (MobileElement) this._driver
					.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()"
							+ ".resourceId(\""+resourceID+"\")).scrollIntoView("
							+ "new UiSelector().text(\""+title+"\"));");
		} catch (Exception e) {
			System.out.println("*** 2nd time scroll to " + title);
			elementToClick = (MobileElement) this._driver
					.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()"
							+ ".resourceId(\""+resourceID+"\")).scrollIntoView("
							+ "new UiSelector().text(\""+title+"\"));");
		} finally {
			if(!this.isElementVisible("//*[@text='"+title+"']")){
				System.out.println("*** 3rd time scroll to " + title);
				elementToClick = (MobileElement) this._driver
						.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()"
								+ ".resourceId(\""+resourceID+"\")).scrollIntoView("
								+ "new UiSelector().text(\""+title+"\"));");
			}
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
	
	public boolean isElementVisible(MobileElement ele){
		try {
			 wait.until(ExpectedConditions.visibilityOf(ele));
			 return true;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
	}
	
	public boolean waitForAttributeReady(String xpath, String attribute, String value, int durationOfSeconds){
		this.wait = new FluentWait<AndroidDriver<MobileElement>>(this._driver).withTimeout(Duration.ofSeconds(durationOfSeconds))
				.pollingEvery(Duration.ofSeconds(1)).ignoring(NoSuchElementException.class);
		try {
			 wait.until(ExpectedConditions.attributeToBe(By.xpath(xpath), attribute, value));
			 return true;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
	}
}
