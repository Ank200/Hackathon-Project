package pageObject;

import java.time.Duration;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
//import org.openqa.selenium.support.PageFactory;

public class BasePage {
	public WebDriver driver;
	public static WebDriverWait wait;	
	public static Actions act;
	public static JavascriptExecutor js;
		
	public BasePage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver=driver;
		PageFactory.initElements(driver,this);
		wait= new WebDriverWait(driver,Duration.ofSeconds(10));
		act=new Actions(driver);
		js= (JavascriptExecutor)driver;
	}
	public int randomNumber() {
		int randomNumber = (int)(Math.random()*20)+1;
		return randomNumber;
	}
	public String randomPhoneNumber()
	{
		String generatedString=RandomStringUtils.randomNumeric(10);
		return generatedString;
	}
	public String randomName()
	{
		String generatedString=RandomStringUtils.randomAlphabetic(5);
		return generatedString;
	}
	public String randomEmail()
	{
		String s1=RandomStringUtils.randomAlphabetic(5);
		String s2=RandomStringUtils.randomAlphabetic(5);
		return s1+'@'+s2;
	}
}