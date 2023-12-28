package com.ilp.services;
import java.util.ArrayList;
import java.util.Scanner;
import com.ilp.entity.Account;
import com.ilp.entity.CurrentAccount;
import com.ilp.entity.Customer;
import com.ilp.entity.LoanAccount;
import com.ilp.entity.Product;
import com.ilp.entity.SavingsMaxAccount;
import com.ilp.entity.Service;
public class BankServices {
	public static ArrayList<Service> createService(ArrayList<Service> serviceList) {
		 
		char moreServiceChoice;
		do {
			Scanner scanner = new Scanner(System.in);
			System.out.println("Enter the Service Code : ");
			String serviceCode = scanner.nextLine();
			System.out.println("Enter the Service Name : ");
			String serviceName = scanner.nextLine();
			System.out.println("Enter the Service Rate : ");
			double serviceRate = scanner.nextDouble();
			Service service = new Service(serviceCode, serviceName, serviceRate);
			serviceList.add(service);
			System.out.println("Do you want to create more service : ");
			moreServiceChoice = scanner.next().charAt(0);
		} while (moreServiceChoice == 'y');
		return serviceList;
	}
	public static ArrayList<Product> createProduct(ArrayList<Service> serviceList, ArrayList<Product> productList) {
		// TODO Auto-generated method stub
		char moreProductChoice;
		do {
			Scanner scanner1 = new Scanner(System.in);
			System.out.println("Enter the Product Code : ");
			String productCode = scanner1.nextLine();
			System.out.println("Enter the Product Name : ");
			String productName = scanner1.nextLine();
			ArrayList<Service> productServiceList = new ArrayList<Service>();
			System.out.println("Service Code" + "	   " + "Servcie Name");
			for (Service service : serviceList) {
				System.out.println(service.getServiceCode() + "	" + service.getServiceName());
			}
			char repeatChoice;
			do {
				Scanner scanner2 = new Scanner(System.in);
				System.out.println("Enter the Service Code of the service you want to add : ");
				String serviceCode = scanner2.nextLine();
				for (Service service : serviceList) {
					if (service.getServiceCode().equalsIgnoreCase(serviceCode))
						productServiceList.add(service);
				}
				System.out.println("Do you want to add more services into the product : ");
				repeatChoice = scanner1.next().charAt(0);
			} while (repeatChoice == 'y');
			if (productName.equalsIgnoreCase("SavingsMax Account")) {
				SavingsMaxAccount product = new SavingsMaxAccount(productCode, productName, productServiceList);
				productList.add(product);
			} else if (productName.equalsIgnoreCase("Current Account")) {
				CurrentAccount product = new CurrentAccount(productCode, productName, productServiceList);
				productList.add(product);
			} else if (productName.equalsIgnoreCase("Loan Account")) {
				LoanAccount product = new LoanAccount(productCode, productName, productServiceList);
				productList.add(product);
			}
			System.out.println("Do you want to create more product : ");
			moreProductChoice = scanner1.next().charAt(0);
		} while (moreProductChoice == 'y');
		return productList;
	}
	public static ArrayList<Customer> createCustomer(ArrayList<Product> productList, ArrayList<Customer> customerList) {
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter the Customer Code : ");
		String customerCode = scanner.nextLine();
		System.out.println("Enter the Customer Name : ");
		String customerName = scanner.nextLine();
		Customer user = null;
		ArrayList<Account> accountList = new ArrayList<Account>();
		
		for(Customer customer : customerList) {
			if(customer.getCustomerCode().equalsIgnoreCase(customerCode)){
				user = customer;
			}
		}
		
		if(user == null){
			System.out.println("User does not exist.Create a new account.");
		}
		else {
			accountList = user.getAccountList();
		}
				
		char repeatChoice;
		do {
			Scanner scanner1 = new Scanner(System.in);
			accountList.add(createAccount(productList));
			System.out.println("Do you want to create more account : ");
			repeatChoice = scanner1.next().charAt(0);
		} while (repeatChoice == 'y');
		
		if(user == null){
			Customer customer = new Customer(customerCode, customerName, accountList);
			customerList.add(customer);
		}
		else {
			user.setAccountList(accountList);
		}

		return customerList;
	}
	private static Account createAccount(ArrayList<Product> productList) {
		Product accountProduct = null;
		String accountType = null;
		System.out.println("Product Code" + "	" + "Product Name");
		for (Product product : productList) {
			System.out.println(product.getProductCode() + "	" + product.getProductName());
		}
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter the Product Code of the product you want to add : ");
		String productCode = scanner.nextLine();
		for (Product product : productList) {
			if (product.getProductCode().equalsIgnoreCase(productCode)) {
				accountProduct = product;
				accountType = product.getProductName();
			}
		}
		Scanner scanner1 = new Scanner(System.in);
		System.out.println("Enter the Account No : ");
		String accountNo = scanner1.nextLine();
		double accountBalance;
		do {
			System.out.println("Enter the Account Balance : ");
			accountBalance = scanner1.nextDouble();
			if (accountType.equalsIgnoreCase("SavingsMax Account") && (accountBalance < 1000) ) {
				System.out.println("SavingsMax Account should have a minimum balace of 1000Rs.");
			}
		} while (accountBalance < 1000);
		Account account = new Account(accountNo, accountType, accountBalance, accountProduct);
		return account;

	}
	public static void manageAccount(ArrayList<Customer> customerList) {
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter Customer Code : ");
		Customer user = null;
		Account userAccount = null;
		String customerCode = scanner.nextLine();
		for (Customer customer : customerList) {
			if (customer.getCustomerCode().equalsIgnoreCase(customerCode)) {
				user = customer;
			}
		}
		ArrayList<Account> accountList = user.getAccountList();
		System.out.println(user.getCustomerName() + " has following accounts : ");
		for (Account account : accountList) {
			System.out.println(account.getAccountType() + "   " + account.getAccountNo());
		}
		System.out.println("Enter the account number to choose account : ");
		String accountNo = scanner.nextLine();
		for (Account account : accountList) {
			if (account.getAccountNo().equalsIgnoreCase(accountNo)) {
				userAccount = account;
			}
		}
		char repeatChoice;
		do {
			System.out.println("1.Deposit \n" + "2.Withdraw \n" + "3.Display Balace \n"
					+ "Enter the number corresponding to service required : ");
			int mainMenuChoice = scanner.nextInt();

			switch (mainMenuChoice) {

			case 1:
				TransactionServices.depositMoney(userAccount);
				break;
			case 2:
				TransactionServices.withdrawMoney(userAccount);
				break;
			case 3:
				TransactionServices.displayBalance(userAccount);
				break;
			}

			System.out.println("Do you want to continue : ");
			repeatChoice = scanner.next().charAt(0);

		} while (repeatChoice == 'y');
	}
	public static void displayCustomers(ArrayList<Customer> customerList) {
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter Customer Code : ");
		String customerCode = scanner.nextLine();
		Customer user = null;
		for (Customer customer : customerList) {
			if (customer.getCustomerCode().equalsIgnoreCase(customerCode)) {
				user = customer;
			}
		}
		System.out.println("Customer Name : " + user.getCustomerName() + "       " + "Customer Code : " + user.getCustomerCode());
		ArrayList<Account> accountList = user.getAccountList();		
		for (Account account : accountList) {
			System.out.println("Account Type        Account Number       Account Balance");
			System.out.println(
					account.getAccountType() + "   " + account.getAccountNo() + "  " + account.getAccountBalance());
			System.out.println("Services Provided : ");
			for (Service service : account.getProduct().getServiceList()) {
				System.out.print(service.getServiceName() + " ");
			}
			System.out.println(" ");
		}
	}
}
