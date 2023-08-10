package com.devatechno.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name="Insuranceplan_tab")
@NoArgsConstructor
public class InsurancePlan {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
private Integer planId;
private String holderName;
private LocalDate startDate;
private LocalDate endDate;
private String planName;
private String gender;
private String planStatus;
private LocalDate terminatedDate;
private String terminationReason;
private Double beneficiaryAmt;
private String deniedReason;
}
