package com.vlad.newsapi4j.client;

import java.util.Date;
import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

import com.vlad.newsapi4j.response.APIResponse;
import com.vlad.newsapi4j.service.Endpoint;
import com.vlad.newsapi4j.service.IAsyncService;
import com.vlad.newsapi4j.service.SortBy;
import com.vlad.newsapi4j.utils.Callback;
import com.vlad.newsapi4j.utils.Category;
import com.vlad.newsapi4j.utils.DateRange;
import com.vlad.newsapi4j.utils.NewsAPIException;

class StandartAsyncService implements IAsyncService {

	private StandartService stdService;

	public StandartAsyncService() {
		stdService = new StandartService();
	}

	@Override
	public IAsyncService withSources(String... sources) {
		stdService.withSources(sources);
		return this;
	}

	@Override
	public IAsyncService withDomains(String... domains) {
		stdService.withDomains(domains);
		return this;
	}

	@Override
	public IAsyncService withKeyword(String keyword) {
		stdService.withKeyword(keyword);
		return this;
	}

	@Override
	public IAsyncService language(String lang) {
		stdService.language(lang);
		return this;
	}

	@Override
	public IAsyncService sortBy(SortBy sortBy) {
		stdService.sortBy(sortBy);
		return this;
	}

	@Override
	public IAsyncService country(String country) {
		stdService.country(country);
		return this;
	}

	@Override
	public IAsyncService page(int page) {
		stdService.page(page);
		return this;
	}

	@Override
	public IAsyncService to(Date to) {
		stdService.to(to);
		return this;
	}

	@Override
	public IAsyncService from(Date date) {
		stdService.from(date);
		return this;
	}

	@Override
	public void send(Callback<APIResponse> result, Callback<Throwable> onError) {
		new Thread(() -> {
			try {
				result.invoke(stdService.send());
			} catch (NewsAPIException exception) {
				onError.invoke(exception);
			}
		}).start();
	}

	@Override
	public IAsyncService prepare(String apiKey, Endpoint endpoint) {
		stdService.prepare(apiKey, endpoint);
		return this;
	}

	@Override
	public IAsyncService withCategory(Category category) {
		stdService.withCategory(category);
		return this;
	}

	@Override
	public IAsyncService dateRange(Supplier<DateRange> range) {
		stdService.dateRange(range);
		return this;
	}



}
