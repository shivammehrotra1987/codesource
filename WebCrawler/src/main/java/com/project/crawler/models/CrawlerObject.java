package com.project.crawler.models;

import java.net.URL;
import java.util.List;

public class CrawlerObject {
	
	
	URL url;
	String title;
	int imageCount;
	List<URL> urls;

	public CrawlerObject(URL url, String title,int imageCount, List<URL> urls) {
		super();
		this.url = url;
		this.title = title;
		this.imageCount=imageCount;
		this.urls = urls;
		
	}

	public URL getUrl() {
		return url;
	}

	public void setUrl(URL url) {
		this.url = url;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getImageCount() {
		return imageCount;
	}

	public void setImageCount(int imageCount) {
		this.imageCount = imageCount;
	}

	public List<URL> getUrls() {
		return urls;
	}

	public void setUrls(List<URL> urls) {
		this.urls = urls;
	}
	
}
