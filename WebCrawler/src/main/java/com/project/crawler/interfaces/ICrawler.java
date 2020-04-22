package com.project.crawler.interfaces;

import java.net.URL;

import com.project.crawler.models.CrawlerResponse;
import com.project.crawler.mybatis.model.CrawlerStatus;

public interface ICrawler {
	
	public String crawl(URL url, int maxDepth);
	public CrawlerStatus fetchStatus(String acknowledgementToken);
	public CrawlerResponse fetchResults(String acknowledgementToken);

}
