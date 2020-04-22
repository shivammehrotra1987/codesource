package com.project.crawler.mybatis.mappers;

import com.project.crawler.mybatis.model.CrawlerData;
import com.project.crawler.mybatis.model.CrawlerStatus;

public interface CrawlerDataMapper {

	public void insertResponse(CrawlerData crawlerData);
	public CrawlerData getCrawlResponse(String acknowledgementtoken);
    
}
