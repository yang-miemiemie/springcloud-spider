package com.mie.spider.task;

import org.apache.log4j.Logger;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.mie.spider.thread.LianjiaOldAddSpider;
import com.mie.spider.thread.LianjiaOldSpider;
import com.mie.spider.thread.pipeline.LianjiaOldPipeline;

import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.scheduler.QueueScheduler;

@Component("spiderTask")
public class SpiderTask {

    private Logger logger = Logger.getLogger(this.getClass());
	
    @Scheduled(cron = "0 0 2 * * ?")
    public void saveLiveVisitCount() {
    	try{
    		logger.info("定时任务开始爬取链家昨日房源========");
    		Spider spider=  Spider.create(new LianjiaOldAddSpider()).setScheduler(new QueueScheduler()).thread(5);
    		spider.addUrl("https://wh.lianjia.com/ershoufang/pg1co32y1sf1/");
    		spider.addPipeline(new LianjiaOldPipeline());
    		spider.runAsync();
    		logger.info("定时任务爬取链家昨日房源结束========");
    	}catch(Exception e) {
        	logger.warn("定时任务爬取链家昨日房源失败========");
        	logger.error(e.getMessage());
        	e.printStackTrace();
		}
    }  
}
