package com.blank;

import com.blank.Architectures.Architecture;
import com.blank.Customers.Customer;
import com.blank.Factory.StoreAbstractFactory;
import com.blank.Store.Store;
import com.github.javafaker.Faker;

import java.util.ArrayList;
import java.util.List;

public class Mall {

	private String mName;
	private List<Store> mStores = new ArrayList<>();
	private List<Customer> mCustomers = new ArrayList<>();


	private static Mall ourInstance = new Mall();

	public static Mall getInstance() {
		return ourInstance;
	}

	private Mall() {
	}

	public String getName() {
		return mName;
	}

	public void setName(String name) {
		mName = name;
	}

	public List<Store> getStores() {
		return mStores;
	}

	public void setStores(List<Store> stores) {
		mStores = stores;
	}

	public List<Customer> getCustomers() {
		return mCustomers;
	}

	public void setCustomers(List<Customer> customers) {
		mCustomers = customers;
	}


	/**
	 * Open stores inside the mall for the sake of example
	 */
	public void openFakeStores(){
		StoreAbstractFactory storeAbstractFactory = new StoreAbstractFactory();
		Faker faker = new Faker();

		for (Architecture architecture : Architecture.values()){
			Store store = storeAbstractFactory.create(architecture);
			store.setName(store.getClass().getName() + " : " + faker.app().name());
			store.hostFakeCustomers();
			mStores.add(store);
			List<Customer> customers = store.getCustomers();

			for (Customer customer : customers){
				mCustomers.add(customer);
			}
		}
	}

	public void enter(){
			
	}

	public void exit(){

	}
}
