package com.mie.spider.thread;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.downloader.HttpClientDownloader;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.proxy.Proxy;
import us.codecraft.webmagic.proxy.SimpleProxyProvider;
import us.codecraft.webmagic.scheduler.QueueScheduler;

public class AnjukeOldSpider implements PageProcessor{
	private Site site = Site.me()
			.setSleepTime(0)
			.setCycleRetryTimes(3)
			.setTimeOut(6000)
			.setUserAgent("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/31.0.1650.63 Safari/537.36")
		;
    public static final String list_pattern = ".*wuhan\\.anjuke\\.com/sale/p\\d+-t49-y2/";//校验地址正则
    public static final String detail_pattern = ".*wuhan\\.anjuke\\.com/prop/view/.*";//校验地址正则
    //public static final String detail_pattern = ".*\\.lianjia\\.com/ershoufang/.*\\.html";//校验地址正则
	@Override
	public Site getSite() {
		return site;
	}

	@Override
	public void process(Page page) {
		try {
		FileWriter fs = new FileWriter("F:/test/web.txt",false);
        fs.write(page.getHtml().get());
        fs.close();
		}catch (Exception e) {
		}
		
/*		for (String str : page.getHtml().links().all()) {
			System.out.println(str);
		}*/
		
		String url=page.getUrl().get();
		if(page.getUrl().regex(list_pattern).match()) {
			try {
				FileWriter fs = new FileWriter("F:/test/listurl.txt",true);
		        fs.write(url);
		        fs.write("\r\n");
		        fs.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			page.addTargetRequests(page.getHtml().links().regex(list_pattern).all());
			page.addTargetRequests(page.getHtml().links().regex(detail_pattern).all());
		}else if(page.getUrl().regex(detail_pattern).match()) {
			try {
				FileWriter fs = new FileWriter("F:/test/detailurl.txt",true);
		        fs.write(url);
		        fs.write("\r\n");
		        fs.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	/*
	public static void main(String[] args) {
		HttpClientDownloader httpClientDownloader = new HttpClientDownloader();
		httpClientDownloader.setProxyProvider(SimpleProxyProvider.from(new Proxy("59.173.72.10", 808)));
		    
		Spider spider=  Spider.create(new AnjukeOldSpider())
				  .setScheduler(new QueueScheduler())
				  .setDownloader(httpClientDownloader)
				  .thread(1);
		spider.addUrl("https://wuhan.anjuke.com/sale/p1-t49-y2/");
		//spider.addUrl("https://wuhan.anjuke.com/prop/view/A1113173263");
		spider.run();
		  Spider.create(new LianjiaOldListSpider())
		  .setScheduler(new QueueScheduler())
		  .addUrl("https://wh.lianjia.com/ershoufang/pg10co32y1sf1/").thread(5).run();
	}*/

}
