package com.blank.Store;

public class GroceryStore extends Store {

	private static GroceryStore mGroceryStore = new GroceryStore();

	public static GroceryStore getInstance(){ return mGroceryStore; }

	private GroceryStore() { }
}
