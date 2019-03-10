package com.blank.Store;

public class BookStore extends Store {

	private static BookStore mBookStore = new BookStore();

	public static BookStore getInstance(){ return mBookStore; }

	private BookStore(){ }

}
