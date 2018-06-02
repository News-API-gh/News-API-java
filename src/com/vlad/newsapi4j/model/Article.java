package com.vlad.newsapi4j.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.json.JSONException;
import org.json.JSONObject;

public class Article implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 4316843304782307619L;

	private Source				source;

	private String				author;

	private String				title;

	private String				description;

	private String				url, urlToImage;

	private Date				publishedAt;

	public Article(JSONObject from) {
		this.deserialize(from);
	}

	private void deserialize(JSONObject from) {
		try {
			JSONObject source = from.getJSONObject("source");
			this.source = new Source(source.getString("id"), source.getString("name"));
			this.author = from.getString("author");
			this.title = from.getString("title");
			this.description = from.getString("description");
			this.url = from.getString("url");
			this.urlToImage = from.getString("urlToImage");
			String publishedAt = from.getString("publishedAt");
			if (publishedAt != null) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
				this.publishedAt = sdf.parse(publishedAt);
			}
		} catch (JSONException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String getAuthor() {
		return author;
	}

	public String getDescription() {
		return description;
	}

	public Date getPublishedAt() {
		return publishedAt;
	}

	public Source getSource() {
		return source;
	}

	public String getTitle() {
		return title;
	}

	public String getUrl() {
		return url;
	}

	public String getUrlToImage() {
		return urlToImage;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((author == null) ? 0 : author.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((publishedAt == null) ? 0 : publishedAt.hashCode());
		result = prime * result + ((source == null) ? 0 : source.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		result = prime * result + ((url == null) ? 0 : url.hashCode());
		result = prime * result + ((urlToImage == null) ? 0 : urlToImage.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Article other = (Article) obj;
		if (author == null) {
			if (other.author != null)
				return false;
		} else if (!author.equals(other.author))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (publishedAt == null) {
			if (other.publishedAt != null)
				return false;
		} else if (!publishedAt.equals(other.publishedAt))
			return false;
		if (source == null) {
			if (other.source != null)
				return false;
		} else if (!source.equals(other.source))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		if (url == null) {
			if (other.url != null)
				return false;
		} else if (!url.equals(other.url))
			return false;
		if (urlToImage == null) {
			if (other.urlToImage != null)
				return false;
		} else if (!urlToImage.equals(other.urlToImage))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Article [source=" + source + ", author=" + author + ", title=" + title + ", description=" + description
		        + ", url=" + url + ", urlToImage=" + urlToImage + ", publishedAt=" + publishedAt + "]";
	}

	public class Source {

		private String	id;
		private String	name;

		public Source(String id, String name) {
			this.id = id;
			this.name = name;
		}

		public String getId() {
			return id;
		}

		public String getName() {
			return name;
		}

		@Override
		public String toString() {
			return "Source [id=" + id + ", name=" + name + "]";
		}

	}

}
