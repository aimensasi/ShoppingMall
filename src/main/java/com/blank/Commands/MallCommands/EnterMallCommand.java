package com.blank.Commands.MallCommands;

import com.blank.Commands.Command;
import com.blank.Mall;

public class EnterMallCommand implements Command {

	private Mall mMall;


	public EnterMallCommand(Mall mall) {
		mMall = mall;
	}

	@Override
	public void execute() {
		mMall.enter();
	}

	@Override
	public void undo() {
		mMall.exit();
	}
}
