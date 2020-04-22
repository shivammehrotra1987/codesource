package com.project.crawler.utils;

import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.project.crawler.models.CrawlerResponse;

@Service("jsonUtil")
public class JsonUtil {

	Gson gson = new Gson();

	public <T> T convertFromJsonToObject(String json, T object) {

		if (object instanceof CrawlerResponse) {
			T crawlerResponse = (T) gson.fromJson(json, CrawlerResponse.class);
			return crawlerResponse;
		}
		return object;

	}
	
	
	

}
