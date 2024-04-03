package baseClass;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.*;

import pageObject.PopupPage;

 
public class BaseClass {
	
	public static WebDriver driver;
	public Logger logger;
	public Properties p;
	
	
	@Parameters({"browser"})
	@BeforeClass
	public void setup(String br) throws IOException {
		 FileReader file=new FileReader(".//src//test//resources//config.properties");
		 p=new Properties();
		 p.load(file);
		 
		 logger=LogManager.getLogger(this.getClass());

		 switch(br.toLowerCase()) {
		 case "chrome":{
			 driver=new ChromeDriver();
			 logger.info("Test started in Chrome Browser");
			 System.out.println("Test started in Google Chrome..........  ");
			 System.out.println();
			 System.out.println("***********************************************");
			 System.out.println();
			 break;
		 	}
		 case "edge":{
			 driver=new EdgeDriver();
			 logger.info("Test started in Edge Browser");
			 System.out.println("Test started in Microsoft Edge..........  ");
			 System.out.println();
			 System.out.println("***********************************************");
			 System.out.println();
			 break;
		 	}
		 default:{
			 System.out.println("No matching browser selected");
			 logger.error("Browser selected is wrong. Pls check it carefully and try again");
			 return;
		 	}
		 }
		driver.manage().deleteAllCookies();
		driver.get(p.getProperty("appUrl"));
		logger.info("Directed to MakeMyTrip website");
//		driver.get("https://www.makemytrip.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}
	
	
	@AfterClass
	public void complete() {
		System.out.println();
		System.out.println("********************************************************************************************");
		System.out.println();
		driver.quit();
	}
	
	public static String captureScreen(String tname) throws IOException {

		String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		TakesScreenshot takesScreenshot = (TakesScreenshot)driver;
		File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
		String targetFilePath=System.getProperty("user.dir")+"\\screenshots\\" + tname + "_" + timeStamp + ".png";
		File targetFile=new File(targetFilePath);
		FileUtils.copyFile(sourceFile,targetFile);
		sourceFile.renameTo(targetFile);	
		return targetFilePath;

	}
	@BeforeMethod
	public void popupHandle() throws InterruptedException {
		try{
			PopupPage p= new PopupPage(driver);
			p.switchToFrame();
			logger.info("Switch to PopUp Frame");
			Thread.sleep(2000);
			p.clickCloseButton();
			logger.info("Close the PopUp window");
			Thread.sleep(2000);
			p.switchOutOfFrame();
			logger.info("Switch back to main window");
			logger.info("PopUp Handled Successfully");
		}
		catch(Exception e) {
		}		
	}	
}