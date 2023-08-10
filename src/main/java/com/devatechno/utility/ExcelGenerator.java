package com.devatechno.utility;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Component;

import com.devatechno.entity.InsurancePlan;
@Component
public class ExcelGenerator {

public void generateExcel(HttpServletResponse response,File file,List<InsurancePlan>planList) throws IOException {
	
	HSSFWorkbook workbook=new HSSFWorkbook();
	
	HSSFSheet sheet=workbook.createSheet("InsurancePlan");
	HSSFRow headerRow=sheet.createRow(0);
	headerRow.createCell(0).setCellValue("PlanID");
	headerRow.createCell(1).setCellValue("HolderName");
	headerRow.createCell(2).setCellValue("Gender");
	headerRow.createCell(3).setCellValue("PlanName");
	headerRow.createCell(4).setCellValue("PlanStatus");
	headerRow.createCell(5).setCellValue("StartDate");
	headerRow.createCell(6).setCellValue("EndDate");
	headerRow.createCell(7).setCellValue("Amount");
	int rowIndex=1;
	for(InsurancePlan list:planList) {
	HSSFRow dataRow=	sheet.createRow(rowIndex);
	dataRow.createCell(0).setCellValue(list.getPlanId());
	dataRow.createCell(1).setCellValue(list.getHolderName());
	dataRow.createCell(2).setCellValue(list.getGender());
	dataRow.createCell(3).setCellValue(list.getPlanName());
	dataRow.createCell(4).setCellValue(list.getPlanStatus());
	dataRow.createCell(5).setCellValue(list.getStartDate()+"");
	dataRow.createCell(6).setCellValue(list.getEndDate()+"");
	if(list.getBeneficiaryAmt()==null) {
	dataRow.createCell(7).setCellValue(0d);
	}else {
		dataRow.createCell(7).setCellValue(list.getBeneficiaryAmt());
			
	}
	rowIndex ++;
	}
	FileOutputStream fos=new FileOutputStream(file);
	workbook.write(file);
	workbook.close();
	fos.close();
	
	ServletOutputStream outputStream =response.getOutputStream();
	workbook.write(outputStream);
	workbook.close();
	outputStream.close();


}
}
