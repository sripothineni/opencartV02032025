package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {
	
	//Login dataprovider
	@DataProvider(name = "LoginData")
	public String[][] getLoginData() throws IOException{
		
		String path =".\\testData\\Opencart_LoginData.xlsx";
		ExcelUtil xlutil = new ExcelUtil(path);
		
		int totalrows = xlutil.getRowCount("Sheet1");
		int totalcols = xlutil.getCellCount("Sheet1", 1);
		
		String loginData[][]= new String[totalrows][totalcols];
		
		for(int r=1; r<=totalrows; r++) {
			
			for(int c=0;c<totalcols;c++) {
				loginData[r-1][c]=xlutil.getCellData("Sheet1", r, c);
			}
		}
		return loginData;
	}

}
