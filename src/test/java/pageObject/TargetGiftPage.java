package pageObject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;


public class TargetGiftPage extends BasePage{

	public TargetGiftPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath = "//span[@data-cy='Counter_443']")
	WebElement plus;
	
	@FindBy(xpath = "//input[@name='senderName']")
	WebElement senderName;
	
	@FindBy(xpath = "//input[@name='senderMobileNo']")
	WebElement senderPhoneNumber;
	
	@FindBy(xpath = "//input[@name='senderEmailId']" )
	WebElement senderEmailId;
	
	@FindBy(xpath = "//button[normalize-space()='BUY NOW']")
	WebElement buyNow;
	
	public void noOfGifts(int n) {
		for(int i=1; i<n;i++) {
			plus.click();
		}
	}
	
	public void groupGifting() {
		WebElement ele= wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='slider round']")));
		js.executeScript("arguments[0].click();", ele);
	}
	
	public void inputPhoneNumber() {
		List<WebElement> list= wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[@class='append-bottom12']//input")));
		for(WebElement ele:list) {
			String s= randomPhoneNumber();
			ele.sendKeys(s);
		}
	}
	
	public void senderInfo() {
		senderName.sendKeys(randomName());
		senderPhoneNumber.sendKeys(randomPhoneNumber());
		senderEmailId.sendKeys(randomEmail());
	}
	
	public void clickBuyNow() {
		buyNow.click();
	}
	
	public String captureErrorMessage() {
		
		WebElement ele=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='form__field__wrap']/p")));
		js.executeScript("arguments[0].scrollIntoView();", ele);
		String errorMessage= ele.getText();
		return errorMessage;
	}
}
