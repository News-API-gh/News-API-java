package com.vlad.newsapi4j.response;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.json.JSONObject;

import com.vlad.newsapi4j.model.Article;
import com.vlad.newsapi4j.model.NewsSource;
import com.vlad.newsapi4j.utils.ResponseStatus;

/**
 * Returned by querying newsapi
 */
public interface APIResponse {

	public enum Type {
		SOURCES,

		ARTICLES
	}

	/**
	 * Returned whenever the 'totalResults' info is unavailable
	 */
	int INFORMATION_UNAVAILABLE = -0xff;

	/**
	 * @return the json object obtained;
	 */
	JSONObject getJSON();

	/**
	 * Returns the status of the request
	 */
	ResponseStatus getStatus();

	/**
	 * Returns the type of information the object is holding
	 * 
	 * @return
	 */
	APIResponse.Type getResponseType();

	/**
	 * @return either the total amount of elements collected by the query or
	 *         <code> IAPIRespnse.INFORMATION_UNAVAILABLE </code>
	 */
	int totalResults();

	/**
	 * @return a list view of the jsonArray containing all the items queried <br>
	 * 
	 */
	java.util.List<JSONObject> getData();

	default Optional<List<Article>> viewAsArticles() {
		if (getResponseType() != Type.ARTICLES)
			return Optional.empty();
		return Optional.of(getData().stream().map(Article::new).collect(Collectors.toList()));
	}

	default Optional<List<NewsSource>> viewAsNewsSources() {
		if (getResponseType() != Type.SOURCES)
			return Optional.empty();
		return Optional.of(getData().stream().map(NewsSource::new).collect(Collectors.toList()));
	}

}
