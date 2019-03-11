package com.blank.Factory;

import com.blank.Architectures.Architecture;
import com.blank.Store.*;

public class StoreAbstractFactory implements AbstractFactory<Store>{

	private static final StoreAbstractFactory mStoreAbstractFactory = new StoreAbstractFactory();

	public static StoreAbstractFactory getInstance(){
		return mStoreAbstractFactory;
	}

	private StoreAbstractFactory(){}



	@Override
	public Store create(Architecture architecture) {
		Store store = null;

		switch (architecture){
			case BookStore:
				store = new BookStore();
				break;
			case GameStore:
				store = new GameStore();
				break;
			case ShoeStore:
				store = new ShoeStore();
				break;
			case GroceryStore:
				store = new GroceryStore();
				break;
		}

		return store;
	}
}
