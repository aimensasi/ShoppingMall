package com.blank.Items;

import java.util.concurrent.atomic.AtomicInteger;

public class Item {

	private static final AtomicInteger mIdCount = new AtomicInteger(0);

	private Integer mId;
	private String mName;
	private Integer mStoreId;
	private Double mPrice;


	public Item(){
		mId = mIdCount.incrementAndGet();
	}

	public Integer getId() {
		return mId;
	}


	public String getName() {
		return mName;
	}

	public void setName(String name) {
		mName = name;
	}

	public Integer getStoreId() {
		return mStoreId;
	}

	public void setStoreId(Integer storeId) {
		mStoreId = storeId;
	}

	public Double getPrice() {
		return mPrice;
	}

	public void setPrice(Double price) {
		mPrice = price;
	}
}
