package com.blank.Commands.StoreCommands;

import com.blank.Commands.Command;
import com.blank.Store.Store;

public class ExitStoreCommand implements Command {

	private Store mStore;

	public ExitStoreCommand(Store store) {
		mStore = store;
	}

	@Override
	public void execute() {
		mStore.exist();
	}

	@Override
	public void undo() {
		mStore.enter();
	}
}
