package com.devatechno.utility;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import com.devatechno.entity.InsurancePlan;
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

@Component
public class PdfGenerator {
	public void generatePdf(File file,List<InsurancePlan>list,HttpServletResponse response) throws Exception {
		Document document = new Document(PageSize.A4);
		FileOutputStream fos=new FileOutputStream(file);
		// Getting instance of PdfWriter
		PdfWriter.getInstance(document, response.getOutputStream());
        PdfWriter.getInstance(document,fos);
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
				document.add(table);
				document.close();
	}

}
