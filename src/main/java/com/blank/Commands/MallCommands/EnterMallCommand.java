package com.blank.Commands.MallCommands;

import com.blank.Commands.Command;
import com.blank.Customers.Customer;
import com.blank.Mall;

public class EnterMallCommand implements Command {

	private Mall mMall;
	private Customer mCustomer;


	public EnterMallCommand(Mall mall, Customer customer) {
		mMall = mall;
		mCustomer = customer;
	}

	@Override
	public void execute() {
		mMall.enter(mCustomer);
	}

	@Override
	public void undo() {
		mMall.exit(mCustomer);
	}
}
