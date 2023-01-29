package dataDrivenTesting;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DataDrivenTest {

	WebDriver driver;

	@BeforeClass
	public void setup() {
		System.setProperty("webdriver.chrome.driver", "D:\\Important\\Selenium\\driver\\chromedriver.exe");
		driver = new ChromeDriver();

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().deleteAllCookies();

	}

	@Test(dataProvider = "dp1")
	public void verifyLoginTest(String username, String password, String expected) throws InterruptedException {
		driver.get("https://admin-demo.nopcommerce.com/login?ReturnUrl=%2Fadmin%2F");

		WebElement user = driver.findElement(By.id("Email"));
		user.clear();
		user.sendKeys(username);

		WebElement pass = driver.findElement(By.id("Password"));
		pass.clear();
		pass.sendKeys(password);

		driver.findElement(By.cssSelector("input[type*='ch']")).click();
		driver.findElement(By.xpath("//button[@type='submit']")).click();

		Thread.sleep(2000);

		String expectedTitle = "Dashboard / nopCommerce administration";
		String actualTitle = driver.getTitle();

		if (expected.equals("Valid")) {
			if (expectedTitle.equals(actualTitle)) {
				driver.findElement(By.cssSelector("a[href='/logout']")).click();
				Assert.assertTrue(true);
			} else {
				Assert.assertTrue(false);
			}
		}

		else if (expected.equals("Invalid")) {
			if (expectedTitle.equals(actualTitle)) {
				driver.findElement(By.cssSelector("a[href='/logout']")).click();
				Assert.assertTrue(false);
			} else {
				Assert.assertTrue(true);
			}
		}
	}


	@DataProvider(name = "dp1")
	public Object[][] getExcelData1() {

		String data[][] = { { "admin@yourstore.com", "admin", "Valid" }, { "admin@yourstore.com", "adm", "Invalid" },
				{ "admin@yourstore.com", "admin123", "Invalid" }, { "admin11@yourstire.com", "admin", "Invalid" },
				{ "adm@yourstore.com", "admin11", "Invalid" } };

		return data;

	}

	@AfterClass
	public void tearDown() {
		driver.close();
	}

}
