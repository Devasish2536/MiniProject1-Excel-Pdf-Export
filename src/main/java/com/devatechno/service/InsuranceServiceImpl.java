package com.devatechno.service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.devatechno.entity.FormBinding;
import com.devatechno.entity.InsurancePlan;
import com.devatechno.repository.InsuranceRepo;
import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.CMYKColor;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.lowagie.text.pdf.PdfWriter;

@Service
public class InsuranceServiceImpl implements InsuranceServiceInterface {
	@Autowired
	private InsuranceRepo repo;

	public List<InsurancePlan> allList() {
		List<InsurancePlan>planList=repo.findAll();
		return planList;
	}
/*
	public List<InsurancePlan> findByPlanName(String name) {
		return (List<InsurancePlan>) repo.findByPlanName(name);
	}

	public List<InsurancePlan> findByPlanStatus(String name) {
		return (List<InsurancePlan>) repo.findByPlanStatus(name);
	}
	*/

	public List<String> getPlanName() {
		return repo.getDistinctPlanName();
	}

	public List<String> getPlanStatus() {
		return repo.getDistinctPlanStatus();
	}

	public List<InsurancePlan> searchResult(FormBinding obj) {
		InsurancePlan plan=new InsurancePlan();
		if(null!=obj.getPlanName()&&""!=obj.getPlanName())
			plan.setPlanName(obj.getPlanName());
		if(null!=obj.getPlanStatus()&&""!=obj.getPlanStatus())
			plan.setPlanStatus(obj.getPlanStatus());
		
		if(null!=obj.getGender()&&""!=obj.getGender())
			plan.setGender(obj.getGender());
		
		if(null!=obj.getStartDate()&&""!=obj.getStartDate())
			plan.setStartDate(LocalDate.parse(obj.getStartDate()));
		
		if(null!=obj.getEndDate()&&""!=obj.getEndDate())
			plan.setEndDate(LocalDate.parse(obj.getEndDate()));
		
		List<InsurancePlan> list=repo.findAll(Example.of(plan));
		return list;
	}

	public void exportExcel(HttpServletResponse response) throws IOException {
		List<InsurancePlan>planList=repo.findAll();
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
		ServletOutputStream outputStream =response.getOutputStream();
		workbook.write(outputStream);
		workbook.close();
		outputStream.close();

	}

	@Override
	public void exportpdf(HttpServletResponse response)throws Exception {
		List<InsurancePlan>list=repo.findAll();
		// Creating the Object of Document
		Document document = new Document(PageSize.A4);

		// Getting instance of PdfWriter
		PdfWriter.getInstance(document, response.getOutputStream());

		// Opening the created document to modify it
		document.open();

		// Creating font
		// Setting font style and size
		Font fontTiltle = FontFactory.getFont(FontFactory.TIMES_ROMAN);
		fontTiltle.setSize(20);

		// Creating paragraph
		Paragraph paragraph = new Paragraph("Insurance Plan List", fontTiltle);

		// Aligning the paragraph in document
		paragraph.setAlignment(Paragraph.ALIGN_CENTER);

		// Adding the created paragraph in document
		document.add(paragraph);

		// Creating a table of 3 columns
		PdfPTable table = new PdfPTable(8);

		// Setting width of table, its columns and spacing
		table.setWidthPercentage(100f);
		table.setWidths(new int[] { 3, 3, 3 ,3 ,3 ,3 ,3 ,3});
		table.setSpacingBefore(5);

		// Create Table Cells for table header
		PdfPCell cell = new PdfPCell();

		// Setting the background color and padding
		cell.setBackgroundColor(CMYKColor.ORANGE);
		cell.setPadding(5);

		// Creating font
		// Setting font style and size
		Font font = FontFactory.getFont(FontFactory.TIMES_ROMAN);
		font.setColor(CMYKColor.WHITE);
		cell.setPhrase(new Phrase("ID", font));
		table.addCell(cell);
		cell.setPhrase(new Phrase("HolderName", font));
		table.addCell(cell);
		cell.setPhrase(new Phrase("Gender", font));
		table.addCell(cell);
		cell.setPhrase(new Phrase("PlanName", font));
		table.addCell(cell);
		cell.setPhrase(new Phrase("PlanStatus", font));
		table.addCell(cell);
		cell.setPhrase(new Phrase("StartDate", font));
		table.addCell(cell);
		cell.setPhrase(new Phrase("EndDate", font));
		table.addCell(cell);
		cell.setPhrase(new Phrase("Amount", font));
		table.addCell(cell);
		
		for (InsurancePlan planList :list ) {
			table.addCell(String.valueOf(planList.getPlanId()));
			table.addCell(planList.getHolderName());
			table.addCell(planList.getGender());
			table.addCell(planList.getPlanName());
			table.addCell(planList.getPlanStatus());
			table.addCell(String.valueOf(planList.getStartDate()));
			table.addCell(String.valueOf(planList.getEndDate()));
			if(planList.getBeneficiaryAmt()!=null)
			table.addCell(String.valueOf(planList.getBeneficiaryAmt()));
			else
				table.addCell("NA");
		}

		// Adding the created table to document
				document.add(table);

				// Closing the document
				document.close();

	}

	
}
