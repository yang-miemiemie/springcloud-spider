package com.mie.spider.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.mie.spider.thread.LianjiaOldSpider;
import com.mie.spider.thread.LianjiaThread;
import com.mie.spider.thread.pipeline.LianjiaOldPipeline;

import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.scheduler.QueueScheduler;

@RestController
public class SpiderController {
	@RequestMapping(value = {"/lianjia_new"}, produces="application/json;charset=UTF-8")
	public String lianjia_new(HttpServletRequest request) {
		WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(request.getSession().getServletContext());
		LianjiaThread lianjiaThread = (LianjiaThread)context.getBean("lianjiaThread");
		lianjiaThread.start();
		return "start";
	}
	
	@RequestMapping(value = {"/lianjia_old"}, produces="application/json;charset=UTF-8")
	public String spider(HttpServletRequest request) {
		Spider spider=  Spider.create(new LianjiaOldSpider()).setScheduler(new QueueScheduler()).thread(5);
		spider.addUrl("https://wh.lianjia.com/ershoufang/pg1co32y1sf1/");
		spider.addPipeline(new LianjiaOldPipeline());
		spider.runAsync();
		return "start";
	}
}