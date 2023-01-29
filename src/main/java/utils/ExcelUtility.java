package utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtility {

	public FileInputStream fi=null;
	public FileOutputStream fo=null;
	public XSSFWorkbook wb=null;
	public XSSFSheet sh=null;
	public XSSFRow row=null;
	public XSSFCell cell=null;
	public CellStyle style=null;
	String path = null;

	public ExcelUtility(String path) {
		this.path = path;
	}

	public int getRowCount(String sheetName) throws Exception {
		fi = new FileInputStream(path);
		wb = new XSSFWorkbook(fi);
		sh = wb.getSheet(sheetName);
		int rowCount = sh.getLastRowNum();
		wb.close();
		fi.close();

		return rowCount;
	}

	public int getCellCount(String sheetName, int rowCount) throws Exception {
		fi = new FileInputStream(path);
		wb = new XSSFWorkbook(fi);
		sh = wb.getSheet(sheetName);
		row = sh.getRow(rowCount);
		int cellCount = row.getLastCellNum();
		wb.close();
		fi.close();

		return cellCount;
	}

	public String getCellData(String sheetName, int rowNum, int colNum) throws Exception {
		fi = new FileInputStream(path);
		wb = new XSSFWorkbook(fi);
	
		try {
			sh = wb.getSheet(sheetName);
			row = sh.getRow(rowNum);
			
			if(row==null)
				return "";
			
			cell = row.getCell(colNum);
			
			if (cell == null)
				return "";
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		DataFormatter form = new DataFormatter();
		String data;

		try {
			data = form.formatCellValue(cell);
		}

		catch (Exception e) {
			data = "";
		}

		wb.close();
		fi.close();

		return data;
	}

	public void setCellData(String sheetName, int rowNum, int colNum, String data) throws Exception {
		fi = new FileInputStream(path);
		wb = new XSSFWorkbook(fi);
		sh = wb.getSheet(sheetName);
		row = sh.getRow(rowNum);
		cell = row.createCell(colNum);
		cell.setCellValue(data);

		fo = new FileOutputStream(path);
		wb.write(fo);

		wb.close();
		fi.close();
		fo.close();

	}

	public void fillGreenColor(String sheetName, int rowNum, int colNum) throws Exception {
		fi = new FileInputStream(path);
		wb = new XSSFWorkbook(fi);
		sh = wb.getSheet(sheetName);

		row = sh.getRow(rowNum);
		cell = row.getCell(colNum);

		style = wb.createCellStyle();

		style.setFillForegroundColor(IndexedColors.GREEN.getIndex());
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

		cell.setCellStyle(style);
		wb.write(fo);

		wb.close();
		fo.close();
		fi.close();

	}

	public void fillRedColor(String sheetName, int rowNum, int colNum) throws Exception {
		fi = new FileInputStream(path);
		wb = new XSSFWorkbook(fi);
		sh = wb.getSheet(sheetName);

		row = sh.getRow(rowNum);
		cell = row.getCell(colNum);

		style = wb.createCellStyle();

		style.setFillForegroundColor(IndexedColors.RED.getIndex());
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

		cell.setCellStyle(style);
		wb.write(fo);

		wb.close();
		fo.close();
		fi.close();

	}

}
