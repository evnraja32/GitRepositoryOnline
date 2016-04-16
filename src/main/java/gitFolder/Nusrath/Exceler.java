package gitFolder.Nusrath;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Exceler {

	public static void main(String[] args) {
		System.out.println("Hello World");
		
		Exceler obj = new Exceler();
		
		int rowIndex = 0;
		int cellnum = 1;
		
		obj.getCellAt(obj.getRowAt(obj.prepareExcelSheet("fileName", "sheetName"), rowIndex), cellnum);

	}


	//====== Handling Excel Files =======

	public XSSFSheet prepareExcelSheet(String fileName,String sheetName){
		XSSFWorkbook workbook = null;
		XSSFSheet sheet = null;

		try{
			FileInputStream fis = new FileInputStream(new File("./data/"+fileName+".xlsx"));
			workbook = new XSSFWorkbook(fis);
			sheet = workbook.getSheet(sheetName);

		}catch(IOException e){
			printException(e, "Error in excel book");
		}
		return sheet;
	}

	public XSSFRow getRowAt(XSSFSheet excelSheet,int rowIndex){
		return excelSheet.getRow(rowIndex);
	}

	public XSSFCell getCellAt(XSSFRow currentRow,int cellnum){
		return currentRow.getCell(cellnum);
	}

	//====== Handling Excel Files =======


	public XSSFCell readExcel(String filename, String sheetnm, int rowIndex, int colnum)
	{
		XSSFRow currentRow = null;
		FileInputStream data;
		try {
			data = new FileInputStream(new File("./data/"+filename+".xlsx"));
			XSSFWorkbook workbook = new XSSFWorkbook(data);
			XSSFSheet sheet = workbook.getSheet(sheetnm);
			currentRow = sheet.getRow(rowIndex);
		}
		catch (IOException e) {

			e.printStackTrace();
		}return currentRow.getCell(colnum);
	}


	public  void  printException(Throwable e, String errorMessage) {

		System.out.println(errorMessage);
		e.printStackTrace();

	}


}
