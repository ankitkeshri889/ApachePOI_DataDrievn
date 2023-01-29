package dataDrivenTesting;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import utils.ExcelUtility;

public class ExcelDataDriven {

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
	public void verifyLoginTest(String username, String password) throws InterruptedException {
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

		if (driver.getCurrentUrl().contains("/admin/")) {
			System.out.println(username + " : " + password + "->  Login is successful");
			driver.findElement(By.cssSelector("a[href='/logout']")).click();
		}

		else {
			System.out.println(username + " : " + password + "->  Login failed ");
		}

	}

	@DataProvider(name = "dp")
	public Object[][] getExcelData() throws Exception {

		String path = ".\\dataFiles\\data.xlsx";
		ExcelUtility excel = new ExcelUtility(path);

		int rows = excel.getRowCount("Sheet1");
		int cols = excel.getCellCount("Sheet1", 1);

		String data[][] = new String[rows][cols];

		for (int i = 1; i <= rows; i++) {
			for (int j = 0; j < cols; j++) {
				data[i - 1][j] = excel.getCellData("Sheet1", i, j);
			}
		}

		return data;

	}

	@AfterClass
	public void tearDown() {
		driver.close();
	}

}
