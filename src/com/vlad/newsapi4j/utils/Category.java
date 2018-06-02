package com.vlad.newsapi4j.utils;

public enum Category {

	Business, Entertainment, Health, Science, Sports, Technology, General;

	public String toLink() {
		return this.name().toLowerCase();
	}

	public static Category parse(String category) {
		if (category.equalsIgnoreCase(Category.Business.name()))
			return Category.Business;
		if (category.equalsIgnoreCase(Category.Entertainment.name()))
			return Category.Entertainment;
		if (category.equalsIgnoreCase(Category.Health.name()))
			return Category.Health;
		if (category.equalsIgnoreCase(Category.Science.name()))
			return Category.Science;
		if (category.equalsIgnoreCase(Category.Sports.name()))
			return Category.Sports;
		if (category.equalsIgnoreCase(Category.Technology.name()))
			return Category.Technology;
		return Category.General;
	}

}
