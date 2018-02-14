package com.google.newsapi.client;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.eclipse.jetty.client.HttpClient;
import org.eclipse.jetty.client.api.ContentResponse;
import org.eclipse.jetty.client.api.Request;
import org.eclipse.jetty.client.util.FutureResponseListener;
import org.eclipse.jetty.http.HttpMethod;
import org.eclipse.jetty.util.ssl.SslContextFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.google.newsapi.config.Constants;
import com.google.newsapi.models.Article;
import com.google.newsapi.models.ArticlesResult;
import com.google.newsapi.models.EverythingRequest;
import com.google.newsapi.models.TopHeadlinesRequest;

/**
 * 
 * 
 * @author Mit
 *
 */
public class NewsApiClient
{
	private static final Logger logger = LoggerFactory.getLogger(NewsApiClient.class);
	private static final String BASE_URL = "https://newsapi.org/v2/";
	private static final String AGENT = "News-API-java/0.1";
	private static SimpleDateFormat parser = new SimpleDateFormat("yyyy-MM-dd");
	private HttpClient httpClient;
	private String apiKey;

	private int maxContentSize = 1024 * 1024; // 1limit response size to 1M
	private int timeout = 5; // sec.

	public int getMaxContentSize()
	{
		return maxContentSize;
	}

	public void setMaxContentSize(int maxContentSize)
	{
		this.maxContentSize = maxContentSize;
	}

	public int getTimeout()
	{
		return timeout;
	}

	public void setTimeout(int timeout)
	{
		this.timeout = timeout;
	}

	/**
	 * 
	 * @param apiKey
	 *          News API key. You can create one for free at https://newsapi.org
	 */
	public NewsApiClient(String apiKey)
	{
		this.apiKey = apiKey;
		SslContextFactory sslContextFactory = new SslContextFactory();
		httpClient = new HttpClient(sslContextFactory);
		try
		{
			httpClient.start();
		}
		catch (Exception e)
		{
			logger.error("ex: ", e);
		}

	}

	/**
	 * use method for blocking calls
	 */

	public void setHttpClientBlocking()
	{
		httpClient.setConnectBlocking(true);
	}

	/**
	 * use method to clean stop using httpClient
	 */
	public void stopHttpClient()
	{
		try
		{
			httpClient.stop();
		}
		catch (Exception e)
		{
			logger.error("ex: ", e);
		}
	}

	private ArticlesResult makeRequest(String endpoint, String querystring)
	{
		ArticlesResult res = new ArticlesResult();
		String url = BASE_URL + endpoint + "?" + querystring;
		Request request = httpClient.newRequest(url);
		request.method(HttpMethod.GET);
		request.agent(AGENT);
		request.header("x-api-key", apiKey);
		FutureResponseListener listener = new FutureResponseListener(request, maxContentSize);
		request.send(listener);
		try
		{
			ContentResponse response = listener.get(timeout, TimeUnit.SECONDS);
			String content = response.getContentAsString();
			res = new Gson().fromJson(content, ArticlesResult.class);
		}
		catch (InterruptedException | ExecutionException | TimeoutException e)
		{
			logger.error("ex: ", e);
		}
		return res;
	}

	public ArticlesResult getTopHeadlines(TopHeadlinesRequest request)
	{
		StringBuffer queryParams = new StringBuffer();
		// build the querystring

		// q
		if ((request.q != null && !request.q.isEmpty()))
		{
			queryParams.append("q=" + request.q + "&");
		}

		// sources
		if (request.sources.size() > 0)
		{
			List<String> all = request.sources;
			StringBuffer buf = new StringBuffer();
			for (String str : all)
			{
				buf.append(str + ",");
			}
			String sourcesStr = new String(buf.substring(0, buf.length() - 1));
			queryParams.append("sources=" + sourcesStr + "&");
		}

		if (request.category != null)
		{
			queryParams.append("category=" + request.category.toString().toLowerCase() + "&");
		}

		if (request.language != null)
		{
			queryParams.append("language=" + request.language.toString().toLowerCase() + "&");
		}

		if (request.country != null)
		{
			queryParams.append("country=" + request.country.toString().toLowerCase() + "&");
		}

		// page
		if (request.page > 1)
		{
			queryParams.append("page=" + request.page + "&");
		}

		// page size
		if (request.pageSize > 0)
		{
			queryParams.append("pageSize=" + request.pageSize);
		}

		String querystring = new String(queryParams);
		if (querystring.endsWith("&"))
			querystring = querystring.substring(0, querystring.length() - 1);

		return makeRequest("top-headlines", querystring);
	}

