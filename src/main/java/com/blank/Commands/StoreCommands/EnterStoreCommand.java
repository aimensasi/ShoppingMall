package com.blank.Commands.StoreCommands;

import com.blank.Commands.Command;
import com.blank.Store.Store;

public class EnterStoreCommand implements Command {

	private Store mStore;

	public EnterStoreCommand(Store store) {
		mStore = store;
	}

	@Override
	public void execute() {
		mStore.enter();
	}

	@Override
	public void undo() {
		mStore.exist();
	}
}
