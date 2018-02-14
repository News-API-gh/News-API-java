package com.google.newsapi.models;

import static com.google.newsapi.config.Constants.*;

import java.util.List;

public class ApiResponse
{
	public STATUSES status;
	public ERROR_CODES code;
	public String message;
  public List<Article> articles;
	public int totalResults;
	
	@Override
	public String toString()
	{
		return "ApiResponse [status=" + status + ", code=" + code + ", message=" + message + ", articles=" + articles
				+ ", totalResults=" + totalResults + "]";
	}
}
