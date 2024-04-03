package testCases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import baseClass.BaseClass;
import pageObject.GiftCardPage;
import pageObject.HomePage;
import pageObject.TargetGiftPage;

public class TC_02_GiftCard extends BaseClass{

	
	@Test(priority = 1)
	public void switchFrame() {
		
		HomePage hp= new HomePage(driver);
		hp.clickGiftCard();
		logger.info("Clicked on Gift Card Button");
		hp.switchWindow();
		logger.info("Switched to Gift Card Window");		
	}
	
	@Test(priority = 2, dependsOnMethods = {"switchFrame"})
	public void selectingGiftCard() throws InterruptedException {
		
		GiftCardPage gcp= new GiftCardPage(driver);
		gcp.selectingGiftCard();
		logger.info("Random Gift Card selected");
		
		TargetGiftPage tcp= new TargetGiftPage(driver);
		tcp.noOfGifts(5);
		logger.info("Multiple Gift Card option selected");
		tcp.groupGifting();
		logger.info("Gift card given to multiple people");
		
	}
	
	@Test(priority = 3, dependsOnMethods = "selectingGiftCard")
	public void insertingData() {
		
		TargetGiftPage tcp= new TargetGiftPage(driver);

		tcp.inputPhoneNumber();
		logger.info("Random phone number given");
		tcp.senderInfo();
		logger.info("Sender info filled in form");
		tcp.clickBuyNow();
		logger.info("Buy Now botton is clicked");
		
	}
	
	@Test(priority = 4, dependsOnMethods = "insertingData")
	public void cpaturingErrorMessage() throws IOException {
		
		TargetGiftPage tcp= new TargetGiftPage(driver);
		
		String error=tcp.captureErrorMessage();
		
		captureScreen("Error_Message");
		logger.info("Screenshot of error message is taken");
		
		System.out.println("Error Message: "+error);
		logger.info("Error message is read and printed in console");

		Assert.assertEquals(error,"Please enter a valid Email id.");
		logger.info("Assertion is done successfully");
	}
}
