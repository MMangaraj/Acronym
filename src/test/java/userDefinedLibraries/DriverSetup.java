package userDefinedLibraries;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class DriverSetup {
	
	public static WebDriver driver;
	public static String url="https://be.cognizant.com/";
	@Parameters({"browser"})
	@BeforeClass
	public void  driverInstantiate(String br) {
			
		if(br.equalsIgnoreCase("chrome")) {
			driver=new ChromeDriver();
		}else if(br.equalsIgnoreCase("edge")) {
			driver=new EdgeDriver();
		}
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		driver.get(url);
		
	}
	@AfterClass
	public static void driverTearDown() {
		driver.quit();
	}
	
}

