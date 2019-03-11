package com.blank;

import com.blank.Architectures.Architecture;
import com.blank.Commands.Invoker;
import com.blank.Commands.MallCommands.ExitMallCommand;
import com.blank.Commands.StoreCommands.EnterStoreCommand;
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
		Scanner scanner = new Scanner(System.in);

		while (true){

			displayActions(invoker);

			int option = scanner.nextInt();

			switch (option){
				case 1:
					displayStores();
					break;
				case 2:
					onEnterStoreOptionSelected(scanner, invoker);
					break;
				case 99:
					invoker.undo();
					break;
				case 0:
					ExitMallCommand exitMallCommand = new ExitMallCommand(this, mCurrentCustomer);
					invoker.execute(exitMallCommand);
					option = -1;
					break;
			}

			if (option == -1){
				break;
			}
		}
	}


	public void displayStores(){
		System.out.printf("%1s  %-15s   %-7s   %-6s%n","ID", "NAME", "Number Of Customers", "Number Of Items");
		System.out.println("---------------------------------------------------");
		for (Store store : mStores){
			store.display();
		}
	}

	public void displayActions(Invoker invoker){
		System.out.println();
		System.out.println("Choose an action : ");

		System.out.println("1 - Display List Of Stores");
		System.out.println("2 - Enter A Store");
		if (invoker.hasUndo()){
			System.out.println("99 - To Undo Previous Command");
		}
		System.out.println("0 - Exit Mall");
	}

	public void onEnterStoreOptionSelected(Scanner scanner, Invoker invoker){
		System.out.println("Enter Store ID : ");
		displayStores();

		int storeId = scanner.nextInt();
		Store selectedStore = null;

		for (Store store : mStores){
			if (store.getId() == storeId){
				selectedStore = store;
			}
		}

		if (selectedStore != null){
			EnterStoreCommand enterStoreCommand = new EnterStoreCommand(selectedStore, mCurrentCustomer);
			invoker.execute(enterStoreCommand);
		}else{
			System.out.println("Sorry Store Is Not Found.");
		}
	}

}
