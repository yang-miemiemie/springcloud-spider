package com.mie.spider.thread.pipeline;

import java.math.BigDecimal;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import com.mie.spider.dao.LianjiaOldDao;
import com.mie.spider.pojo.po.LianjiaOld;
import com.mie.util.ApplicationContextHolder;

import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

public class LianjiaOldPipeline implements Pipeline{

	LianjiaOldDao dao;
	
	public LianjiaOldPipeline() {
		dao = (LianjiaOldDao)ApplicationContextHolder.getBean("lianjiaOldDao");
	}
	
	@Override
	public void process(ResultItems resultItems, Task task) {
		String lianjiaid=resultItems.get("no");
		if(lianjiaid!=null && !lianjiaid.equals("null")) {
			String link= resultItems.get("link");
			String viliage=resultItems.get("viliage");
			int roomcount=resultItems.get("roomcount");
			int hallcount=resultItems.get("hallcount");
			double price=resultItems.get("price");
			String region=resultItems.get("region");
			String area=resultItems.get("area");
			String publicdate=resultItems.get("publicdate");
			
			LianjiaOld entity=new LianjiaOld(lianjiaid, region, viliage, area, roomcount, hallcount, BigDecimal.valueOf(price), publicdate, link);
			dao.save(entity);
		}
		
	}

}
