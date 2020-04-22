package com.project.crawler.mybatis.mappers;

import com.project.crawler.mybatis.model.CrawlerStatus;

public interface CrawlerMapper {

	public void insertStatus(CrawlerStatus crawlerStatus);
    public CrawlerStatus getCrawlerStatus(String acknowledgementtoken);
    public void updateStatus(CrawlerStatus crawlerStatus);

}
