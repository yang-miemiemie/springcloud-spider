package com.mie.spider.listener;

import java.io.IOException;
import java.net.ConnectException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebRequest;
import com.gargoylesoftware.htmlunit.WebResponse;
import com.gargoylesoftware.htmlunit.util.FalsifyingWebConnection;
import com.mie.spider.exception.MySpiderException;

public class MySpiderConectionListener extends FalsifyingWebConnection {  
	  
	private static final Logger LOGGER = LoggerFactory.getLogger(MySpiderConectionListener.class);  
  
    public MySpiderConectionListener(WebClient webClient) throws IllegalArgumentException {  
        super(webClient);  
    }  
  
    @Override  
    public WebResponse getResponse(WebRequest request) throws IOException{  
        // 得到了这个响应，你想怎么处理就怎么处理了，不多写了  
  
        WebResponse response = super.getResponse(request);  
        String url = response.getWebRequest().getUrl().toString();  
  
        if(response.getStatusCode()==200) {
            return response;  
        }else{
        	System.out.println(response.getStatusCode());
        	throw new MySpiderException();
        }
    }  
  
    private boolean check(String url) {  
        // TODO 加入你自己的判断什么的  
        return false;  
    }  
  
}  