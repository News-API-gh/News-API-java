package com.vlad.newsapi4j.client;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.vlad.newsapi4j.response.APIResponse;
import com.vlad.newsapi4j.service.Endpoint;
import com.vlad.newsapi4j.utils.ResponseStatus;

public class Response implements APIResponse {

	private ResponseStatus		status;
	private int					totalRes;
	private JSONObject			content;
	private List<JSONObject>	list;
	private Endpoint			endpoint;

	public Response(Endpoint reqType, String content, ResponseStatus status, int totalRes) {
		this.totalRes = totalRes;
		this.status = status;
		List<JSONObject> dataActual = new ArrayList<>();
		try {
			this.content = new JSONObject(content);
			JSONArray jsonData = this.content.getJSONArray(reqType != Endpoint.SOURCES ? "articles" : "sources");
			for (int i = 0; i < jsonData.length(); i++) {
				dataActual.add(jsonData.getJSONObject(i));
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.list = dataActual;
		this.endpoint = reqType;
	}

	@Override
	public ResponseStatus getStatus() {
		return status;
	}

	@Override
	public int totalResults() {
		return totalRes;
	}

	@Override
	public JSONObject getJSON() {
		return content;
	}

	@Override
	public List<JSONObject> getData() {
		return list;
	}

	@Override
	public Type getResponseType() {
		return endpoint == Endpoint.SOURCES ?
				Type.SOURCES 
			  : Type.ARTICLES;
	}

}