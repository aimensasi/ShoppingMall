package com.blank.Commands.StoreCommands;

import com.blank.Commands.Command;
import com.blank.Items.Item;
import com.blank.ShoppingCart.ShoppingCart;
import com.blank.Store.Store;

public class RemoveFromCartCommand implements Command {

	private Store mStore;
	private ShoppingCart mShoppingCart;
	private Item mItem;

	public RemoveFromCartCommand(Store store, ShoppingCart shoppingCart, Item item) {
		mStore = store;
		mShoppingCart = shoppingCart;
		mItem = item;
	}

	@Override
	public void execute() {
		mStore.removeFromCart(mShoppingCart, mItem);
	}

	@Override
	public void undo() {
		mStore.addToCart(mShoppingCart, mItem);
	}
}
