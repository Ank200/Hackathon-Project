package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CabBookingPage extends BasePage{
	
	public CabBookingPage(WebDriver driver) {
		super(driver);
	}
	
	
	@FindBy(xpath = "//label[normalize-space()='SUV']")
	WebElement suv;
	
	
	@FindBy(xpath = "//div[@id='List']/div[1]//p[contains(text(),'₹')][@class='font28 latoBlack blackText ']")
	WebElement minPrice;
	
	boolean isSUV=false;
	public void clickSUV() {
		 try{
			 suv.click();
			 isSUV=true;
		 }
		 catch(Exception e) {
		 }
	}
	
	public void minPrice(String fromCity,String toCity) {
		
		js.executeScript("arguments[0].scrollIntoView();", minPrice);
		String min=minPrice.getText();
		if(isSUV) {
			System.out.println("Minimum SUV price form "+fromCity+" to "+toCity+" is: " + min);
		}
		else {
			System.out.println("No SUV car is available form "+fromCity+" to "+toCity);
		}
		
	}
	
	public void returnHomePage() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@class='chMmtLogo']"))).click();
	}
}
