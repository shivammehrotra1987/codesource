package com.project.crawler.logger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


public class LoggerService {
	
	 private static Logger log = LoggerFactory.getLogger(LoggerService.class);

	  public static Logger getLogger() {
	      return log;
	  }

}
