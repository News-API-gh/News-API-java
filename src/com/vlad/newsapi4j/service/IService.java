package com.vlad.newsapi4j.service;

import com.vlad.newsapi4j.response.APIResponse;
import com.vlad.newsapi4j.utils.Category;
import com.vlad.newsapi4j.utils.DateRange;
import com.vlad.newsapi4j.utils.NewsAPIException;

public interface IService {

	/**
	 * Set the lookup sources <br>
	 * such as abc-news, bbc, cnn, etc <br>
	 * <br>
	 * 
	 * For more information, visit NewsAPI's official website<b>
	 * <a href="https://newsapi.org/docs"> here </a> </b>
	 * 
	 * @param sources
	 * @return
	 */
	IService withSources(String... sources);

	/**
	 * Set the lookup domains <br>
	 * example : (techcrunch.com, bbc.co.uk) <br>
	 * <br>
	 * 
	 * For more information, visit NewsAPI's official website<b>
	 * <a href="https://newsapi.org/docs"> here </a> </b>
	 * 
	 * @param domains
	 * @return
	 */
	IService withDomains(String... domains);

	/**
	 * Set the searched keyword <br>
	 * <br>
	 * 
	 * For more information, visit NewsAPI's official website<b>
	 * <a href="https://newsapi.org/docs"> here </a> </b>
	 * 
	 * @param keyword
	 * @return
	 */
	IService withKeyword(String keyword);

	/**
	 * Filters the news given a category <br>
	 * <br>
	 * 
	 * For more information, visit NewsAPI's official website<b>
	 * <a href="https://newsapi.org/docs"> here </a> </b>
	 * 
	 * @param category
	 * @return
	 */
	IService withCategory(Category category);

	/**
	 * Sets the language <br>
	 * eg : find all articles written in English. <br>
	 * <br>
	 * 
	 * For more information, visit NewsAPI's official website<b>
	 * <a href="https://newsapi.org/docs"> here </a> </b>
	 * 
	 * @param lang
	 * @return
	 */
	IService language(String lang);

	/**
	 * Sort the results by <br>
	 * Date<br>
	 * Relevancy to search keyword<br>
	 * Popularity of source <br>
	 * <br>
	 * 
	 * For more information, visit NewsAPI's official website<b>
	 * <a href="https://newsapi.org/docs"> here </a> </b> <br>
	 * 
	 * @see {@link SortBy}
	 * @param sortBy
	 * @return
	 */
	IService sortBy(SortBy sortBy);

	/**
	 * Sets the country of origin <br>
	 * <br>
	 * 
	 * For more information, visit NewsAPI's official website<b>
	 * <a href="https://newsapi.org/docs"> here </a> </b>
	 * 
	 * @param country
	 * @return
	 */
	IService country(String country);

	/**
	 * Sets the lookup page <br>
	 * <br>
	 * 
	 * For more information, visit NewsAPI's official website<b>
	 * <a href="https://newsapi.org/docs"> here </a> </b>
	 * 
	 * @param page
	 * @return
	 */
	IService page(int page);
	
	/**
	 * Sets the lookup page size <br>
	 * I.E the amount of articles requested<br>
	 * 
	 * For more information, visit NewsAPI's official website<b>
	 * <a href="https://newsapi.org/docs"> here </a> </b>
	 * @param pageSize
	 * @return this
	 */
	IService pageSize(int pageSize);

	/**
	 * Sets the 'to' date <br>
	 * i.e every request after <b> to </b> will be ignored <br>
	 * <br>
	 * 
	 * For more information, visit NewsAPI's official website<b>
	 * <a href="https://newsapi.org/docs"> here </a> </b>
	 * 
	 * @param to
	 * @return
	 */
	IService to(java.util.Date to);

	/**
	 * Sets the 'from' date <br>
	 * i.e every request before <b> from </b> will be ignored <br>
	 * <br>
	 * 
	 * For more information, visit NewsAPI's official website<b>
	 * <a href="https://newsapi.org/docs"> here </a> </b>
	 * 
	 * @param from
	 * @return
	 */
	IService from(java.util.Date date);

	/**
	 * Sets the range of the news (both from and to)
	 * 
	 * <br>
	 * For more information, visit NewsAPI's official website<b>
	 * <a href="https://newsapi.org/docs"> here </a> </b>
	 * @param range
	 * @return
	 */
	IService dateRange(java.util.function.Supplier<DateRange> range);

	/**
	 * Sends the request to the newsapi server and returns the result <br>
	 * <br>
	 *
	 * 
	 * @return
	 * @throws NewsAPIException
	 *             if anything went wrong <br>
	 *             i.e wrong request, etc.
	 */
	APIResponse send() throws NewsAPIException;

	/**
	 * Supplies the <code> Service </code> object with the given api key and
	 * endpoint<br>
	 * <b>Not meant to be used by the user of the API </b>
	 * 
	 * @param apiKey
	 * @param endpoint
	 * @return
	 */
	IService prepare(String apiKey, Endpoint endpoint);

}
