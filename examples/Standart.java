package com.vlad.newsapi4j.examples;

import java.util.Collections;
import java.util.List;

import com.vlad.newsapi4j.client.NewsApiClient;
import com.vlad.newsapi4j.model.NewsSource;
import com.vlad.newsapi4j.response.APIResponse;
import com.vlad.newsapi4j.service.SortBy;
import com.vlad.newsapi4j.utils.Category;
import com.vlad.newsapi4j.utils.NewsAPIException;

public class Standart {

	public static void main(String[] args) throws NewsAPIException {
		NewsApiClient client = new NewsApiClient("99827975269246bc91c1780b77e7e008");
		/**
		 * Wait and receive response from server
		 */
		APIResponse response = client.newSourcesService()
				                     .withKeyword("bulgaria")
				                     .sortBy(SortBy.POPULARITY)
				                     .send();
		
		System.out.println("Response type : "+response.getResponseType());
		System.out.println("Response total results : "+response.totalResults());
		
		/**
		 * viewAs methods return an optional, since it's uncertain what the data type inside APIResponse.getData is
		 */
		List<NewsSource> newsSources = response.viewAsNewsSources().orElseGet(Collections::emptyList);
		
		/**
		 * Let's say we wanted to be extra cool and printed only the news sources that specialize in sports 
		 */
		newsSources.stream()
		           .filter(nSource -> nSource.getCategory() == Category.Sports)
		           .forEach(System.out::println);
		

	}
	

}
