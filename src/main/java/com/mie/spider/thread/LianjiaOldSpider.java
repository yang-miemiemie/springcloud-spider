package com.mie.spider.thread;

import org.assertj.core.util.DateUtil;

import com.mie.spider.thread.pipeline.LianjiaOldPipeline;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.scheduler.QueueScheduler;
import us.codecraft.webmagic.selector.Html;

public class LianjiaOldSpider implements PageProcessor{
	private Site site = Site.me()
			.setSleepTime(0)
			.setCycleRetryTimes(3)
			.setTimeOut(6000)
			.setUserAgent("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/31.0.1650.63 Safari/537.36")
		;
    public static final String list_pattern = ".*\\.lianjia\\.com/ershoufang/pg\\d+co32y1sf1/";//校验地址正则
    public static final String detail_pattern = ".*\\.lianjia\\.com/ershoufang/.*\\.html";//校验地址正则
	@Override
	public Site getSite() {
		return site;
	}

	@Override
	public void process(Page page) {
		if(page.getUrl().regex(list_pattern).match()) {
			page.addTargetRequests(page.getHtml().links().regex(list_pattern).all());
			page.addTargetRequests(page.getHtml().links().regex(detail_pattern).all());
		}else if(page.getUrl().regex(detail_pattern).match()) {
			Html html= page.getHtml();
			String publicdate =html.xpath("//div[@class='transaction']//li[1]/span[2]/text()").toString();
			page.putField("publicdate", publicdate);
			page.putField("link", page.getUrl().toString());
			page.putField("viliage", html.xpath("//div[@class='communityName']/a[@class='info']/text()").toString());
			String room= html.xpath("//div[@class='room']/div[@class='mainInfo']/text()").toString();
			page.putField("roomcount",Integer.valueOf(room.substring(0, 1)));
			page.putField("hallcount",Integer.valueOf(room.substring(2, 3)));
			page.putField("price", Double.valueOf(html.xpath("//div[@class='price']/span[@class='total']/text()").toString()));
			page.putField("region", html.xpath("//div[@class='areaName']/span[@class='info']/a[1]/text()").toString());
			String area=html.xpath("//div[@class='houseInfo']/div[@class='area']/div[@class='mainInfo']/text()").toString();
			page.putField("area", area.substring(0, area.length()-2));
			page.putField("no", html.xpath("//div[@class='houseRecord']/span[@class='info']/text()").toString());
			page.putField("publicdate", html.xpath("//div[@class='transaction']//li[1]/span[2]/text()").toString());
		}
	}

}
