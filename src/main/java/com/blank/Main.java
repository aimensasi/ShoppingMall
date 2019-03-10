package com.blank;


import com.blank.Commands.Invoker;
import com.blank.Commands.MallCommands.EnterMallCommand;
import com.blank.Customers.Customer;
import com.blank.ShoppingCart.ShoppingCart;
import com.blank.Store.Store;

import java.util.List;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
//		Initiate Mall
		Mall mall = Mall.getInstance();
		mall.setName("Heart beat Mall");

//		Welcome Customer To the Mall
		System.out.println("Welcome to : " + mall.getName());


//		Ask Customer For his/her Details
		System.out.println("Please provide us with your information to enter ");
		System.out.println();
		System.out.print("Enter Your Name : ");

		Scanner scanner = new Scanner(System.in);

		if (!scanner.hasNext()){
			return;
		}

//		Create a customer and then Enter the store
		Customer customer = new Customer();
		customer.setName(scanner.nextLine());
		customer.setShoppingCart(new ShoppingCart());

		EnterMallCommand enterMallCommand = new EnterMallCommand(mall, customer);
		Invoker invoker = Invoker.getInstance();
		invoker.execute(enterMallCommand);


//		mall.openFakeStores();
//		System.out.println("Welcome to : " + mall.getName());
//		System.out.println("____________________________________");
//		System.out.println("Stores :: ");
//		System.out.printf("%1s  %-15s   %-7s   %-6s%n","ID", "NAME", "Number Of Customers", "Number Of Items");
//		System.out.println("---------------------------------------------------");
//		List<Store> stores = mall.getStores();
//		for (Store store : stores){
//			System.out.printf("%1s  %-15s   %-7d   %-6d%n", store.getId(), store.getName(), store.getCustomers().size(), store.getItems().size());
//		}
	}
}
