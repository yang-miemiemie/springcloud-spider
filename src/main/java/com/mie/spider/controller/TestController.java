package com.mie.spider.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.mie.spider.thread.LianjiaThread;

@RestController
@RequestMapping(value="/test")
public class TestController {
	@RequestMapping(value = {"/isRun"}, produces="application/json;charset=UTF-8")
	public String do_test(HttpServletRequest request) {
		return "RUNNING";
	}
}