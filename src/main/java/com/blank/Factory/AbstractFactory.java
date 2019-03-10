package com.blank.Factory;

import com.blank.Architectures.Architecture;

public interface AbstractFactory<T> {

	public T create(Architecture architecture);
}
