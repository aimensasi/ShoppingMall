package com.blank;


import com.blank.Store.Store;

import java.util.List;

public class Main {

	public static void main(String[] args) {
		Mall mall = Mall.getInstance();
		mall.setName("Heart beat Mall");
		mall.openFakeStores();
		System.out.println("Welcome to : " + mall.getName());
		System.out.println("____________________________________");
		System.out.println("Stores :: ");
		List<Store> stores = mall.getStores();
		for (Store store : stores){
			System.out.println(store.getName());
		}
	}
}
