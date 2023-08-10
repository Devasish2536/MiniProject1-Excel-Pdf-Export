package com.devatechno.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.devatechno.entity.InsurancePlan;

public interface InsuranceRepo extends JpaRepository<InsurancePlan, Integer> {
/*
	List<InsurancePlan> findByPlanName(String name);
	List<InsurancePlan> findByPlanStatus(String name);
	*/
	@Query("select distinct planName from InsurancePlan")
	List<String> getDistinctPlanName();
	@Query("select distinct planStatus from InsurancePlan")
	List<String> getDistinctPlanStatus();
     
}
