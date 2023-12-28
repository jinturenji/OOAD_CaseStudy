package com.ilp.entity;
import java.util.ArrayList;
public class SavingsMaxAccount extends Product  {	
	public SavingsMaxAccount(String productCode, String productName, ArrayList<Service> serviceList) {
		super(productCode, productName, serviceList);
		// TODO Auto-generated constructor stub
	}
	private final double minimumBalance = 1000;
	public double getMinimumBalance() {
		return minimumBalance;
	}	
}
