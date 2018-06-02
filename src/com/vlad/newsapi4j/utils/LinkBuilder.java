package com.vlad.newsapi4j.utils;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.stream.Collectors;

import com.vlad.newsapi4j.service.Endpoint;
import com.vlad.newsapi4j.service.SortBy;

/**
 * Utility class used for building the request link for NewsAPI
 *
 */
public class LinkBuilder {

	public static final String	NEWSAPI_LINK_V2	= "https://newsapi.org/v2/";

	private StringBuilder		current			= new StringBuilder(NEWSAPI_LINK_V2);
	private boolean				firstAddition	= true;

	private Endpoint			endPoint;

	public LinkBuilder(Endpoint ePoint) {
		current.append(
				ePoint == Endpoint.EVERYTHING    ? "everything" 
						:
		        ePoint == Endpoint.TOP_HEADLINES ? "top-headlines" : "sources");
		current.append('?');
		this.endPoint = ePoint;
	}

	public LinkBuilder sources(String... sources) {
		addTag("sources");
		current.append(join(sources));
		return this;
	}

	public LinkBuilder domains(String... domains) {
		addTag("domains");
		current.append(join(domains));
		return this;
	}

	public LinkBuilder category(String category) {
		if(endPoint == Endpoint.EVERYTHING)
			throw new NewsAPIRuntimeException("The category param is not currently supported on the /everything endpoint");
		addTag("category");
		current.append(category);
		return this;
	}
	
	public LinkBuilder pageSize(int pageSize) {
		addTag("pageSize");
		current.append(pageSize);
		return this;
	}

	private void addTag(String tag) {
		if (firstAddition) {
			current.append(tag).append('=');
			firstAddition = false;
		} else
			current.append('&').append(tag).append('=');
	}

	public LinkBuilder language(String lang) {
		addTag("language");
		current.append(lang);
		return this;
	}

	private CharSequence join(String... strings) {
		return Arrays.stream(strings)
				     .collect(Collectors.joining(","));
	}

	@Override
	public String toString() {
		return current.toString();
	}

	public LinkBuilder keyword(String string) {
		addTag("q");
		current.append(string);
		return this;
	}

	public LinkBuilder finish(String apiKey) {
		addTag("apiKey");
		current.append(apiKey);
		return this;
	}

	public LinkBuilder country(String country) {
		if (endPoint == Endpoint.EVERYTHING)
			throw new NewsAPIRuntimeException("Cannot use an everything request with a country");
		addTag("country");
		current.append(country);
		return this;
	}

	public LinkBuilder sortBy(SortBy sortBy) {
		addTag("sortBy");
		current.append(sortBy.toLink());
		return this;
	}

	public LinkBuilder page(int page) {
		addTag("page");
		current.append(page);
		return this;
	}

	/*
	 * YY-MM-DD
	 */
	public LinkBuilder from(Date from) {
		Calendar c = Calendar.getInstance();
		c.setTime(from);
		int dd = c.get(Calendar.DAY_OF_MONTH);
		int yy = c.get(Calendar.YEAR);
		int mm = c.get(Calendar.MONTH) + 1;
		addTag("from");
		current.append(yy).append('-').append((String.valueOf(mm).length() != 2 ? "0" : "") + mm).append('-').append(
		        (String.valueOf(dd).length() != 2 ? "0" : "") + dd);
		return this;
	}

	public LinkBuilder to(Date to) {
		Calendar c = Calendar.getInstance();
		c.setTime(to);
		int dd = c.get(Calendar.DAY_OF_MONTH);
		int yy = c.get(Calendar.YEAR);
		int mm = c.get(Calendar.MONTH) + 1;
		addTag("to");
		current.append(yy).append('-').append((String.valueOf(mm).length() != 2 ? "0" : "") + mm).append('-').append(
		        (String.valueOf(dd).length() != 2 ? "0" : "") + dd);
		return this;
	}

}
