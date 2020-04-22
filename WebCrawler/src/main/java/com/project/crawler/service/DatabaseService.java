package com.project.crawler.service;

import org.springframework.stereotype.Service;

import com.google.gson.JsonObject;
import com.project.crawler.models.CrawlerNode;
import com.project.crawler.mybatis.model.CrawlerData;
import com.project.crawler.mybatis.model.CrawlerStatus;
import com.project.crawler.mybatis.service.CrawlerDataService;
import com.project.crawler.mybatis.service.CrawlerStatusService;

@Service("dbService")
public class DatabaseService {

	public void updateCrawlStatus(CrawlerStatus crawlerStatus, CrawlerStatusService crawlerStatusService,
			String status) {

		crawlerStatus.setStatus(status);
		crawlerStatusService.updateStatus(crawlerStatus);

	}
	
	public CrawlerStatus setCrawlStatus(CrawlerStatusService crawlerStatusService,
			String status,String acknowledgementToken,String url) {

		CrawlerStatus crawlerStatus= new CrawlerStatus();
		crawlerStatus.setAcknowledgementtoken(acknowledgementToken);
		crawlerStatus.setStatus(status);
		crawlerStatus.setUrl(url);
		crawlerStatusService.insertStatus(crawlerStatus);
		
		return crawlerStatus;

	}

	public void persistInDatabase(CrawlerDataService crawlerDataService, CrawlerStatus crawlerStatus,JsonObject finalObject) {

		

		CrawlerData crawlerData = new CrawlerData();
		crawlerData.setAcknowledgementtoken(crawlerStatus.getAcknowledgementtoken());
		crawlerData.setResponse(finalObject.toString());

		try {
			crawlerDataService.insertResponse(crawlerData);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
