package com.blank.Store;

public class ShoeStore extends Store {

	private static ShoeStore mShoeStore = new ShoeStore();

	public static ShoeStore getInstance(){ return mShoeStore; }

	private ShoeStore() { }
}
