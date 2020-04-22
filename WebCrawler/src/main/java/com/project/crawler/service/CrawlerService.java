package com.project.crawler.service;

import java.net.URL;
import java.util.Collections;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.project.crawler.CrawlerTasks.CrawlRequestTask;
import com.project.crawler.constants.CrawlerConstants;
import com.project.crawler.interfaces.ICrawler;
import com.project.crawler.logger.LoggerService;
import com.project.crawler.models.CrawlerNode;
import com.project.crawler.models.CrawlerResponse;
import com.project.crawler.mybatis.model.CrawlerData;
import com.project.crawler.mybatis.model.CrawlerStatus;
import com.project.crawler.mybatis.service.CrawlerDataService;
import com.project.crawler.mybatis.service.CrawlerStatusService;
import com.project.crawler.utils.CrawlerUtil;
import com.project.crawler.utils.JsonUtil;
import com.project.crawler.utils.ResponseJsonUtil;

@Service("crawlerService")

public class CrawlerService implements ICrawler{

	@Autowired
	@Qualifier("crawlerServiceImpl")
	private CrawlerStatusService crawlerStatusService;

	@Autowired
	@Qualifier("crawlerDataServiceImpl")
	private CrawlerDataService crawlerDataService;

	@Autowired
	@Qualifier("dbService")
	private DatabaseService dbService;

	@Autowired
	private JsonUtil jsonUtil;

	private static Logger log = LoggerService.getLogger();

	JsonArray array = new JsonArray();
	int imageCount = 0;

	private ExecutorService executorService = Executors.newFixedThreadPool(5);

	public String crawl(URL url, int maxDepth) {

		CrawlerNode crawler = null;
		
		if (!CrawlerUtil.validateURL(url))
			return ResponseJsonUtil.createJsonObject(514, "URL Validation Failed", "");

		String acknowledgementToken = null;
		JsonObject finalObject = new JsonObject();
		try {

			acknowledgementToken = UUID.randomUUID().toString();
			finalObject.addProperty("acknowledgement_token", acknowledgementToken);

			CrawlerStatus crawlerStatus = dbService.setCrawlStatus(crawlerStatusService,
					CrawlerConstants.CRAWL_SUBMITTED, acknowledgementToken, url.toString());

			CrawlRequestTask crawlRequestTask = new CrawlRequestTask(crawler, Collections.singletonList(url), maxDepth,
					crawlerStatusService, crawlerDataService, crawlerStatus, dbService);

			/*
			 * submitting crawl Job to executor
			 */
			executorService.submit(crawlRequestTask);

			log.info("Crawl Request submitted successfully for url:"+url.toString());
			
		} catch (Exception e) {

			dbService.setCrawlStatus(crawlerStatusService, CrawlerConstants.CRAWL_FAILED, acknowledgementToken,
					url.toString());
			log.info("Crawl Request Failed for url:",url.toString());
			
			return ResponseJsonUtil.createJsonObject(500, "Submit request failed", finalObject.toString());

		}

		return ResponseJsonUtil.createJsonObject(200, "Successfuly submitted request ", finalObject.toString());
	}

	public CrawlerStatus fetchStatus(String acknowledgementToken) {

		CrawlerStatus crawlerStatus = crawlerStatusService.getCrawlerStatus(acknowledgementToken);
		log.info("Status of crawl url:" + crawlerStatus.getUrl() + ": " + crawlerStatus.getStatus());
		
		return crawlerStatus;

	}

	public CrawlerResponse fetchResults(String acknowledgementToken) {

		CrawlerStatus crawlerStatus = crawlerStatusService.getCrawlerStatus(acknowledgementToken);
		CrawlerData crawlerData = new CrawlerData();
		crawlerData.setResponse("Request Not Found");
		CrawlerResponse crawlerResponse = new CrawlerResponse();

		if (crawlerStatus.getStatus().equalsIgnoreCase("in-processing")) {

			crawlerResponse.setResponseStatus(crawlerStatus.getStatus());
		}

		if (crawlerStatus.getStatus().equalsIgnoreCase("processed")) {

			crawlerData.setResponse("Data Processed");
			
			crawlerData = crawlerDataService.getCrawlResponse(acknowledgementToken);

			log.info("Response of crawl : "+ crawlerData.getResponse());

			crawlerResponse = (CrawlerResponse) jsonUtil.convertFromJsonToObject(crawlerData.getResponse(),
					crawlerResponse);
			crawlerResponse.setResponseStatus(crawlerStatus.getStatus());

		}

		return crawlerResponse;

	}

}
