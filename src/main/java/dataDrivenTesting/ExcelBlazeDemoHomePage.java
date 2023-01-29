package dataDrivenTesting;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import utils.Base;
import utils.ExcelUtility;

public class ExcelBlazeDemoHomePage extends Base {

	@Test(dataProvider = "dp")
	public void verifyLoginTest(String fromPort, String toPort) throws InterruptedException {
		driver.get("https://blazedemo.com/");

		WebElement from = driver.findElement(By.name("fromPort"));
		Select sel = new Select(from);
		sel.selectByVisibleText(fromPort);

		WebElement to = driver.findElement(By.name("toPort"));
		Select sel1 = new Select(to);
		sel1.selectByVisibleText(toPort);

		driver.findElement(By.xpath("//input[@type='submit']")).click();

		if (driver.getCurrentUrl().contains("/reserve.php")) {
			driver.findElement(By.cssSelector("[href*='index']")).click();
		}

		else {
			Assert.assertTrue(false);
		}

	}

	@DataProvider(name = "dp")
	public Object[][] getExcelData() throws Exception {

		String path = ".\\dataFiles\\blazeDemo.xlsx";
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
