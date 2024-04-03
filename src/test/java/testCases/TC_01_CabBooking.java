package testCases;

import java.io.IOException;

import org.testng.annotations.Test;

import baseClass.BaseClass;
import pageObject.CabBookingPage;
import pageObject.HomePage;
import testUtilities.DataProviders;

public class TC_01_CabBooking extends BaseClass{

	
	@Test(priority = 1, dataProvider = "DataInput", dataProviderClass=DataProviders.class)
	public void fillingDetails(String fromCity,String toCity, String date, String time ,String zone) throws InterruptedException, IOException {
		HomePage hp= new HomePage(driver);
		hp.clickCabTab();
		hp.fillFromCity(fromCity);
		logger.info(fromCity+" is put in 'from' City");
		hp.fillToCity(toCity);
		logger.info(toCity+" is put in 'to' City");
		hp.datePicker(date);
		logger.info("Date picked successfully");
		hp.timeSelector(time,zone);
		logger.info("Time selected successfully");
		captureScreen("Data_Provided");
		logger.info("Screenshot Captured");
		hp.clickSearch();
		logger.info("Search option clicked");
		
		CabBookingPage cbp= new CabBookingPage(driver);
		cbp.clickSUV();
		logger.info("SUV clickbox selected");
		captureScreen("Min_Cab_Price");
		logger.info("Screenshot Captured");
		cbp.minPrice(fromCity,toCity);
		logger.info("Minimum price is read and printed");
		
		cbp.returnHomePage();
		logger.info("Returned to MakeMyTrip Home page");
	}
}
