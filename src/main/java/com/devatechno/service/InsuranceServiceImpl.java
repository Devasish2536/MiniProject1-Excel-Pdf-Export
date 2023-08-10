package com.devatechno.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import com.devatechno.entity.FormBinding;
import com.devatechno.entity.InsurancePlan;
import com.devatechno.repository.InsuranceRepo;
import com.devatechno.utility.ExcelGenerator;
import com.devatechno.utility.MailSenderClass;
import com.devatechno.utility.PdfGenerator;


@Service
public class InsuranceServiceImpl implements InsuranceServiceInterface {
	@Autowired
	private InsuranceRepo repo;
	@Autowired
	private ExcelGenerator excelgenerator;
	@Autowired
	private PdfGenerator pdfgenerator;
	@Autowired
	private MailSenderClass sender;

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
		File file=new File("plans.xls");
		 excelgenerator.generateExcel(response, file, planList);
		 String subject="Insurance Plan List";
		 String body="<h3> Plan List</h3>";
		 String to="miku02behera@gmail.com";
		 sender.sendMail(subject,body, to, file);
		 file.delete();
		 
			}

	@Override
	public void exportpdf(HttpServletResponse response)throws Exception {
		List<InsurancePlan>list=repo.findAll();
		File file=new File("plans.pdf");
		pdfgenerator.generatePdf(file, list, response);
		 String subject="Insurance Plan List";
		 String body="<h3> Plan List</h3>";
		 String to="miku02behera@gmail.com";
		 sender.sendMail(subject,body, to, file);
		 file.delete();
		
			}

	
}
