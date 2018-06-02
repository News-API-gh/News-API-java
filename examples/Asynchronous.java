package com.vlad.newsapi4j.examples;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import com.vlad.newsapi4j.client.NewsApiClient;
import com.vlad.newsapi4j.model.Article;
import com.vlad.newsapi4j.service.SortBy;

public class Asynchronous {

	public static void main(String[] args) {
		NewsApiClient client = new NewsApiClient("99827975269246bc91c1780b77e7e008");
		/**
		 * Create the asynchronous request and supply with information
		 * Get the top headlines in the us today, sort them by date, show the first page(optional it's 1 by default)
		 */
		client.newTopHeadlinesServiceAsync()
		      .country("us")
		      .from(new Date())
		      .sortBy(SortBy.DATE)
		      .page(1)
		      /**
		       * send method gets called either with the result callback or with the error one
		       */
		      .send(result -> {
		    	  /**
		    	   * We are here, everything is fine
		    	   */
		    	  List<Article> articles = result.viewAsArticles().orElseGet(Collections::emptyList);
		    	  /**
		    	   * Get all articles whose author starts with the letter 'a', coz why not :D
		    	   */
		    	  articles.stream()
//		    	          .filter(article -> article.getAuthor().startsWith("A"))
		    	          .forEach(System.out::println);
		    	  
		      }, 	 /**
		    		   * We are here, something went wrong, just print the cause
		    		   */
		    		  Throwable::printStackTrace);
		/**
		 * Program flow continues freely 
		 */
		System.out.println("Yay, running concurrently");
	}
	
}
