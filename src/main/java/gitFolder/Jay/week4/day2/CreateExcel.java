package week4.day2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

public class CreateExcel {

	@Test
	public void writeIntoExcel() throws IOException{

		XSSFWorkbook wbook = new XSSFWorkbook();
		XSSFSheet sheet = wbook.createSheet("output");
		
		XSSFRow header = sheet.createRow(0);
		
		header.createCell(0).setCellValue("Serial No");
		header.createCell(1).setCellValue("TC Name");
		header.createCell(2).setCellValue("Status");

		for(int row = 1 ; row <= 4 ; row++){
			XSSFRow sheetRow = sheet.createRow(row) ;
			for(int col = 0 ; col <= 2 ; col++){
				
				if(row%2 != 0){
					sheetRow.createCell(col).setCellValue(""+row);
					sheetRow.createCell(2).setCellValue("PASS");
				}else{
					sheetRow.createCell(col).setCellValue(""+row);
					sheetRow.createCell(2).setCellValue("FAIL");
				}
			}
		}
		
		FileOutputStream fout = new FileOutputStream(new File("./data/TestOutput.xlsx"));
		wbook.write(fout);
		
		wbook.close();
		fout.close();
		
		
	}
}
