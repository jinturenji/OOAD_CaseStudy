package com.ilp.utility;
import java.util.ArrayList;
import java.util.Scanner;
import com.ilp.entity.Account;
import com.ilp.entity.Customer;
import com.ilp.entity.Product;
import com.ilp.entity.Service;
import com.ilp.services.BankServices;
public class BankUtility {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(System.in);
		char repeatChoice;
		ArrayList<Service> serviceList = new ArrayList<Service>();
		ArrayList<Product> productList = new ArrayList<Product>();
		ArrayList<Customer> customerList = new ArrayList<Customer>();		
		System.out.println("************* WELCOME TO THE BANK*****************");		
		do {
		    int mainMenuChoice;		
			System.out.println("1.Create Service \n"
					+ "2.Create Product \n"
					+ "3.Create Customer \n"
					+ "4.Manage Accounts \n"
					+ "5.Display Customer \n"
					+ "6.Exit \n"
					+ "Enter the number corresponding to service required : ");	
			mainMenuChoice = scanner.nextInt();			
			switch(mainMenuChoice) {
			case 1:
				serviceList = BankServices.createService(serviceList);
				break;
			case 2:
				productList = BankServices.createProduct(serviceList,productList);
				break;
		    case 3:
		    	customerList = (BankServices.createCustomer(productList,customerList));
		        break;
			case 4:
				BankServices.manageAccount(customerList);
				break;
			case 5:
				BankServices.displayCustomers(customerList);
				break;
			case 6:
				System.exit(0);
			default :
				System.out.println("Invalid Choice!")	;
			    break;				
			}
			System.out.println("Do you want to continue : ");
			repeatChoice = scanner.next().charAt(0);			
		}while(repeatChoice == 'y');		
	}
}
