package com.blank.Commands.MallCommands;

import com.blank.Commands.Command;
import com.blank.Customers.Customer;
import com.blank.Mall;

public class ExitMallCommand implements Command {

	private Mall mMall;
	private Customer mCustomer;

	public ExitMallCommand(Mall mall, Customer customer) {
		mMall = mall;
		mCustomer = customer;
	}

	@Override
	public void execute() {
		mMall.exit(mCustomer);
	}

	@Override
	public void undo() {
		mMall.enter(mCustomer);
	}
}
