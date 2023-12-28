package com.ilp.services;
import java.util.Scanner;
import com.ilp.entity.Account;
import com.ilp.entity.CurrentAccount;
import com.ilp.entity.LoanAccount;
import com.ilp.entity.SavingsMaxAccount;
import com.ilp.entity.Service;
public class TransactionServices {
	public static void depositMoney(Account userAccount) {
		// TODO Auto-generated method stub
		if((userAccount.getProduct() instanceof SavingsMaxAccount) || (userAccount.getProduct() instanceof CurrentAccount)) {
			Scanner scanner = new Scanner(System.in);
			System.out.println("Enter the amount to deposit : ");
			double amount = scanner.nextDouble();
			userAccount.setAccountBalance(userAccount.getAccountBalance() + amount);
			System.out.println("Transaction Succesfull");			
		}
		else if(userAccount.getProduct() instanceof LoanAccount){			
			Scanner scanner = new Scanner(System.in);			
			for(Service service : userAccount.getProduct().getServiceList()) {
				System.out.println(service.getServiceName());
			}
			System.out.println("Choose Transaction Service Type : ");
			String serviceName= scanner.nextLine();
						
			System.out.println("Enter the amount to deposit : ");
			double amount = scanner.nextDouble();

			if(serviceName.equalsIgnoreCase("Cheque Deposit")) {				
	     		LoanAccount loanAccount = (LoanAccount)userAccount.getProduct();
				amount *= ( 1 - loanAccount.getChequeDepositCharge() );
				userAccount.setAccountBalance(userAccount.getAccountBalance() + amount);
			}
			System.out.println("Transaction Succesfull");
		}		
	}
	public static void withdrawMoney(Account userAccount) {
		// TODO Auto-generated method stub
		if(userAccount.getProduct() instanceof SavingsMaxAccount) {
			Scanner scanner = new Scanner(System.in);
			System.out.println("Enter the amount to withdraw : ");
			double amount = scanner.nextDouble();			
			SavingsMaxAccount savingsMaxAccount=(SavingsMaxAccount)userAccount.getProduct();
			if((userAccount.getAccountBalance() - amount) < savingsMaxAccount.getMinimumBalance() ) {			
				System.out.println("Transaction failed.");
				System.out.println("Minimum Balance should be maintained.");
			}
			else {
				userAccount.setAccountBalance(userAccount.getAccountBalance() - amount);
				System.out.println("Transaction Succesfull");
			}			
		}
		else if(userAccount.getProduct() instanceof CurrentAccount){
			Scanner scanner1 = new Scanner(System.in);
			System.out.println("Enter the amount to withdraw : ");
			double amount = scanner1.nextDouble();
			if((userAccount.getAccountBalance() - amount) < 0){
				System.out.println("Transaction failed.");
			}
			else {
				userAccount.setAccountBalance(userAccount.getAccountBalance() - amount);
				System.out.println("Transaction Succesfull");
			}			
		}
		else if(userAccount.getProduct() instanceof LoanAccount){
			System.out.println("Transaction Failed");
			System.out.println("You cannote withdraw money from loan account!");
		}
	}
	public static void displayBalance(Account userAccount) {
		// TODO Auto-generated method stub
		System.out.println("Account No" + "    " + "Account Type" + "    " + "Account Balance");	
	    System.out.println(userAccount.getAccountNo() + "    " + userAccount.getAccountType() + "    " + "     " + userAccount.getAccountBalance());		
	}	
}
