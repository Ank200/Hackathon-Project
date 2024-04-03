package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class PopupPage extends BasePage{
	
	public PopupPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//iframe[@id='webklipper-publisher-widget-container-notification-frame']")
	WebElement frame;
	
	@FindBy(xpath = "//a[@id='webklipper-publisher-widget-container-notification-close-div']")
	WebElement closeButton;
	
	String windowHandle;

	
	public void switchToFrame() throws InterruptedException {
		windowHandle=driver.getWindowHandle();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//iframe[@id='webklipper-publisher-widget-container-notification-frame']")));
		driver.switchTo().frame(frame);
	}
	public void clickCloseButton() {
		js.executeScript("arguments[0].click();", closeButton);
	}
	public void switchOutOfFrame() {
		driver.switchTo().defaultContent();
	}
}

	
		