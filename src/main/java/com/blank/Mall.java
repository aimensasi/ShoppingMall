package com.blank;

import com.blank.Architectures.Architecture;
import com.blank.Commands.Invoker;
import com.blank.Commands.MallCommands.ExitMallCommand;
import com.blank.Customers.Customer;
import com.blank.Factory.StoreAbstractFactory;
import com.blank.Store.Store;
import com.github.javafaker.Faker;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Mall {

	private String mName;
	private Customer mCurrentCustomer;
	private List<Store> mStores = new ArrayList<>();
	private List<Customer> mCustomers = new ArrayList<>();



	private static Mall ourInstance = new Mall();

	public static Mall getInstance() {
		return ourInstance;
	}

	private Mall() {
	}


	public void setName(String name) {
		mName = name;
	}

	public void setStores(List<Store> stores) {
		mStores = stores;
	}

	public void setCustomers(List<Customer> customers) {
		mCustomers = customers;
	}

	public String getName() {
		return mName;
	}

	public List<Customer> getCustomers() {
		return mCustomers;
	}

	public List<Store> getStores() {
		return mStores;
	}


	/**
	 * Open stores inside the mall for the sake of example
	 */
	public void openFakeStores(){
		StoreAbstractFactory storeAbstractFactory = new StoreAbstractFactory();
		Faker faker = new Faker();

		for (Architecture architecture : Architecture.values()){
			Store store = storeAbstractFactory.create(architecture);
			store.setName(store.getClass().getSimpleName() + " : " + faker.app().name());
			store.hostFakeCustomers();
			store.addFakeItemsToStore();
			mStores.add(store);
			List<Customer> customers = store.getCustomers();

			for (Customer customer : customers){
				mCustomers.add(customer);
			}
		}
	}

	public void enter(Customer customer){
		if (mStores.isEmpty()){
			openFakeStores();
		}

		mCurrentCustomer = customer;
		mCustomers.add(mCurrentCustomer);

		displayAvailableActions();
	}

	public void exit(Customer customer){
		mCustomers.remove(customer);
	}

	public void displayAvailableActions(){
		Invoker invoker = Invoker.getInstance();

		System.out.println();
		System.out.println("Available Actions : ");

		System.out.println("1 - Display List Of Stores");
		System.out.println("2 - Display Shopping Cart Items");
		System.out.println("3 - Enter A Store");
		System.out.println("4 - Checkout");
		System.out.println("0 - Exit Mall");

		Scanner scanner = new Scanner(System.in);

		if (!scanner.hasNext()){
			return;
		}

		int option = 0;

		switch (option){
			case 1:

				break;
			case 2:
				break;
			case 3:
				break;
			case 4:
				break;
			case 0:
				ExitMallCommand exitMallCommand = new ExitMallCommand(this, mCurrentCustomer);
				invoker.execute(exitMallCommand);
				break;
		}


	}


}
