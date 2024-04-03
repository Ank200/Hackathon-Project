package testUtilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {

	//DataProvider 1
	
	@DataProvider(name="DataInput")
	public String [][] getData() throws IOException
	{
		String path=".\\testData\\MakeMyTrip.xlsx";
		
		ExcelUtility xlutil=new ExcelUtility(path);
		
		int totalrows=xlutil.getRowCount("Sheet1");	
		int totalcells=xlutil.getCellCount("Sheet1",1);
				
		String data[][]=new String[totalrows][totalcells];
		
		for(int i=1;i<=totalrows;i++)  
		{		
			for(int j=0;j<totalcells;j++)  
			{
				data[i-1][j]= xlutil.getCellData("Sheet1",i, j); 
			}
		}
	return data;				
	}
}
