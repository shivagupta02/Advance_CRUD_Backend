package com.OnlyApp.OnlyApp.excelTemplate;

import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.OnlyApp.OnlyApp.Models.EmployeeEntity;
import com.OnlyApp.OnlyApp.repo.Repository;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;

@Service
public class DownloadData{
	
	@Autowired
	private Repository empRepo;
	public void dataToExcel(HttpServletResponse response) throws IOException
	{
		List<EmployeeEntity> empList=empRepo.findAll();
		XSSFWorkbook workbook= new XSSFWorkbook();
		Sheet sheet=workbook.createSheet("EmployeeList");
		Row row=sheet.createRow(0);
		
		CellStyle cellStyle=workbook.createCellStyle();
		cellStyle.setFillBackgroundColor(IndexedColors.YELLOW.getIndex());
		
		
		    
		row.createCell(0).setCellValue("Name");
		
		row.createCell(1).setCellValue("Email");
		row.createCell(2).setCellValue("City");
		row.createCell(3).setCellValue("Department");
		

		
		
		 int  dataRowIndex=1;
		 
		 for(EmployeeEntity emp:empList)
		 {
			 Row dataRow=sheet.createRow(dataRowIndex);
			 dataRow.createCell(0).setCellValue(emp.getEmpName());
			 dataRow.createCell(1).setCellValue(emp.getEmpEmail());
			 dataRow.createCell(2).setCellValue(emp.getEmpMobile());
			 dataRow.createCell(3).setCellValue(emp.getDepartment());
			 dataRowIndex++;
		 }
		 for (int i = 0; i < row.getLastCellNum(); i++) {
		        sheet.autoSizeColumn(i);
		    }
		 
		 
		 ServletOutputStream out=response.getOutputStream();
		 workbook.write(out);
		 workbook.close();
	}
	
	

}
