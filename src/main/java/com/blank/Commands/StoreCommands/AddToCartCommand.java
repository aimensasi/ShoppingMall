package com.blank.Commands.StoreCommands;

import com.blank.Commands.Command;
import com.blank.Items.Item;
import com.blank.ShoppingCart.ShoppingCart;
import com.blank.Store.Store;

public class AddToCartCommand implements Command {

	private Store mStore;
	private ShoppingCart mShoppingCart;
	private Item mItem;


	public AddToCartCommand(Store store, ShoppingCart shoppingCart, Item item) {
		mStore = store;
		mShoppingCart = shoppingCart;
		mItem = item;
	}

	@Override
	public void execute() {
		mStore.addToCart(mShoppingCart, mItem);
	}

	@Override
	public void undo() {
		mStore.removeFromCart(mShoppingCart, mItem);
	}
}
