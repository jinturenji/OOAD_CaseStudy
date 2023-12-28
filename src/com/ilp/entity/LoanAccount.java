package com.ilp.entity;
import java.util.ArrayList;
public class LoanAccount extends Product {	
    public LoanAccount(String productCode, String productName, ArrayList<Service> serviceList) {
		super(productCode, productName, serviceList);
		// TODO Auto-generated constructor stub
	}
	private final double chequeDepositCharge = 0.03; 
	public double getChequeDepositCharge() {
		return chequeDepositCharge;
	}
}
