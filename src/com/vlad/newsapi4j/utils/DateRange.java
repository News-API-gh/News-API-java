package com.vlad.newsapi4j.utils;

import java.util.Date;

public class DateRange {

	private Date from, to;

	public DateRange(Date from, Date to) {
		this.from = from;
		System.out.println("from :: "+from);
		this.to = to;
	}

	public static final DateRange SINCE_YESTERDAY() {
		return new DateRange(new Date(System.currentTimeMillis() - 3600 * 1000 * 24), new Date());
	}

	public static final DateRange LAST_7_DAYS() {
		return new DateRange(new Date(System.currentTimeMillis() - 3600 * 1000 * 24 * 7), new Date());
	}

	public static final DateRange LAST_30_DAYS() {
		return new DateRange(new Date(System.currentTimeMillis() - 3600 * 1000 * 24 * 30), new Date());
	}

	public static final DateRange LAST_100_DAYS() {
		return new DateRange(new Date(System.currentTimeMillis() - 3600 * 1000 * 24 * 100), new Date());
	}

	public Date getFrom() {
		return from;
	}

	public Date getTo() {
		return to;
	}

}
