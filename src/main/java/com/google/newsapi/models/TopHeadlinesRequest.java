package com.google.newsapi.models;
import static com.google.newsapi.config.Constants.*;

import java.util.ArrayList;
import java.util.List;

	/// 
  /// Params for making a request to the /top-headlines endpoint.
  /// 
  public class TopHeadlinesRequest
  {
      /// 
      /// The keyword or phrase to search for. Boolean operators are supported.
      /// 
      public String q;
      /// 
      /// If you want to restrict the results to specific sources, add their Ids here. You can find source Ids with the /sources endpoint or on newsapi.org.
      /// 
      public List<String> sources = new ArrayList<String>();
      /// 
      /// If you want to restrict the headlines to a specific news category, add these here.
      /// 
      public CATEGORIES category;
      /// 
      /// The language to restrict articles to.
      /// 
      public LANGUAGES language;
      /// 
      /// The country of the source to restrict articles to.
      /// 
      public COUNTRIES country;
      /// 
      /// Each request returns a fixed amount of results. Page through them by increasing this.
      /// 
      public int page;
      /// 
      /// Set the max number of results to retrieve per request. The max is 100.
      /// 
      public int pageSize;
			@Override
			public String toString()
			{
				return "TopHeadlinesRequest [q=" + q + ", sources=" + sources + ", category=" + category + ", language="
						+ language + ", country=" + country + ", page=" + page + ", pageSize=" + pageSize + "]";
			}
      
      
}
