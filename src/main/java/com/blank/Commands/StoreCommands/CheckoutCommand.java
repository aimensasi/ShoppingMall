package com.blank.Commands.StoreCommands;

import com.blank.Commands.Command;
import com.blank.Items.Item;
import com.blank.ShoppingCart.ShoppingCart;
import com.blank.Store.Store;

import java.util.List;

public class CheckoutCommand implements Command {

	private Store mStore;
	private ShoppingCart mShoppingCart;
	private List<Item> mItemList;


	public CheckoutCommand(Store store, ShoppingCart shoppingCart) {
		mStore = store;
		mShoppingCart = shoppingCart;
		mItemList = mShoppingCart.getItems();
	}

	@Override
	public void execute() {
		mStore.checkout(mShoppingCart);
	}

	@Override
	public void undo() {
		mStore.undoCheckout(mShoppingCart, mItemList);
	}
}
