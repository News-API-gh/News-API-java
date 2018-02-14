package com.google.newsapi.models;
import static com.google.newsapi.config.Constants.*;

import java.util.List;
public class ArticlesResult
{
	public STATUSES status;
  public Error error;
  public int totalResults;
  public List<Article> articles;
  
	@Override
	public String toString()
	{
		return "ArticlesResult [status=" + status + ", error=" + error + ", totalResults=" + totalResults + ", articles="
				+ articles + "]";
	}
  
  
}
