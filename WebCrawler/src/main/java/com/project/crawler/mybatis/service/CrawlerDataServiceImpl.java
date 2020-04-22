package com.project.crawler.mybatis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.crawler.mybatis.mappers.CrawlerDataMapper;
import com.project.crawler.mybatis.model.CrawlerData;


@Service("crawlerDataServiceImpl")
public class CrawlerDataServiceImpl implements CrawlerDataService {

	@Autowired
	private CrawlerDataMapper crawlerMapper;

	@Override
	public void insertResponse(CrawlerData crawlerData) {
		crawlerMapper.insertResponse(crawlerData);
		// TODO Auto-generated method stub

	}

	@Override
	public CrawlerData getCrawlResponse(String acknowledgementtoken) {
		
		return crawlerMapper.getCrawlResponse(acknowledgementtoken);
	}

	
	
}
