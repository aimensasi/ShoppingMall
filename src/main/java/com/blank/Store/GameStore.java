package com.blank.Store;

public class GameStore extends Store{

	private static GameStore mGameStore = new GameStore();

	public static GameStore getInstance(){ return mGameStore; }

	private GameStore() { }

}
