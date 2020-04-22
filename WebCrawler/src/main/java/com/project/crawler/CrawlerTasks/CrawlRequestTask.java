package com.project.crawler.CrawlerTasks;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.slf4j.Logger;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.project.crawler.constants.CrawlerConstants;
import com.project.crawler.logger.LoggerService;
import com.project.crawler.models.CrawlerNode;
import com.project.crawler.models.CrawlerObject;
import com.project.crawler.mybatis.model.CrawlerData;
import com.project.crawler.mybatis.model.CrawlerStatus;
import com.project.crawler.mybatis.service.CrawlerDataService;
import com.project.crawler.mybatis.service.CrawlerStatusService;
import com.project.crawler.service.DatabaseService;

public class CrawlRequestTask implements Callable<Object> {
	JsonArray array = new JsonArray();
	int imageCount = 0;
	CrawlerNode crawler;
	List<URL> urls;
	int maxDepth;
	CrawlerStatusService crawlerStatusService;
	CrawlerStatus crawlerStatus;
	CrawlerDataService crawlerDataService;
	DatabaseService dbService;
	private ExecutorService executorService = Executors.newFixedThreadPool(10);


	public CrawlRequestTask(CrawlerNode crawler, List<URL> urls, int maxDepth,
			CrawlerStatusService crawlerStatusService, CrawlerDataService crawlerDataService,
			CrawlerStatus crawlerStatus, DatabaseService dbService) {
		this.crawler = crawler;
		this.urls = urls;
		this.maxDepth = maxDepth;
		this.crawlerStatusService = crawlerStatusService;
		this.crawlerStatus = crawlerStatus;
		this.crawlerDataService = crawlerDataService;
		this.dbService = dbService;

	}

	public CrawlerNode crawlUrls(CrawlerNode crawler, List<URL> urls, int maxDepth)
			throws InterruptedException, ExecutionException {

		if (maxDepth == 0)
			return null;

		if (crawler == null) {

			CrawlerTask crawlerTask = new CrawlerTask(urls.get(0));
			Future<CrawlerObject> crawlResponse = executorService.submit(crawlerTask);

			CrawlerObject crawlObject = crawlResponse.get();
			crawler = new CrawlerNode(crawlObject.getUrl().toString(), crawlObject.getTitle(),
					new ArrayList<CrawlerNode>());
			createDetailsJsonObject(crawlObject);
			

		}

		List<Future<CrawlerObject>> crawlerObjectFutureList = new ArrayList<>();

		for (URL url : urls) {

			CrawlerTask crawlerTask = new CrawlerTask(url);
			Future<CrawlerObject> crawlResponse = executorService.submit(crawlerTask);
			crawlerObjectFutureList.add(crawlResponse);
		}

		for (Future<CrawlerObject> crawlResponseData : crawlerObjectFutureList) {
			CrawlerObject crawlObject = crawlResponseData.get();
			CrawlerNode crawlerChild = new CrawlerNode(crawlObject.getUrl().toString(), crawlObject.getTitle(),
					new ArrayList<CrawlerNode>());
			createDetailsJsonObject(crawlObject);
			CrawlerNode child = crawlUrls(crawlerChild, crawlObject.getUrls(), maxDepth - 1);

			if (child != null)
				crawlerChild.getCrawlNodes().add(child);

			crawler.getCrawlNodes().add(crawlerChild);

		}
		return crawler;

	}

	

	@Override
	public Object call() throws Exception {
		// TODO Auto-generated method stub

		dbService.updateCrawlStatus(crawlerStatus, crawlerStatusService, CrawlerConstants.CRAWL_IN_PROCESSING);
		crawler = crawlUrls(crawler, urls, maxDepth);

		dbService.persistInDatabase(crawlerDataService, crawlerStatus, createCrawlResponseJsonObject());
		dbService.updateCrawlStatus(crawlerStatus, crawlerStatusService, CrawlerConstants.CRAWL_PROCESSED);
		return crawler;

	}

	
	public void createDetailsJsonObject(CrawlerObject crawlObject){
		
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("page_title", crawlObject.getTitle());
		jsonObject.addProperty("page_link", crawlObject.getUrl().toString());
		jsonObject.addProperty("image_count", crawlObject.getImageCount());
		array.add(jsonObject);
		imageCount += crawlObject.getImageCount();
		
	}
	
	
	public JsonObject createCrawlResponseJsonObject() {
		JsonObject finalObject = new JsonObject();
		finalObject.addProperty("total_links", array.size());
		finalObject.addProperty("total_images", imageCount);
		finalObject.add("details", array);
		return finalObject;
	}

}
