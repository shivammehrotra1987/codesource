package com.project.crawler.mybatis.service;

import com.project.crawler.mybatis.model.CrawlerData;

public interface CrawlerDataService {

	public void insertResponse(CrawlerData crawlerData);
	public CrawlerData getCrawlResponse(String acknowledgementtoken);

}
