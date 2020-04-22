package com.project.crawler.utils;



import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


public class ResponseJsonUtil {
	
	
	public static String createJsonObject(int code, String message,String response) {
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("resCode", code);
		jsonObject.addProperty("resMessage", message);
		
		

		if (response != null && !"".equals(response)) {
			JsonParser parser = new JsonParser();
			jsonObject.add("response", parser.parse(response));
		}
		return jsonObject.toString();
	}	
	
	public static String updateMessageJsonObject(int code, String message,
			String interactionId, String interationType, String response, String sessionId) {
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("resCode", code);
		jsonObject.addProperty("resMessage", message);
		jsonObject.addProperty("interactionId", interactionId);
		jsonObject.addProperty("action", "upgrade");
		jsonObject.addProperty("interationType", interationType);
		jsonObject.addProperty("sessionId", sessionId);

		if (response != null && !"".equals(response)) {
			JsonParser parser = new JsonParser();
			jsonObject.add("response", parser.parse(response));
		}
		return jsonObject.toString();
	}	
	
	
}
