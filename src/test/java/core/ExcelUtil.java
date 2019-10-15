package core;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtil {

	public static ArrayList<HashMap<String, String>> readExcel(String filePath, String sheetName, String testcaseID)
			throws IOException {
		FileInputStream inputStream = new FileInputStream(new File(filePath));
		ArrayList<HashMap<String, String>> dataArr = new ArrayList<HashMap<String, String>>();
		String fileExtension = filePath.substring(filePath.indexOf("."));
		System.out.println("-------------------------" + fileExtension);
		Workbook wb = null;
		if (fileExtension.equalsIgnoreCase(".xls")) {
			wb = new HSSFWorkbook(inputStream);
		} else {

			wb = new XSSFWorkbook(inputStream);
			System.out.println("-------------------------");
		}

		Sheet sheet = wb.getSheet(sheetName);
		DataFormatter formatter = new DataFormatter(); 

		int rowCount = sheet.getLastRowNum() - sheet.getFirstRowNum();
		System.out.println("Row count " + rowCount);
		Row row = sheet.getRow(0);
		int columnCount = row.getLastCellNum();
		System.out.println("Column count " + columnCount);
		for (int i = 1; i <= rowCount; i++) {
			HashMap<String, String> map = new HashMap<String, String>();
			for (int j = 0; j < columnCount; j++) {
				String columnHeader = formatter.formatCellValue(sheet.getRow(0).getCell(j));
				String columnValue = formatter.formatCellValue(sheet.getRow(i).getCell(j)); 
				System.out.println(columnHeader + " " + columnValue + " || ");
				map.put(columnHeader, columnValue);
			}
			System.out.println();
			dataArr.add(map);
		}
		return dataArr;
	}

	public static void main(String[] args) throws IOException {
		ArrayList<HashMap<String, String>> dataArr = readExcel("D:\\Framework\\Appium\\Demo1\\test-data\\data.xlsx",
				"Data User", "1");
		for (HashMap<String, String> hashMap : dataArr) {
			System.out.println(hashMap.get("Extension") + " ");
		}
	}
}
