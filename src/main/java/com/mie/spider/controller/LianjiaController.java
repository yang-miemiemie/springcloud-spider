package com.mie.spider.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.mie.spider.thread.LianjiaThread;

@RestController
@RequestMapping(value="/lianjia")
public class LianjiaController {
	@RequestMapping(value = {"/spider"}, produces="application/json;charset=UTF-8")
	public String spider(HttpServletRequest request) {
		WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(request.getSession().getServletContext());
		LianjiaThread lianjiaThread = (LianjiaThread)context.getBean("lianjiaThread");
		lianjiaThread.start();
		return "start";
	}
}