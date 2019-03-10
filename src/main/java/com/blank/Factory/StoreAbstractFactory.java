package com.blank.Factory;

import com.blank.Architectures.Architecture;
import com.blank.Store.*;

public class StoreAbstractFactory implements AbstractFactory<Store>{


	@Override
	public Store create(Architecture architecture) {
		Store store = null;

		switch (architecture){
			case BookStore:
				store = BookStore.getInstance();
				break;
			case GameStore:
				store = GameStore.getInstance();
				break;
			case ShoeStore:
				store = ShoeStore.getInstance();
				break;
			case GroceryStore:
				store = GroceryStore.getInstance();
				break;
		}

		return store;
	}
}
