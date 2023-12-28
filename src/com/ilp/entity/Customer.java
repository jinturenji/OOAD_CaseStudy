package com.ilp.entity;
import java.util.ArrayList;
public class Customer {
	private String customerCode;
	private String customerName;
	private ArrayList<Account> accountList = new ArrayList<Account>();
	public Customer(String customerCode, String customerName, ArrayList<Account> account) {
		super();
		this.customerCode = customerCode;
		this.customerName = customerName;
		this.accountList = account;
	}	
	public String getCustomerCode() {
		return customerCode;
	}
	public void setCustomerCode(String customerCode) {
		this.customerCode = customerCode;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public ArrayList<Account> getAccountList() {
		return accountList;
	}
	public void setAccountList(ArrayList<Account> account) {
		this.accountList = account;
	}
	@Override
	public String toString() {
		return "Customer [customerCode=" + customerCode + ", customerName=" + customerName + ", accountList="
				+ accountList + "]";
	}	
}
