package com.vlad.newsapi4j.service;

/**
 * Indicates the response sort order
 */
public enum SortBy {

    RELEVANCE,

	POPULARITY,

	DATE;
	
	public String toLink() {
		return this.name().toLowerCase();
	}
	

}
