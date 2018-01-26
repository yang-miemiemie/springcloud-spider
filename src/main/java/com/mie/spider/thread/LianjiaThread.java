package com.mie.spider.thread;

import java.io.FileWriter;
import java.math.BigDecimal;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.mie.spider.dao.LianjiaDao;
import com.mie.spider.exception.MySpiderException;
import com.mie.spider.listener.MySpiderConectionListener;
import com.mie.spider.pojo.po.Lianjia;

@Component("lianjiaThread")
@Scope("prototype")
public class LianjiaThread extends Thread{
	@Autowired
	LianjiaDao lianjiaDao;
	private static final Logger LOGGER = LoggerFactory.getLogger(LianjiaThread.class);
	
	@Override
	public void run() {
		int errorCount=0;
        WebClient webClient = new WebClient();   
        webClient.getOptions().setRedirectEnabled(true);  
        webClient.getOptions().setJavaScriptEnabled(false); // 设置支持JavaScript
        webClient.addRequestHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/56.0.2924.87 Safari/537.36");
        
        for(int i=1;i<100;i++) {
			String url="https://wh.fang.lianjia.com/loupan/pg"+i+"nht1nhs3/";
	        try {
	        	LOGGER.info("GETTING URL:"+url);
	            HtmlPage htmlPage = webClient.getPage(url);  
	            String pageXml = htmlPage.asXml().toString();  
	            // 得到Document对象
	            Document document = Jsoup.parse(pageXml);
	       
	            // 查找所有img标签
	            Elements lis = document.select("ul#house-lst").first().children();
	            // 遍历img标签并获得src的属性
	            for (Element element : lis) {
	            	String href=element.select("div.pic-panel").first().child(0).attr("href");
	            	String anjukeid=href.substring(8, href.length()-1);
	            	String name=element.getElementsByTag("h2").first().child(0).html();
	            	String[] regin=element.select("span.region").html().split("-");
	            	String area=regin[0];
	            	String address=regin[1];
	            	String[] areastr=element.select("div.area").text().split("-");
	            	String roomcount=areastr[0];
	            	String size=areastr[1];
	            	String price=element.select("span.num").html();
	            	Lianjia entity=new Lianjia(anjukeid, name, area, address, roomcount, size, new BigDecimal(price));
	            	lianjiaDao.save(entity);
	            }
	        }catch(Exception e) {
	        	e.printStackTrace();
	        	break;
	        }
        }
	}
}
