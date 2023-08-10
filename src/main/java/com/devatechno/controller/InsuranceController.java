package com.devatechno.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.devatechno.entity.FormBinding;
import com.devatechno.entity.InsurancePlan;
import com.devatechno.service.InsuranceServiceInterface;

@Controller
@RequestMapping("/insure")
public class InsuranceController {
	@Autowired
	private InsuranceServiceInterface service;
	/*
 @GetMapping("/all")
	public ResponseEntity<List<InsurancePlan>>allList(){
		return new ResponseEntity<List<InsurancePlan>>(service.allList(), HttpStatus.OK);
	}
 @GetMapping("/getplanname/{name}")
   public ResponseEntity<List<InsurancePlan>>listByName(@PathVariable String name){
	  return new ResponseEntity<List<InsurancePlan>>(service.findByPlanName(name), HttpStatus.OK);  
   }
 @GetMapping("/getplanstatus/{name}")
   public ResponseEntity<List<InsurancePlan>>listByStatus(@PathVariable String name){
	   return new ResponseEntity<List<InsurancePlan>>(service.findByPlanStatus(name), HttpStatus.OK);
   }
   */
	@GetMapping("/")
   public String loadForm(Model model) {
		model.addAttribute("data", new FormBinding());
		init(model);
	   return"Index.html";
   }
	private void init(Model model) {
		//model.addAttribute("data", new FormBinding());
		model.addAttribute("planList", service.getPlanName());
		model.addAttribute("statusList", service.getPlanStatus());
	}
	@PostMapping("/search")
	public String searchResult(@ModelAttribute("data") FormBinding obj,Model model) {
		init(model);
		List<InsurancePlan>dataList=service.searchResult(obj);
		model.addAttribute("dataList", dataList);
		return "Index.html";
	}
	@GetMapping("/excel")
	public void excelExport(HttpServletResponse response) throws Exception {
		response.setContentType("application/octet-stream");
		response.addHeader("Content-Disposition", "attachment;filename=plans.xls");
		service.exportExcel(response);
	}
	@GetMapping("/pdf")
	public void pdfExport(HttpServletResponse response) throws Exception{
		response.setContentType("application/pdf");
		response.addHeader("Content-Disposition", "attachment;filename=plans.pdf");
		service.exportpdf(response);
	}
}
