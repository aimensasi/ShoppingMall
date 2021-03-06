package com.blank.Customers;

import com.blank.Items.Item;
import com.blank.Observers.Observer;
import com.blank.ShoppingCart.ShoppingCart;
import com.blank.Store.Store;

import java.util.concurrent.atomic.AtomicInteger;

public class Customer implements Observer {

	private static final AtomicInteger mIdCount = new AtomicInteger(0);

	private Integer mId;
	private String mName;
	private ShoppingCart mShoppingCart;
	private Store mStore;


	public Customer() {
		mId = mIdCount.incrementAndGet();
	}

	public void setName(String name) {
		mName = name;
	}

	public void setShoppingCart(ShoppingCart shoppingCart) {
		mShoppingCart = shoppingCart;
	}

	public void setStore(Store store) {
		mStore = store;
	}

	public Integer getId() {
		return mId;
	}

	public String getName() {
		return mName;
	}

	public ShoppingCart getShoppingCart() {
		return mShoppingCart;
	}

	public Store getStore() {
		return mStore;
	}

	@Override
	public void _notify(Object o) {
		Item item = (Item) o;

		System.out.println("Notification :: Item Has been Added " + item.getName());
	}
}
