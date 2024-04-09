package testSenario;


import userDefinedLibraries.DriverSetup;
import pageObjectRepository.Acronym;
import pageObjectRepository.BeCognizant;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import userDefinedLibraries.ExcelUtils;
import userDefinedLibraries.ScreenShot;

public class TestCase extends DriverSetup {
	
	BeCognizant lp;
	Acronym ls;
	
  @Test(priority = 0)
  public void captureUserInfo() {
	  lp=new BeCognizant(driver);
	  lp.captureUserInfo();
  }
  @Test(priority = 1)
  public void clickoncompany() {
	  lp.clickoncompany();
	  System.out.println("Clicked on Company DropDown");
	  
  }
  @Test(priority = 2)
  public void clickonacronym() {
	  lp.clickonacronym();
	  System.out.println("Clicked on Acronym");
  }
  @Test(priority = 3)
  public void title() {
	  ls = new Acronym(driver);
	  Assert.assertEquals(ls.urltitle(),true,"Acronyms is not Displayed");
	  System.out.println("Acronyms is Displayed");
  }
  @Test(priority = 4)
  public void tooltip() {
	  System.out.println("ToolTip is :"+ls.tooltip());
  }
  @Test(priority = 5)
  public void data() {
	  System.out.println("Total Data Available:"+ls.datasize());
  }
  @Test(priority = 6)
  public void alldata() {
	  System.out.println("Printing all The Data");
	  try {
		ls.alldata();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
  }
  @Test(priority = 7)
  public void clickonbecognizant() {
	  ls.clickonbecognizant();
	  System.out.println("Clicked on Be.Cognizant");
  }
  @Test(priority = 8)
  public void ValidateCogniHomePage() {
	  Assert.assertEquals(lp.ValidateCogniHomePage(), driver.getCurrentUrl());
	  System.out.println("This is Home Page");
  }
  @Test(priority = 9)
  public void scrollBottom() {
	  lp.scrollBottom();
  }
  @Test(priority = 10)
  public void getStockPrice() {
	  
	  System.out.println("Current Stock Price is "+lp.getStockPrice());
	  
  }
  @Test(priority = 11)
  public void ValidateStockPrice() throws IOException {
	  String file = System.getProperty("user.dir")+"\\testdata\\StocksData.xlsx";
		int rows=ExcelUtils.getRowCount(file, "Sheet1");
		int flag=0;
		for(int i=1;i<rows;i++) {
			String stocksdata=ExcelUtils.getCellData(file,"Sheet1" , i, 1);	
			if( Double.parseDouble(stocksdata) == Double.parseDouble(lp.getStockPrice()) ) {
				String date=ExcelUtils.getCellData(file,"Sheet1" , i, 0);
				System.out.println(date);
				flag++;
			}
			
		}
		ScreenShot.screenShotTC(driver, "StocksPrice");
		if(flag==0) {
			System.out.println("No Data Found");
		}
  }
  

  
}
