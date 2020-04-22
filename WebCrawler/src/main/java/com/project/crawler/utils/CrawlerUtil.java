package com.project.crawler.utils;

import java.net.URL;

public class CrawlerUtil {

	public static boolean validateURL(URL url) {

		if (url != null && (url.getProtocol().equalsIgnoreCase("http") || url.getProtocol().equalsIgnoreCase("https")))
			return true;
		return false;

	}

}
