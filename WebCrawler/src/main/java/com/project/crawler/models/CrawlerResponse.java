package com.project.crawler.models;

import java.util.List;

public class CrawlerResponse {


	public 	int total_links;
	public int total_images;
    List<CrawlerData> details;
    String responseStatus;
	
    public int getTotal_links() {
		return total_links;
	}
	public void setTotal_links(int total_links) {
		this.total_links = total_links;
	}
	public int getTotal_images() {
		return total_images;
	}
	public void setTotal_images(int total_images) {
		this.total_images = total_images;
	}
	public List<CrawlerData> getDetails() {
		return details;
	}
	public void setDetails(List<CrawlerData> details) {
		this.details = details;
	}
	public String getResponseStatus() {
		return responseStatus;
	}
	public void setResponseStatus(String responseStatus) {
		this.responseStatus = responseStatus;
	}
	
}
