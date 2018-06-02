package com.vlad.newsapi4j.access;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import com.vlad.newsapi4j.utils.LinkBuilder;

public class APIConnection {

	public APIConnection(LinkBuilder link) {
		this.fromWhere = link.toString();
	}

	public APIConnection(String linkRaw) {
		this.fromWhere = linkRaw;
	}

	private String fromWhere;

	public String getContents() {
		StringBuilder builder = new StringBuilder();
		try {
			HttpURLConnection connection = (HttpURLConnection) new URL(fromWhere).openConnection();
			connection.setDoInput(true);
			connection.setRequestMethod("GET");
			connection.setRequestProperty("Content-Type", "application/json; charset=utf-8");
			connection.setRequestProperty("Data-Type", "json");
			InputStreamReader streamReader = new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8);
			BufferedReader reader = new BufferedReader(streamReader);
			String line;
			while ((line = reader.readLine()) != null) {
				builder.append(line);
			}
		} catch (Exception ex) {

		}
		return builder.toString();
	}

}
