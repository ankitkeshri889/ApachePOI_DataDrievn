package dataDrivenTesting;

import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DataDriven1 {

	WebDriver driver;

	@BeforeClass
	public void setup() {
		System.setProperty("webdriver.chrome.driver", "D:\\Important\\Selenium\\driver\\chromedriver.exe");
		driver = new ChromeDriver();

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().deleteAllCookies();

	}

	@Test(dataProvider = "dp")
	public void dryRun(String user, String pwd) {
		System.out.println(user + " " + pwd);
	}

	@DataProvider(name = "dp")
	public Object[][] getExcelData() {

		String data[][] = { { "admin@yourstore.com", "admin" }, { "admin@yourstore.com", "adm" },
				{ "admin@yourstore.com", "admin123" }, { "admin11@yourstire.com", "admin" },
				{ "adm@yourstore.com", "admin11" } };

		return data;

	}

	@AfterClass
	public void tearDown() {
		driver.close();
	}

}
