package pageObject;

import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class HomePage extends BasePage{
	
	public HomePage(WebDriver driver){
		super(driver);
	}
	
	List<WebElement> adults;
	
	@FindBy(xpath = "//nav//li[@data-cy='menu_Cabs']//a/span")
	WebElement cabs;
	
	@FindBy(xpath = "//input[@placeholder='To']")
	WebElement toCity;
	
	@FindBy(linkText = "SEARCH")
	WebElement search;
	
	@FindBy(xpath = "//a[@href='https://www.makemytrip.com/hotels/']")
	WebElement hotel;
	
	@FindBy(xpath = "//li[@data-cy='tertiaryRowItem_Gift Cards']")
	WebElement giftCard;

	
	public void clickCabTab() {
		js.executeScript("arguments[0].click();", cabs);
	}
	
	public void fillFromCity(String city) throws InterruptedException {
		WebElement fromCity= wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='fromCity']")));
		act.moveToElement(fromCity).click().perform();

		Thread.sleep(2000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='From']"))).sendKeys(city);
		Thread.sleep(2000);
		WebElement w= wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//li//span[contains(text(),'"+city+"')])[1]")));
		act.moveToElement(w).click().build().perform();
	}
	public void fillToCity(String city) throws InterruptedException {
		toCity.sendKeys(city);
		Thread.sleep(2000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//li//span[contains(text(),'"+city+"')])[1]"))).click();
	}
	public int monthConverter(String month) {
		
		int mon=0;
		switch(month.toLowerCase()) {
			case "mar":{
				mon=3;
				break;
			}
			case "apr":{
				mon=4;
				break;
			}
			case "may":{
				mon=5;
				break;
			}
			case "jun":{
				mon=6;
				break;
			}
			case "jul":{
				mon=7;
				break;
			}
		}
		return mon;
	}
	public void datePicker(String date) {
		String[] s=date.split("-");
		String month=s[1];
		int day=Integer.parseInt(s[0]);		
		int n= monthConverter(month);
		
		for(int i=0;i<4;i++){
			String text1=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//div[@role='heading'])[1]/div"))).getText();
			text1=text1.substring(0,3);
			int m= monthConverter(text1);
			
			if(m==n) {
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='DayPicker-Months']/div[1]//div[text()='"+day+"']"))).click();
				break;
			}
			else if(n==m+1) {
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='DayPicker-Months']/div[2]//div[text()='"+day+"']"))).click();
				break;
			}
			else if(m>n) {
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@aria-label='Previous Month']"))).click();
			}
			else if(m<n) {
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@aria-label='Next Month']"))).click();
			}
		}
	}
	
	public void timeSelector(String time, String shift) throws InterruptedException {
		String[] s= time.split(":");
		Thread.sleep(2000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[@data-cy='HrSlots_81']//span[contains(text(),'"+s[0]+"')]"))).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[@data-cy='MinSlots_83']/span[contains(text(),'"+s[1]+"')]"))).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[@data-cy='MeridianSlots_82']/span[contains(text(),'"+shift+"')]"))).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='APPLY']"))).click();

	}
	
	public void clickSearch() throws InterruptedException {
		Thread.sleep(2000);
		js.executeScript("arguments[0].click();", search);
	}
	
	public void clickHotelTab() {
		hotel.click();
	}
	
	public List<WebElement> listOfAdults(){
		WebElement w1= wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='hsw_inputBox roomGuests']")));
		act.moveToElement(w1).click().perform();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@data-testid='adult_count']"))).click();
		adults=wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//ul[@class='gstSlct__list']/li"))); 
		
		return adults;
	}
	
	public void listData() {
		listOfAdults();
		System.out.println("The List of Adults available for booking: ");
		System.out.println();
		for(WebElement ele: adults) {
			System.out.println(ele.getText());
		}
	}
	
	public void clickGiftCard() {
		giftCard.click();
	}
	
	public void switchWindow() {
		String wh= driver.getWindowHandle();
		Set<String> list= driver.getWindowHandles();
		for(String s:list) {
			if(s!=wh) {
				driver.switchTo().window(s);
			}
		}
	}

}
