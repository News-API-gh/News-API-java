package com.vlad.newsapi4j.service;

/**
 * 
 * Enum representing the different news query types
 *
 */
public enum Endpoint {

	/**
	 * Info from <a href="https://newsapi.org/"> here </a> <br>
	 * <br>
	 * 
	 * All options passed are optional, however at least one must be included, in
	 * order to <br>
	 * query top-headlines
	 */
	TOP_HEADLINES,
	/**
	 * Info from <a href="https://newsapi.org/"> here </a> <br>
	 * <br>
	 * 
	 * No options are mandatory for a sources request
	 * 
	 */
	SOURCES,

	/**
	 * Info from <a href="https://newsapi.org/"> here </a> <br>
	 * <br>
	 * 
	 * To query everything, you must include at least a keyword, <br>
	 * source or domain
	 * 
	 */
	EVERYTHING

}
