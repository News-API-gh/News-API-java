package com.vlad.newsapi4j.client;

import com.vlad.newsapi4j.service.Endpoint;
import com.vlad.newsapi4j.service.IAsyncService;
import com.vlad.newsapi4j.service.IService;

public class NewsApiClient {

	private String apiKey;

	public NewsApiClient(String apiKey) {
		this.apiKey = apiKey;
	}

	/**
	 * creates an 'everything' request <br>
	 * 
	 * To query everything, you must include at least a keyword, <br>
	 * source or domain
	 * 
	 * via the appropriate method from <code> IService </code>
	 * 
	 * <br>
	 * <br>
	 * 
	 * For more information, visit NewsAPI's official website<b>
	 * <a href="https://newsapi.org/docs"> here </a> </b>
	 * 
	 * @return
	 */
	public IService newEverythingService() {
		return new StandartService().prepare(apiKey, Endpoint.EVERYTHING);
	}

	/**
	 * Creates a 'top-headlines' request <br>
	 * 
	 * All options passed are optional, however at least one must be included, in
	 * order to <br>
	 * query top-headlines
	 * 
	 * <br>
	 * <br>
	 * 
	 * For more information, visit NewsAPI's official website<b>
	 * <a href="https://newsapi.org/docs"> here </a> </b>
	 * 
	 * @return
	 */
	public IService newTopHeadlinesService() {
		return new StandartService().prepare(apiKey, Endpoint.TOP_HEADLINES);
	}

	/**
	 * Creates a 'sources' request <br>
	 * 
	 * No options are mandatory
	 * 
	 * <br>
	 * <br>
	 * 
	 * For more information, visit NewsAPI's official website<b>
	 * <a href="https://newsapi.org/docs"> here </a> </b>
	 * 
	 * @return
	 */
	public IService newSourcesService() {
		return new StandartService().prepare(apiKey, Endpoint.SOURCES);
	}

	/**
	 * Creates a custom service <br>
	 * In order to create a custom service, implement IService without overriding
	 * the default constructor!<br>
	 * then, simply pass it to the method :
	 * <code> NewsApiClient.newCustomService(new MyCustomService(), Endpoint.[Some endpoint]);</code>
	 * 
	 * <br>
	 * <br>
	 * 
	 * For more information, visit NewsAPI's official website<b>
	 * <a href="https://newsapi.org/docs"> here </a> </b>
	 * 
	 * @param service
	 * @param endpoint
	 * @return
	 */
	public IService newCustomService(IService service, Endpoint endpoint) {
		return service.prepare(apiKey, endpoint);
	}

	/**
	 * creates an 'everything' request that will run asynchronously <br>
	 * 
	 * To query everything, you must include at least a keyword, <br>
	 * source or domain
	 * 
	 * via the appropriate method from <code> IService </code>
	 * 
	 * <br>
	 * <br>
	 * 
	 * For more information, visit NewsAPI's official website<b>
	 * <a href="https://newsapi.org/docs"> here </a> </b>
	 * 
	 * @return
	 */
	public IAsyncService newEverythingServiceAsync() {
		return new StandartAsyncService().prepare(apiKey, Endpoint.EVERYTHING);
	}

	/**
	 * Creates a 'top-headlines' request that will run asynchronously <br>
	 * 
	 * All options passed are optional, however at least one must be included, in
	 * order to <br>
	 * query top-headlines
	 * 
	 * <br>
	 * <br>
	 * 
	 * For more information, visit NewsAPI's official website<b>
	 * <a href="https://newsapi.org/docs"> here </a> </b>
	 * 
	 * @return
	 */
	public IAsyncService newTopHeadlinesServiceAsync() {
		return new StandartAsyncService().prepare(apiKey, Endpoint.TOP_HEADLINES);
	}

	/**
	 * Creates a 'sources' request that will run asynchronously<br>
	 * 
	 * No options are mandatory <br>
	 * <br>
	 * 
	 * For more information, visit NewsAPI's official website<b>
	 * <a href="https://newsapi.org/docs"> here </a> </b>
	 * 
	 * @return
	 */
	public IAsyncService newSourcesServiceAsync() {
		return new StandartAsyncService().prepare(apiKey, Endpoint.SOURCES);
	}

	/**
	 * Creates a custom asynchronous service <br>
	 * In order to create a custom service, implement IAsyncService without
	 * overriding the default constructor!<br>
	 * then, simply pass it to the method :
	 * <code> NewsApiClient.newCustomAsyncService(new MyCustomService(), Endpoint.[Some endpoint]);</code>
	 * 
	 * <br>
	 * <br>
	 * 
	 * For more information, visit NewsAPI's official website<b>
	 * <a href="https://newsapi.org/docs"> here </a> </b>
	 * 
	 * @param service
	 * @param endpoint
	 * @return
	 */
	public IAsyncService newCustomAsyncService(IAsyncService service, Endpoint endpoint) {
		return service.prepare(apiKey, endpoint);
	}

	/**
	 * @return The API key used by this object
	 */
	public String getApiKey() {
		return new String(apiKey);
	}

}
