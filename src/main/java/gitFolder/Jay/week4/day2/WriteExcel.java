package week4.day2;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

public class WriteExcel {

	@Test
	public void writeIntoExcel(){
		
		try{
		XSSFWorkbook wbook = new XSSFWorkbook();
		XSSFSheet sheet = wbook.createSheet("output");
		
		sheet.getLastRowNum();
		XSSFRow[] row0 = {sheet.createRow(0),sheet.createRow(1),sheet.createRow(2),
						sheet.createRow(3),sheet.createRow(4)};
		
		row0[0].getLastCellNum();//createCell(0).setCellValue("Serial No");
		row0[0].createCell(1).setCellValue("TC Name");
		row0[0].createCell(2).setCellValue("Status");


		row0[1].createCell(1).setCellValue("CreateLead");
		row0[2].createCell(1).setCellValue("EditLead");
		row0[3].createCell(1).setCellValue("MergeLead");
		row0[4].createCell(1).setCellValue("DeleteLead");

		for(int i = 1 ; i <= 4 ; i++){
			if(i%2 != 0){
				row0[i].createCell(0).setCellValue(""+i);
				row0[i].createCell(2).setCellValue("PASS");
			}else{
				row0[i].createCell(0).setCellValue(""+i);
				row0[i].createCell(2).setCellValue("FAIL");
			}
		}

		FileOutputStream fout = new FileOutputStream(new File("./data/TestOutput.xlsx"));
		wbook.write(fout);
		
		wbook.close();
		fout.close();
	}catch(IOException e){
		e.printStackTrace();
	}
		
	}
}
