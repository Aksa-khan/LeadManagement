package com.ind.utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.commons.compress.archivers.dump.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.JavascriptExecutor;

public class ExcelUtilities {
	
static String filePath="D:\\Salesforce Project\\IndusindBank\\IndusindBank\\datafiles\\TestData..xlsx";
	
	//static String filePath=System.getProperty("user.dir")+"/serviceapp/src/main/java/com/sca/resources/Testdata1..xlsx";

	
	static Workbook book;
	static Sheet sheet;
	static JavascriptExecutor js;

	public static Object[][] getExcel(String sheetName) {
		FileInputStream file = null;
		try {
			file = new FileInputStream(filePath);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			book = WorkbookFactory.create(file);
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		sheet = (Sheet) book.getSheet(sheetName);
		Object[][] data = new Object[(sheet).getLastRowNum()][(sheet).getRow(0).getLastCellNum()];
		 System.out.println(sheet.getLastRowNum() + "--------" +
		 sheet.getRow(0).getLastCellNum());
		for (int i = 0; i < (sheet).getLastRowNum(); i++) {
			for (int k = 0; k < (sheet).getRow(0).getLastCellNum(); k++) {
				data[i][k] = (sheet).getRow(i+1).getCell(k).toString();
				System.out.println(data[i][k]);
			}
		}
		return data;
	}
	
	

}