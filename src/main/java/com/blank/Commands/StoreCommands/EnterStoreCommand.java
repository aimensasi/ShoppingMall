package com.blank.Commands.StoreCommands;

import com.blank.Commands.Command;
import com.blank.Customers.Customer;
import com.blank.Store.Store;

public class EnterStoreCommand implements Command {

	private Store mStore;
	private Customer mCustomer;

	public EnterStoreCommand(Store store, Customer customer) {
		mStore = store;
		mCustomer = customer;
	}

	@Override
	public void execute() {
		mStore.enter(mCustomer);
	}

	@Override
	public void undo() {
		mStore.exist(mCustomer);
	}
}
