package pageObjectRepository;

import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import userDefinedLibraries.ScreenShot;

public class BeCognizant {
	
	public WebDriver driver;
	
	public BeCognizant(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver,this);
	}
	
	//WebElements Locators+identification
	@FindBy(id="O365_MainLink_MePhoto")WebElement userInfoButton;
	@FindBy(id="mectrl_currentAccount_primary")WebElement userInfoName;
	@FindBy(id="mectrl_currentAccount_secondary")WebElement userInfoEmail;
	@FindBy(xpath="//span[text()='Company']") WebElement Company;
	@FindBy(xpath="//ul[@role='presentation']//span") List<WebElement> list;
	@FindBy(xpath="(//*[@data-automation-id='CanvasSection'])[4]") WebElement nasdaqscroll;
	@FindBy(xpath="//div[@class='stockValue_60e60a8c']") WebElement StockValue;
	
	public void captureUserInfo() {
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		userInfoButton.click();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		ScreenShot.screenShotTC(driver, "userinfo");
		String name=userInfoName.getText();
		String email=userInfoEmail.getText();
		System.out.println("User Name - "+name);
		System.out.println("User Email - "+email);
	}
	
	public void clickoncompany() {
		Company.click();
	}
	
	public void clickonacronym() {
		String m = "Acronyms";
		for(WebElement p :list) {
			if(p.getText().equals(m)) {
				p.click();
				break;
			}
		}
		ScreenShot.screenShotTC(driver, "ClickonAcronyms");
	}
	
	public String ValidateCogniHomePage() {
		String actualUrl="https://cognizantonline.sharepoint.com/sites/Be.Cognizant/SitePages/Home.aspx";
		return actualUrl;
	}
	
	public void scrollBottom() {
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].scrollIntoView();",nasdaqscroll);
	}
	
	public String getStockPrice() {
		return StockValue.getText();
	}

	

}
