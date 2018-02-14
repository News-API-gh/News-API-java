package com.google.newsapi.models;

import java.util.Date;

public class Article
{
	public Source source;
	public String author;
	public String title;
	public String description;
	public String url;
	public String urlToImage;
	public Date publishedAt;

	@Override
	public String toString()
	{
		return "Article [Source=" + source + ", author=" + author + ", title=" + title + ", description=" + description
				+ ", url=" + url + ", urlToImage=" + urlToImage + ", publishedAt=" + publishedAt + "]";
	}

}
