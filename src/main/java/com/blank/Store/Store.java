package com.blank.Store;

import com.blank.Customers.Customer;
import com.blank.Items.Item;
import com.blank.ShoppingCart.ShoppingCart;
import com.github.javafaker.Faker;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class Store{

	private static final AtomicInteger mIdCount = new AtomicInteger(0);

	private Integer mId;
	private String mName;
	private List<Customer> mCustomers = new ArrayList<>();
	private List<Item> mItems = new ArrayList<>();
	private Faker mFake;

	public Store() {
		mId = mIdCount.incrementAndGet();
		mFake = new Faker();
	}

	public void setName(String name) {
		mName = name;
	}

	public Integer getId() {
		return mId;
	}

	public String getName() {
		return mName;
	}


	public List<Customer> getCustomers() {
		return mCustomers;
	}

	public List<Item> getItems(){
		return mItems;
	}

	/**
	 * Create fake users inside each store for the sake of example
	 */
	public void hostFakeCustomers(){

		for (int i = 0; i <= 10; i++){

			Customer customer = new Customer();
			customer.setName(mFake.name().fullName());
			customer.setStore(this);
			customer.setShoppingCart(new ShoppingCart());
			mCustomers.add(customer);
		}
	}

	/**
	 * Create fake items inside each store for the sake of example
	 */
	public void addFakeItemsToStore(){

		for (int i = 0; i <= 10; i++){
			Item item = new Item();
			item.setName(mFake.pokemon().name());
			item.setPrice(mFake.number().randomDouble(2, 10, 129));
			item.setStoreId(mId);
			mItems.add(item);
		}
	}

	public void enter(){

	}

	public void exist(){

	}

	public void addToCart(ShoppingCart shoppingCart, Item item){

	}

	public void removeFromCart(ShoppingCart shoppingCart, Item item){

	}

	@Override
	public String toString() {
		return mId + " | " + mName + " | " + mCustomers.size() + " | " + mItems.size();
	}
}
