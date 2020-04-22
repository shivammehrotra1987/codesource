package com.project.crawler.mybatis.model;

public class CrawlerStatus {

	int id;
	String acknowledgementtoken;
	String url;
	String processstatus;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAcknowledgementtoken() {
		return acknowledgementtoken;
	}
	public void setAcknowledgementtoken(String acknowledgementtoken) {
		this.acknowledgementtoken = acknowledgementtoken;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getStatus() {
		return processstatus;
	}
	public void setStatus(String processstatus) {
		this.processstatus = processstatus;
	} 
	
}
