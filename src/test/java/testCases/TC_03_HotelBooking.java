package testCases;

import java.io.IOException;

import org.testng.annotations.Test;

import baseClass.BaseClass;
import pageObject.HomePage;

public class TC_03_HotelBooking extends BaseClass{

	
	@Test
	public void noOfAdults() throws IOException {
		
		HomePage hp= new HomePage(driver);
		hp.clickHotelTab();
		logger.info("Clicked on Hotel tab");
		hp.listData();
		logger.info("All element in list is printed in Console");
		captureScreen("Adult_List");
		logger.info("Screenshot of adult List taken");
		
	}
	
}
