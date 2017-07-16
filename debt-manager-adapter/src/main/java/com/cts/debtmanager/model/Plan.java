package com.cts.debtmanager.model;

public class Plan {
	private String planName;
	private String planType;
	private int planInstallments = 3;
	
	public String getPlanName() {
		return planName;
	}
	public void setPlanName(String planName) {
		this.planName = planName;
	}
	public String getPlanType() {
		return planType;
	}
	public void setPlanType(String planType) {
		this.planType = planType;
	}
	public int getPlanInstallments() {
		return planInstallments;
	}
	public void setPlanInstallments(int planInstallments) {
		this.planInstallments = planInstallments;
	}
	
	@Override
	public String toString(){
		return "Plan:"+this.planName+" PlanType:"+this.planType+" Installments:"+this.planInstallments;
	}

}
