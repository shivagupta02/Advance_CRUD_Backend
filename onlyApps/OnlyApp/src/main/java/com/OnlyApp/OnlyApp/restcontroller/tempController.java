package com.OnlyApp.OnlyApp.restcontroller;

import java.io.IOException;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.OnlyApp.OnlyApp.Models.EmployeeEntity;
import com.OnlyApp.OnlyApp.excelTemplate.DownloadData;
import com.OnlyApp.OnlyApp.excelTemplate.ExcelTemplate;

import jakarta.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200/")
public class tempController {
	@Autowired
	private ExcelTemplate excelTemplateService;
	@Autowired
	private DownloadData downloadData;
	
	 @GetMapping("/template")
	    public ResponseEntity<byte[]> downloadTemplate() throws IOException {
	        byte[] excelContent = excelTemplateService.excelTemplate();

	        return ResponseEntity.ok()
	                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=template.xlsx")
	                .contentType(MediaType.APPLICATION_OCTET_STREAM)
	                .body(excelContent);
	    }
	    
	    @GetMapping("/downloadList")
	      public void downloadList(HttpServletResponse response) throws IOException
	      {
	    	response.setContentType("application/octet-stream");
	    	String headerKey= "Content-Disposition";
	    	String headerValue="attachment;filename=EmployeeList.xls";
	    	response.setHeader(headerKey, headerValue);
	    	downloadData.dataToExcel(response);
	    }
	    

}
