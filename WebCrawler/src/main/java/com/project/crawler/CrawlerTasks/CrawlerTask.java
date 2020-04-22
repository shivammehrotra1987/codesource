package com.project.crawler.CrawlerTasks;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;

import com.project.crawler.logger.LoggerService;
import com.project.crawler.models.CrawlerObject;

public class CrawlerTask implements Callable<CrawlerObject> {

	URL url;
	private static Logger log = LoggerService.getLogger();

	public CrawlerTask(URL url) {
		this.url = url;
	}

	@Override
	public CrawlerObject call() throws Exception {
		// TODO Auto-generated method stub
		Document document = null;
		try {
			document = Jsoup.parse(url, 15000);
		} catch (IOException e) {

			log.info("Problem accessing url {}" + String.valueOf(url));

		}

		Elements titles = null;
		Elements links = null;
		Elements images = null;
		List<URL> urlList = new ArrayList<>();
		try {
			titles = document.select("title");
			links = document.select("a[href]");
			images = document.select("img[src]");

			for (Element link : links) {
				String linkString = link.attr("abs:href");
				if (linkString.startsWith("/")) {
					try {
						URL linkURL = new URL(url.toString()+linkString);
						urlList.add(linkURL);
					} catch (MalformedURLException e) {
						log.info("URL is malformed {}" + String.valueOf(url));
						continue;
					}

				} else {
					try {
						URL linkURL = new URL(linkString);
						urlList.add(linkURL);
					} catch (MalformedURLException e) {
						log.info("URL is malformed {}" + String.valueOf(url));
						continue;
					}
				}
			}
		} catch (Exception e) {

			return new CrawlerObject(url, "", 0, urlList);

		}
		return new CrawlerObject(url, titles.get(0).text(), images.size(), urlList);

	}

}
