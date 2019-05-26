package com.virtusa.pankaj;

public interface Convertor<T, U> {

	T execute(U input);
}
