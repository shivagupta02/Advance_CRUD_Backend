package com.OnlyApp.OnlyApp.excelTemplate;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
@Service
public class ExcelTemplate {

	public byte[] excelTemplate() throws IOException{
		Workbook workbook=new XSSFWorkbook();
	    Sheet sheet=workbook.createSheet("empTemplate");

        // Create header row
        Row headerRow = sheet.createRow(0);
        
        String headers []= {"Name","Email","Mobile","Department"};
        
        for(int i=0;i<headers.length;i++)
        {
        	Cell cell=headerRow.createCell(i);
        	cell.setCellValue(headers[i]);
        }
        
        for (int i = 0; i < headers.length; i++) {
            sheet.autoSizeColumn(i);
        }

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        workbook.write(outputStream);
        workbook.close();

        return outputStream.toByteArray();
				
	}
}
