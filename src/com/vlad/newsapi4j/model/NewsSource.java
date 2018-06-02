package com.vlad.newsapi4j.model;

import org.json.JSONException;
import org.json.JSONObject;

import com.vlad.newsapi4j.utils.Category;

public class NewsSource implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long	serialVersionUID	= -5784025163604945320L;

	private String				id;
	private String				name;
	private String				description;
	private String				url;
	private Category			category;
	private String				language, country;

	public NewsSource(JSONObject from) {
		this.deserialize(from);
	}

	private void deserialize(JSONObject from) {
		try {
			this.id = from.getString("id");
			this.name = from.getString("name");
			this.description = from.getString("description");
			this.url = from.getString("url");
			this.category = Category.parse(from.getString("category"));
			this.language = from.getString("language");
			this.country = from.getString("country");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Category getCategory() {
		return category;
	}
	
	public String getCountry() {
		return country;
	}
	
	public String getDescription() {
		return description;
	}
	
	public String getId() {
		return id;
	}
	
	public String getLanguage() {
		return language;
	}
	
	public String getName() {
		return name;
	}
	
	public String getUrl() {
		return url;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((category == null) ? 0 : category.hashCode());
		result = prime * result + ((country == null) ? 0 : country.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((language == null) ? 0 : language.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((url == null) ? 0 : url.hashCode());
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
		NewsSource other = (NewsSource) obj;
		if (category != other.category)
			return false;
		if (country == null) {
			if (other.country != null)
				return false;
		} else if (!country.equals(other.country))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (language == null) {
			if (other.language != null)
				return false;
		} else if (!language.equals(other.language))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (url == null) {
			if (other.url != null)
				return false;
		} else if (!url.equals(other.url))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "NewsSource [id=" + id + ", name=" + name + ", description=" + description + ", url=" + url
		        + ", category=" + category + ", language=" + language + ", country=" + country + "]";
	}
	
	
	

}
