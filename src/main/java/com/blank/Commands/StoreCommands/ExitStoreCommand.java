package com.blank.Commands.StoreCommands;

import com.blank.Commands.Command;
import com.blank.Customers.Customer;
import com.blank.Store.Store;

public class ExitStoreCommand implements Command {

	private Store mStore;
	private Customer mCustomer;

	public ExitStoreCommand(Store store, Customer customer) {
		mStore = store;
		mCustomer = customer;
	}

	@Override
	public void execute() {
		mStore.exist(mCustomer);
	}

	@Override
	public void undo() {
		mStore.enter(mCustomer);
	}
}
