package com.project.crawler.controller;

import java.io.IOException;

import java.net.URL;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.concurrent.ExecutionException;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.crawler.interfaces.ICrawler;
import com.project.crawler.logger.LoggerService;
import com.project.crawler.models.AcknowledgementRequest;
import com.project.crawler.models.CrawlerResponse;
import com.project.crawler.mybatis.model.CrawlerData;
import com.project.crawler.mybatis.model.CrawlerStatus;
import com.project.crawler.service.CrawlerService;

@Controller
public class CrawlerController {

	@Autowired
	@Qualifier("crawlerService")
	ICrawler crawlerService;
	
	
	 private static Logger log =LoggerService.getLogger();

	
	@RequestMapping(value = "/crawlUrl", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public  ResponseEntity<String> submitCrawlRequest(@RequestParam("url") URL url,
			@RequestParam("maxDepth") int maxDepth)
			throws InterruptedException, SQLException, IOException, ExecutionException {

		log.info("Request recieved for Crawl");
		String serviceResponse = crawlerService.crawl(url, maxDepth);

		
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("Content-Type", "application/json");
		return new ResponseEntity<String>(serviceResponse, responseHeaders, HttpStatus.OK);

	}

	@RequestMapping(value = "/getCrawlStatus", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	@ResponseBody
	public CrawlerStatus getCrawlStatus(@RequestBody AcknowledgementRequest acknowledgementRequest)
			throws InterruptedException, SQLException, IOException, ExecutionException {

		CrawlerStatus crawlStatus = crawlerService.fetchStatus(acknowledgementRequest.getAcknowledgementToken());
        return crawlStatus;

	}

	@RequestMapping(value = "/getCrawlResults", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	@ResponseBody
	public CrawlerResponse getCrawlResults(@RequestBody AcknowledgementRequest acknowledgementRequest)
			throws InterruptedException, SQLException, IOException, ExecutionException {

		CrawlerResponse crawlResponseResult = crawlerService.fetchResults(acknowledgementRequest.getAcknowledgementToken());

		return crawlResponseResult;

	}

}
