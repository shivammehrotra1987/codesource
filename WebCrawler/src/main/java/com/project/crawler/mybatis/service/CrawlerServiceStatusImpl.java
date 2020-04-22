package com.project.crawler.mybatis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.crawler.mybatis.mappers.CrawlerMapper;
import com.project.crawler.mybatis.model.CrawlerStatus;

@Service("crawlerServiceImpl")
public class CrawlerServiceStatusImpl implements CrawlerStatusService {

	@Autowired
	private CrawlerMapper crawlerMapper;

	@Override
	public void insertStatus(CrawlerStatus crawlerStatus) {
		crawlerMapper.insertStatus(crawlerStatus);
		// TODO Auto-generated method stub

	}

	@Override
	public CrawlerStatus getCrawlerStatus(String acknowledgementtoken) {
		CrawlerStatus crawlerStatus = crawlerMapper.getCrawlerStatus(acknowledgementtoken);

		return crawlerStatus;
	}

	@Override
	public void updateStatus(CrawlerStatus crawlerStatus) {

		crawlerMapper.updateStatus(crawlerStatus);

	}

}
