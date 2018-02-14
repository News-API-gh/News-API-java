package com.google.newsapi.models;

import static com.google.newsapi.config.Constants.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

///
/// Params for making a request to the /everything endpoint.
/// 
public class EverythingRequest
{
  ///
  /// The keyword or phrase to search for. Boolean operators are supported.
	///
  public String q;
  ///
  /// If you want to restrict the search to specific sources, add their Ids here. You can find source Ids with the /sources endpoint or on newsapi.org.
  ///
  public List<String> sources = new ArrayList<String>();
  ///
  /// If you want to restrict the search to specific web domains, add these here. Example: nytimes.com.
  ///
  public List<String> domains = new ArrayList<String>();
  ///
  /// The earliest date to retrieve articles from. Note that how far back you can go is constrained by your plan type. See newsapi.org/pricing for plan details.
  ///
  public Date from;
  ///
  /// The latest date to retrieve articles from.
  ///
  public Date to;
  ///
  /// The language to restrict articles to.
  ///
  public LANGUAGES language;
  ///
  /// How should the results be sorted? Relevancy = articles relevant to the Q param come first. PublishedAt = most recent articles come first. Publisher = popular publishers come first.
  ///
  public SORT_BYS sortBy;
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
		return "EverythingRequest [q=" + q + ", sources=" + sources + ", domains=" + domains + ", from=" + from + ", to="
				+ to + ", language=" + language + ", sortBy=" + sortBy + ", page=" + page + ", pageSize=" + pageSize + "]";
	}
  
  
}
