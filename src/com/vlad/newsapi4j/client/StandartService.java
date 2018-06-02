package com.vlad.newsapi4j.client;

import java.util.Date;
import java.util.function.Supplier;

import org.json.JSONException;
import org.json.JSONObject;

import com.vlad.newsapi4j.access.APIConnection;
import com.vlad.newsapi4j.response.APIResponse;
import com.vlad.newsapi4j.service.Endpoint;
import com.vlad.newsapi4j.service.IService;
import com.vlad.newsapi4j.service.SortBy;
import com.vlad.newsapi4j.utils.Category;
import com.vlad.newsapi4j.utils.DateRange;
import com.vlad.newsapi4j.utils.LinkBuilder;
import com.vlad.newsapi4j.utils.NewsAPIException;
import com.vlad.newsapi4j.utils.ResponseStatus;

class StandartService implements IService {

	private String		apiKey;

	private Endpoint	endpoint;

	private LinkBuilder		link;

	StandartService() {

	}

	@Override
	public IService withSources(String... sources) {
		link.sources(sources);
		return this;
	}

	@Override
	public IService withDomains(String... domains) {
		link.domains(domains);
		return this;
	}

	@Override
	public IService withKeyword(String keyword) {
		link.keyword(keyword);
		return this;
	}

	@Override
	public IService language(String lang) {
		link.language(lang);
		return this;
	}

	@Override
	public IService sortBy(SortBy sortBy) {
		link.sortBy(sortBy);
		return this;
	}

	@Override
	public IService page(int page) {
		link.page(page);
		return this;
	}

	@Override
	public IService to(Date to) {
		link.to(to);
		return this;
	}

	@Override
	public IService from(Date date) {
		link.from(date);
		return this;
	}

	@Override
	public APIResponse send() throws NewsAPIException {
		/**
		 * Insert the API Key
		 */
		link.finish(apiKey);
		/**
		 * Connect to NewsAPI
		 */
		System.out.println("Sending API request to ["+link+"]...");
		String content = new APIConnection(link).getContents();
		int totalResults = APIResponse.INFORMATION_UNAVAILABLE;
		JSONObject obj = null;
		try {
			obj = new JSONObject(content);
			// System.out.println(obj);
		} catch (JSONException e1) {
			// throw new NewsAPIException("Something went wrong while parsing json");
		}

		ResponseStatus status = ResponseStatus.OK;
		/**
		 * Returns at least an empty string, not null
		 */
		if (content.contains("error") || content.equals("")) {
			status = ResponseStatus.ERROR;
			String message = "";
			try {
				message = obj.getString("message");
			} catch (JSONException e) {
				throw new NewsAPIException("Something went wrong while parsing json");
			}
			throw new NewsAPIException(message);
		}

		if (endpoint != Endpoint.SOURCES)
			try {
				totalResults = obj.getInt("totalResults");
			} catch (JSONException e) {
				throw new NewsAPIException("Something went wrong while parsing json");
			}
		return new ResponseImpl(endpoint, content, status, totalResults);
	}

	@Override
	public IService country(String country) {
		link.country(country);
		return this;
	}

	@Override
	public IService prepare(String apiKey, Endpoint endpoint) {
		this.apiKey = apiKey;
		this.endpoint = endpoint;
		this.link = new LinkBuilder(endpoint);
		return this;
	}

	@Override
	public IService withCategory(Category category) {
		link.category(category.toLink());
		return this;
	}

	@Override
	public IService dateRange(Supplier<DateRange> range) {
		link.from(range.get().getFrom());
		link.to(range.get().getTo());
		return this;
	}

	@Override
	public IService pageSize(int pageSize) {
		link.pageSize(pageSize);
		return this;
	}

}
