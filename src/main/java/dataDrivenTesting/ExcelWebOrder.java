package dataDrivenTesting;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import utils.Base;
import utils.ExcelUtility;

public class ExcelWebOrder extends Base {

	@Test(dataProvider = "dp")
	public void verifyLoginTest(String username, String password) throws InterruptedException {
		driver.get(
				"http://secure.smartbearsoftware.com/samples/TestComplete11/WebOrders/Login.aspx?ReturnUrl=%2fsamples%2fTestComplete11%2fWebOrders%2fDefault.aspx");

		WebElement user = driver.findElement(By.name("ctl00$MainContent$username"));
		user.sendKeys(username);

		WebElement pass = driver.findElement(By.name("ctl00$MainContent$password"));
		pass.sendKeys(password);

		driver.findElement(By.xpath("//input[@type='submit']")).click();

		Thread.sleep(2000);

		if (driver.getCurrentUrl().contains("/admin/")) {
			System.out.println(username + " : " + password + "->  Login is successful");
			driver.findElement(By.id("ctl00_logout")).click();
		}

		else {
			System.out.println(username + " : " + password + "->  Login failed ");
		}

	}

	@DataProvider(name = "dp")
	public Object[][] getExcelData() throws Exception {

		String path = ".\\dataFiles\\orangeHRM.xlsx";
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

}
