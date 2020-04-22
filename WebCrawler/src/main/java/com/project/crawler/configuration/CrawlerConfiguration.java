package com.project.crawler.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.project.crawler.mybatis.service.CrawlerServiceStatusImpl;
import com.project.crawler.mybatis.service.CrawlerStatusService;
import com.project.crawler.service.CrawlerService;


@Configuration
public class CrawlerConfiguration {

	
	@Bean
	public CrawlerService crawlerService(){
		
		return new CrawlerService();
	}
	
	@Bean
	public CrawlerServiceStatusImpl crawlerStatusService(){
		
		return new CrawlerServiceStatusImpl();
	}
}
