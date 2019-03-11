package com.blank.ShoppingCart;

import com.blank.Items.Item;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {

	private List<Item> mItems = new ArrayList<>();


	public void addItem(Item item){
		if (!mItems.contains(item)){
			mItems.add(item);
		}
	}

	public void removeItem(Item item){
		if (mItems.contains(item)){
			mItems.remove(item);
		}
	}

	public double getTotalPrice(){
		double price = 0.0;
		for (Item item : mItems){
			price += item.getPrice();
		}

		return price;
	}

	public List<Item> getItems() {
		return mItems;
	}

	public void setItems(List<Item> items) {
		mItems = items;
	}

	public void displayItems(){
		System.out.printf("%1s  %-15s   %-7s   %-6s%n","ID", "Shop ID", "Name", "Price");
		System.out.println("---------------------------------------------------");

		if (mItems.isEmpty()){
			System.out.println("Your Shopping Cart is still empty, visit one of our stores and start shopping");
			return;
		}

		for (Item item: mItems){
			item.display();
		}
	}

	public void emptyCart() {
		mItems = new ArrayList<>();
	}
}
