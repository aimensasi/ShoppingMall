package com.blank.Commands;

import java.util.Stack;

public class Invoker {

	private final Stack<Command> mUndoCommands = new Stack<>();


	private static Invoker mInvoker = new Invoker();

	public static Invoker getInstance(){ return mInvoker; }

	private Invoker(){ }


	public void execute(Command command){
		command.execute();
		mUndoCommands.push(command);
	}

	public void undo(){
		if (!mUndoCommands.empty()){
			mUndoCommands.pop().undo();
		}
	}
}
