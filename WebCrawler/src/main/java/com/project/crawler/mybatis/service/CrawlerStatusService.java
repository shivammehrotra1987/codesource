package com.project.crawler.mybatis.service;

import java.util.List;

import com.project.crawler.mybatis.model.CrawlerStatus;



public interface CrawlerStatusService {
	
	public void insertStatus(CrawlerStatus crawlerStatus);
    public CrawlerStatus getCrawlerStatus(String acknowledgementtoken);
    public void updateStatus(CrawlerStatus crawlerStatus);

}
