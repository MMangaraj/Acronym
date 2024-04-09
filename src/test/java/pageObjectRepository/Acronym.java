package pageObjectRepository;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import userDefinedLibraries.ScreenShot;

public class Acronym {
	
public WebDriver driver;
	
	public Acronym(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver,this);
	}
	
	//WebElements Locators+identification
	@FindBy(xpath="//div[@title='Acronyms']") WebElement title;
	@FindBy(tagName="tr") List<WebElement> data;
	@FindBy(xpath="//tbody//tr[1]//td") List<WebElement> col;
	@FindBy(xpath="//span[text()='be.cognizant']") WebElement becognizant;
	
	public boolean urltitle() {
		ScreenShot.screenShotTC(driver, "AcronymTitle");
		return title.isDisplayed();
	}
	
	public String tooltip() {
		String tooltip= title.getAttribute("title");
		return tooltip;
	}
	
	public int datasize() {
		return data.size();
	}
	
	public void alldata() throws IOException {
		FileOutputStream fos=new FileOutputStream(System.getProperty("user.dir")+"\\testdata\\Output.xlsx");
		XSSFWorkbook wb=new XSSFWorkbook();
		XSSFSheet sheet=wb.createSheet(); 
		for(int r=1;r<=data.size();r++) {
			XSSFRow row=sheet.createRow(r-1);
			for(int c=1;c<=col.size();c++) {
				String value = driver.findElement(By.xpath("//tbody//tr["+r+"]//td["+c+"]")).getText();
				System.out.print(value+"  ");
				row.createCell(c-1).setCellValue(value);
			}
			System.out.println();
		}
		ScreenShot.screenShotTC(driver, "alldata");
		wb.write(fos);
		wb.close();
		fos.close();
		
	}
	public void clickonbecognizant() {
		becognizant.click();
	}
	
}
