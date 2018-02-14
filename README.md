# News API client library for Java
News API is a simple HTTP REST API for searching and retrieving live articles from all over the web. It can help you answer questions like:

- What top stories is the NY Times running right now?
- What new articles were published about the next iPhone today?
- Has my company or product been mentioned or reviewed by any blogs recently?

You can search for articles with any combination of the following criteria:

- Keyword or phrase. Eg: find all articles containing the word 'Microsoft'.
- Date published. Eg: find all articles published yesterday.
- Source name. Eg: find all articles by 'TechCrunch'.
- Source domain name. Eg: find all articles published on nytimes.com.
- Language. Eg: find all articles written in English.

You can sort the results in the following orders:

- Date published
- Relevancy to search keyword
- Popularity of source

You need an API key to use the API - this is a unique key that identifies your requests. They're free for development, open-source, and non-commercial use. You can get one here: [https://newsapi.org](https://newsapi.org).


## Usage example

```java

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import com.google.newsapi.client.NewsApiClient;
import com.google.newsapi.config.Constants;
import com.google.newsapi.models.*;

public class MyApp
{

	private static SimpleDateFormat parser = new SimpleDateFormat("yyyy-MM-dd");
	
	public static void main(String[] args)
	{
		String apikey = args[0];
		NewsApiClient client = new NewsApiClient(apikey);
		EverythingRequest request = new EverythingRequest();
		request.language = Constants.LANGUAGES.EN;
		request.q = "AAPL";
	  	request.sortBy = Constants.SORT_BYS.Popularity;
		String strDate = "2018-02-01";
		Date date = null;
		try
		{
			date = parser.parse(strDate);
		}
		catch (ParseException e)
		{
			e.printStackTrace();;
		}
		if (date != null)
			request.from = date;
		ArticlesResult res = client.getEverything(request);
		List<Article> list = res.articles;
		if (list != null && list.size()>0)
		{
			for (Article article : list)
			{
				System.out.println(article.toString());
			}
		}
		client.stopHttpClient();
	}
}


```