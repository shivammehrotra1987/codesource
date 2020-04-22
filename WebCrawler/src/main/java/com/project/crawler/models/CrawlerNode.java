package com.project.crawler.models;

import java.net.URL;
import java.util.List;

public class CrawlerNode {

	
	String url;
	String title;
	List<CrawlerNode> crawlNodes;

	public CrawlerNode(String url, String title, List<CrawlerNode> crwalNodes) {
		super();
		this.url = url;
		this.title = title;
		this.crawlNodes = crwalNodes;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<CrawlerNode> getCrawlNodes() {
		return crawlNodes;
	}

	public void setCrawlNodes(List<CrawlerNode> crawlNodes) {
		this.crawlNodes = crawlNodes;
	}

	
}
