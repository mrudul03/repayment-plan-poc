package com.cts.debtmanager.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cts.debtmanager.model.Payment;
import com.cts.debtmanager.model.Plan;

public class RepaymentPlanService {
	static final Logger log = LoggerFactory.getLogger(RepaymentPlanService.class);
	
	public Payment processPlan(Plan plan){
		log.info("Methood Invoked Received:"+ plan );
		
		Payment payment = new Payment();
		payment.setPayment("Success");
		return payment;
	}
}
