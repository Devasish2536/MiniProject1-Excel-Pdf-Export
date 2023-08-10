package com.devatechno.runner;

import java.time.LocalDate;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.devatechno.entity.InsurancePlan;
import com.devatechno.repository.InsuranceRepo;

@Component
public class InsuranceRunner implements ApplicationRunner {
	@Autowired
	private InsuranceRepo repo;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		repo.deleteAll();
		InsurancePlan p1 = new InsurancePlan();
		p1.setPlanName("CASH");
		p1.setPlanStatus("APROVED");
		p1.setStartDate(LocalDate.now().minusMonths(5));
		p1.setEndDate(LocalDate.now());
		p1.setGender("MALE");
		p1.setBeneficiaryAmt(25000d);
		p1.setHolderName("AAA");

		InsurancePlan p2 = new InsurancePlan();
		p2.setPlanName("FOOD");
		p2.setPlanStatus("APROVED");
		p2.setStartDate(LocalDate.now().minusMonths(4));
		p2.setEndDate(LocalDate.now());
		p2.setGender("MALE");
		p2.setBeneficiaryAmt(25000d);
		p2.setHolderName("BBB");

		InsurancePlan p3 = new InsurancePlan();
		p3.setPlanName("MEDICAL");
		p3.setPlanStatus("APROVED");
		p3.setStartDate(LocalDate.now().minusMonths(3));
		p3.setEndDate(LocalDate.now());
		p3.setGender("FEMALE");
		p3.setBeneficiaryAmt(25000d);
		p3.setHolderName("CCC");

		InsurancePlan p4 = new InsurancePlan();
		p4.setPlanName("EMPLOYEMENT");
		p4.setPlanStatus("APROVED");
		p4.setStartDate(LocalDate.now().minusMonths(2));
		p4.setEndDate(LocalDate.now());
		p4.setGender("MALE");
		p4.setBeneficiaryAmt(25000d);
		p4.setHolderName("DDD");
		
		InsurancePlan p5 = new InsurancePlan();
		p5.setPlanName("CASH");
		p5.setPlanStatus("DENIED");
		p5.setStartDate(LocalDate.now().minusMonths(1));
		p5.setEndDate(LocalDate.now());
		p5.setGender("MALE");
		//p5.setBeneficiaryAmt(25000d);
		p5.setDeniedReason("Invalid User");
		p5.setHolderName("EEE");

		InsurancePlan p6 = new InsurancePlan();
		p6.setPlanName("FOOD");
		p6.setPlanStatus("DENIED");
		p6.setStartDate(LocalDate.now().minusMonths(5));
		p6.setEndDate(LocalDate.now());
		p6.setGender("MALE");
		//p6.setBeneficiaryAmt(25000d);
		p6.setDeniedReason("Criminal Record");
		p6.setHolderName("FFF");

		InsurancePlan p7 = new InsurancePlan();
		p7.setPlanName("MEDICAL");
		p7.setPlanStatus("DENIED");
		p7.setStartDate(LocalDate.now().minusMonths(4));
		p7.setEndDate(LocalDate.now());
		p7.setGender("FEMALE");
		//p7.setBeneficiaryAmt(25000d);
		p7.setDeniedReason("Improper Documentation");
		p7.setHolderName("GGG");

		InsurancePlan p8 = new InsurancePlan();
		p8.setPlanName("EMPLOYEMENT");
		p8.setPlanStatus("DENIED");
		p8.setStartDate(LocalDate.now().minusMonths(6));
		p8.setEndDate(LocalDate.now());
		p8.setGender("MALE");
		//p8.setBeneficiaryAmt(25000d);
		p8.setDeniedReason("Not Applicable");
		p8.setHolderName("HHH");

		InsurancePlan p9 = new InsurancePlan();
		p9.setPlanName("CASH");
		p9.setPlanStatus("TERMINATED");
		p9.setStartDate(LocalDate.now().minusMonths(8));
		p9.setEndDate(LocalDate.now());
		p9.setGender("MALE");
		//p9.setBeneficiaryAmt(25000d);
		p9.setTerminationReason("High Salary");
		p9.setTerminatedDate(LocalDate.now().minusMonths(3));
		p9.setHolderName("III");

		InsurancePlan p10 = new InsurancePlan();
		p10.setPlanName("FOOD");
		p10.setPlanStatus("TERMINATED");
		p10.setStartDate(LocalDate.now().minusMonths(5));
		p10.setEndDate(LocalDate.now());
		p10.setGender("MALE");
		//p10.setBeneficiaryAmt(25000d);
		p10.setTerminationReason("NO proper Document");
		p10.setTerminatedDate(LocalDate.now().minusMonths(2));
		p10.setHolderName("JJJ");

		InsurancePlan p11 = new InsurancePlan();
		p11.setPlanName("MEDICAL");
		p11.setPlanStatus("TERMINATED");
		p11.setStartDate(LocalDate.now().minusMonths(1));
		p11.setEndDate(LocalDate.now());
		p11.setGender("FEMALE");
		//p11.setBeneficiaryAmt(25000d);
		p11.setTerminationReason("High Income");
		p11.setTerminatedDate(LocalDate.now().minusMonths(2));
		p11.setHolderName("KKK");

		InsurancePlan p12 = new InsurancePlan();
		p12.setPlanName("EMPLOYEMENT");
		p12.setPlanStatus("TERMINATED");
		p12.setStartDate(LocalDate.now().minusMonths(7));
		p12.setEndDate(LocalDate.now());
		p12.setGender("MALE");
		//p12.setBeneficiaryAmt(25000d);
		p12.setTerminationReason("Incompelte Document");
		p12.setTerminatedDate(LocalDate.now().minusMonths(1));
		p12.setHolderName("LLL");
		
       repo.saveAll(Arrays.asList(p1,p2,p3,p4,p5,p6,p7,p8,p9,p10,p11,p12));
	}

}
