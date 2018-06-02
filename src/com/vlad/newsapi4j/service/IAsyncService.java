package com.vlad.newsapi4j.service;

import com.vlad.newsapi4j.response.APIResponse;
import com.vlad.newsapi4j.utils.Callback;
import com.vlad.newsapi4j.utils.Category;
import com.vlad.newsapi4j.utils.DateRange;

public interface IAsyncService {
	/**
	 * Set the lookup sources <br>
	 * such as abc-news, bbc, cnn, etc
	 * 
	 * <br>
	 * <br>
	 * 
	 * For more information, visit NewsAPI's official website<b>
	 * <a href="https://newsapi.org/docs"> here </a> </b>
	 * 
	 * @param sources
	 * @return
	 */
	IAsyncService withSources(String... sources);

	/**
	 * Set the lookup domains <br>
	 * example : (techcrunch.com, bbc.co.uk)
	 * 
	 * <br>
	 * <br>
	 * 
	 * For more information, visit NewsAPI's official website<b>
	 * <a href="https://newsapi.org/docs"> here </a> </b>
	 * 
	 * @param domains
	 * @return
	 */
	IAsyncService withDomains(String... domains);

	/**
	 * Set the searched keyword
	 * 
	 * <br>
	 * <br>
	 * 
	 * For more information, visit NewsAPI's official website<b>
	 * <a href="https://newsapi.org/docs"> here </a> </b>
	 * 
	 * @param keyword
	 * @return
	 */
	IAsyncService withKeyword(String keyword);

	/**
	 * Filters the news given a category
	 * 
	 * <br>
	 * <br>
	 * 
	 * For more information, visit NewsAPI's official website<b>
	 * <a href="https://newsapi.org/docs"> here </a> </b>
	 * 
	 * @param category
	 * @return
	 */
	IAsyncService withCategory(Category category);

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
	IAsyncService language(String lang);

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
	IAsyncService sortBy(SortBy sortBy);

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
	IAsyncService country(String country);

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
	IAsyncService page(int page);

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
	IAsyncService to(java.util.Date to);

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
	IAsyncService from(java.util.Date date);
	
	/**
	 * Sets the range of the news (both from and to)
	 * 
	 * <br>
	 * For more information, visit NewsAPI's official website<b>
	 * <a href="https://newsapi.org/docs"> here </a> </b>
	 * @param range
	 * @return
	 */
	IAsyncService dateRange(java.util.function.Supplier<DateRange> range);


	/**
	 * Supplies the <code> AsyncService </code> object with the given api key and
	 * endpoint<br>
	 * <b>Not meant to be used by the user of the API </b>
	 * 
	 * @param apiKey
	 * @param endpoint
	 * @return
	 */
	IAsyncService prepare(String apiKey, Endpoint endpoint);

	/**
	 * Sends the request to the newsapi server <b> on a new thread </b> <br>
	 * returns either the result in a callback or the error that occured
	 * 
	 * @param result
	 * @param onError
	 */
	void send(Callback<APIResponse> result, Callback<Throwable> onError);

}
