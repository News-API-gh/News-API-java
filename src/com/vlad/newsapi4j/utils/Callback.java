package com.vlad.newsapi4j.utils;

@FunctionalInterface
public interface Callback<T> {

	void invoke(T t);

}