	public ArticlesResult getEverything(EverythingRequest request)
	{
		StringBuffer queryParams = new StringBuffer();
		// build the querystring

		// q
		if ((request.q != null && !request.q.isEmpty()))
		{
			queryParams.append("q=" + request.q + "&");
		}

		// sources
		if (request.sources.size() > 0)
		{
			List<String> all = request.sources;
			StringBuffer buf = new StringBuffer();
			for (String str : all)
			{
				buf.append(str + ",");
			}
			String sourcesStr = new String(buf.substring(0, buf.length() - 1));
			queryParams.append("sources=" + sourcesStr + "&");
		}

		// domains
		if (request.domains.size() > 0)
		{
			List<String> all = request.domains;
			StringBuffer buf = new StringBuffer();
			for (String str : all)
			{
				buf.append(str + ",");
			}
			String domainsStr = new String(buf.substring(0, buf.length() - 1));
			queryParams.append("sources=" + domainsStr + "&");
		}
		// from
		if (request.from != null)
		{
			// queryParams.append("from=" + parser.format(request.from));
			queryParams.append("from=" + parser.format(request.from) + "&");
		}

		// to
		if (request.to != null)
		{
			queryParams.append("to=" + parser.format(request.to) + "&");
		}

		if (request.language != null)
		{
			queryParams.append("language=" + request.language.toString().toLowerCase() + "&");
		}

		// page
		if (request.page > 1)
		{
			queryParams.append("page=" + request.page + "&");
		}

		// page size
		if (request.pageSize > 0)
		{
			queryParams.append("pageSize=" + request.pageSize);
		}

		String querystring = new String(queryParams);
		if (querystring.endsWith("&"))
			querystring = querystring.substring(0, querystring.length() - 1);

		return makeRequest("everything", querystring);
	}

	/**
	 * api usage
	 * 
	 * @param args
	 * @throws Exception
	 */
	public static void main1(String[] args)
	{

		String apikey = args[0];
		NewsApiClient nac = new NewsApiClient(apikey);
		ArticlesResult res = nac.makeRequest("everything", "q=AAPL");
		List<Article> list = res.articles;
		int count = 0;
		for (Article article : list)
		{
			count++;
			System.out.println(count + ": " + article.toString());
		}
		// stop should be used by api users
		nac.stopHttpClient();

	}

	public static void main(String[] args)
	{
		String apikey = args[0];
		NewsApiClient nac = new NewsApiClient(apikey);
		EverythingRequest evr = new EverythingRequest();
		evr.language = Constants.LANGUAGES.EN;
		evr.q = "AAPL";
	  evr.sortBy = Constants.SORT_BYS.Popularity;
		String strDate = "2018-02-01";
		Date date = null;
		try
		{
			date = parser.parse(strDate);
		}
		catch (ParseException e)
		{
			logger.error("ex: ", e);
		}
		if (date != null)
			evr.from = date;
		ArticlesResult res = nac.getEverything(evr);
		List<Article> list = res.articles;
		if (list != null)
		{
			int count = 0;
			for (Article article : list)
			{
				count++;
				System.out.println(count + ": " + article.toString());
			}
		}
		nac.stopHttpClient();
	}
	
}
