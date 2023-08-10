package com.devatechno.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.devatechno.entity.FormBinding;
import com.devatechno.entity.InsurancePlan;

public interface InsuranceServiceInterface {
	List<InsurancePlan> allList();
	/*
	List<InsurancePlan> findByPlanName(String name);
	List<InsurancePlan> findByPlanStatus(String name);
	*/
	public List<String>getPlanName();
	public List<String>getPlanStatus();
    public List<InsurancePlan>searchResult(FormBinding obj);
    public void exportExcel(HttpServletResponse response) throws IOException;
    public void exportpdf(HttpServletResponse response) throws Exception; 
}
