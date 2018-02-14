package com.google.newsapi.client;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.google.newsapi.config.Constants;
import com.google.newsapi.models.ArticlesResult;
import com.google.newsapi.models.EverythingRequest;
import com.google.newsapi.models.TopHeadlinesRequest;

public class ClientTestCase
{
	NewsApiClient newsclient = null;

	@Before
	public void setUp() throws Exception
	{
		String apikey = "xxxxxxxxxxxxxxxxxxxxxxxxxxxx";
		newsclient = new NewsApiClient(apikey);
	}

	@After
	public void tearDown() throws Exception
	{
		newsclient.stopHttpClient();
	}

	@Test
	public void testEverythingBasic()
	{
		EverythingRequest request = new EverythingRequest();
		request.q = "AAPL";
		ArticlesResult result = newsclient.getEverything(request);
		assertEquals(Constants.STATUSES.Ok, result.status.Ok);
		assertTrue(result.totalResults > 0);
		assertTrue(result.articles.size() > 0);
		assertNull(result.error);
	}

	@Test
	public void testEverythingComplex()
	{
		EverythingRequest request = new EverythingRequest();
		request.q = "AAPL";
		request.sortBy = Constants.SORT_BYS.PublishedAt;
		request.language = Constants.LANGUAGES.EN;
		ArticlesResult result = newsclient.getEverything(request);
		
		assertEquals(Constants.STATUSES.Ok, result.status.Ok);
		assertTrue(result.totalResults > 0);
		assertTrue(result.articles.size() > 0);
		assertNull(result.error);
	}


	@Test
	public void testTopHeadlinesBasic()
	{
		TopHeadlinesRequest request = new TopHeadlinesRequest();
		request.sources.add("techcrunch");
		ArticlesResult result = newsclient.getTopHeadlines(request);

		assertEquals(Constants.STATUSES.Ok, result.status.Ok);
		assertTrue(result.totalResults > 0);
		assertTrue(result.articles.size() > 0);
		assertNull(result.error);
	}

	@Test
	public void testTopHeadlinesWithoutParams()
	{
		TopHeadlinesRequest request = new TopHeadlinesRequest();
		ArticlesResult result = newsclient.getTopHeadlines(request);

		assertTrue(result.totalResults == 0);
		assertNull(result.articles);

	}

}
