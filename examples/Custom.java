package com.vlad.newsapi4j.examples;

import java.util.Date;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import org.json.JSONException;
import org.json.JSONObject;

import com.vlad.newsapi4j.access.APIConnection;
import com.vlad.newsapi4j.client.NewsApiClient;
import com.vlad.newsapi4j.client.Response;
import com.vlad.newsapi4j.model.Article;
import com.vlad.newsapi4j.response.APIResponse;
import com.vlad.newsapi4j.service.Endpoint;
import com.vlad.newsapi4j.service.IAsyncService;
import com.vlad.newsapi4j.utils.Callback;
import com.vlad.newsapi4j.utils.Category;
import com.vlad.newsapi4j.utils.DateRange;
import com.vlad.newsapi4j.utils.LinkBuilder;
import com.vlad.newsapi4j.utils.NewsAPIException;
import com.vlad.newsapi4j.utils.ResponseStatus;

public class Custom {

	public static void main(String[] args) {
		NewsApiClient client = new NewsApiClient("99827975269246bc91c1780b77e7e008");
		client.newCustomAsyncService(new MyCustomAsyncService(), Endpoint.EVERYTHING)
		      .withKeyword("Youtube")
		      .language("en")
		      /**
		       * I HAD to break convention (All caps methods and all that), just look at how beautiful this looks
		       */
		      .dateRange(DateRange::LAST_7_DAYS)
		      .send(result -> {
			        System.out.println("Result matches : " + result.totalResults());
			        /**
			         * Find the first article that has an author ?
			         */
			        Article a = result.viewAsArticles()
			        		          .get()
			        		          .stream()
			        		          .filter(article -> article.getAuthor() != null)
			        		          .findFirst()
			        		          .orElse(null);
			        System.out.println(a);
		        }, Throwable::printStackTrace);

	}

	private static class MyCustomAsyncService implements IAsyncService {

		private String		apiKey;

		private Endpoint	endpoint;

		private LinkBuilder	link;

		public MyCustomAsyncService() {

		}

		@Override
		public IAsyncService withSources(String... sources) {
			link.sources(sources);
			return this;
		}

		@Override
		public IAsyncService withDomains(String... domains) {
			link.domains(domains);
			return this;
		}

		@Override
		public IAsyncService withKeyword(String keyword) {
			link.keyword(keyword);
			return this;
		}

		@Override
		public IAsyncService withCategory(Category category) {
			link.category(category.toLink());
			return this;
		}

		@Override
		public IAsyncService language(String lang) {
			link.language(lang);
			return this;
		}

		@Override
		public IAsyncService sortBy(String sortBy) {
			link.sortBy(sortBy);
			return this;
		}

		@Override
		public IAsyncService country(String country) {
			link.country(country);
			return this;
		}

		@Override
		public IAsyncService page(int page) {
			link.page(page);
			return this;
		}

		@Override
		public IAsyncService to(Date to) {
			link.to(to);
			return this;
		}

		@Override
		public IAsyncService from(Date date) {
			link.from(date);
			return this;
		}

		@Override
		public IAsyncService prepare(String apiKey, Endpoint endpoint) {
			this.apiKey = apiKey;
			this.endpoint = endpoint;
			this.link = new LinkBuilder(endpoint);
			return this;
		}
		
		

		@Override
		public void send(Callback<APIResponse> result, Callback<Throwable> onError) {
			new Thread(() -> {
				link.finish(apiKey);
				String content = new APIConnection(link).getContents();
				int totalRes = APIResponse.INFORMATION_UNAVAILABLE;
				JSONObject obj = null;
				try {
					obj = new JSONObject(content);
					// System.out.println(obj);
				} catch (JSONException e1) {
					// throw new NewsAPIException("Something went wrong while parsing json");
					e1.printStackTrace();
				}

				System.out.println(link);
				ResponseStatus status = ResponseStatus.OK;
				/**
				 * Returns at least an empty string, not null
				 */
				try {
					if (obj.getString("status").equals("error") || content.equals("")) {
						status = ResponseStatus.ERROR;
						String message = "";
						try {
							message = obj.getString("message");
						} catch (JSONException e) {
							onError.invoke(new NewsAPIException("Something went wrong while parsing json"));
						}
						onError.invoke(new NewsAPIException(message));
					}
				} catch (JSONException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				if (endpoint != Endpoint.SOURCES)
					try {
						totalRes = obj.getInt("totalResults");
					} catch (JSONException e) {
						onError.invoke(new NewsAPIException("Something went wrong while parsing json"));
					}
				result.invoke(new Response(endpoint, content, status, totalRes));
			}).start();
		}

		@Override
		public IAsyncService dateRange(Supplier<DateRange> range) {
			this.link.from(range.get().getFrom());
			this.link.to(range.get().getTo());
			return this;
		}

	}

}
