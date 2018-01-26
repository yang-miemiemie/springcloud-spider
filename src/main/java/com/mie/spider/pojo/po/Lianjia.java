package com.mie.spider.pojo.po;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "lianjia")  
public class Lianjia {
	@Id
	private String id;
	private String anjukeid;
	private String name;
	private String area;
	private String address;
	private String roomcount;
	private String size;
	private String createdate;
	private BigDecimal price;
	public String getAnjukeid() {
		return anjukeid;
	}
	public void setAnjukeid(String anjukeid) {
		this.anjukeid = anjukeid;
	}
	public String getCreatedate() {
		return createdate;
	}
	public void setCreatedate(String createdate) {
		this.createdate = createdate;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getRoomcount() {
		return roomcount;
	}
	public void setRoomcount(String roomcount) {
		this.roomcount = roomcount;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public Lianjia(String anjukeid, String name, String area, String address, String roomcount, String size, BigDecimal price) {
		this.anjukeid = anjukeid;
		this.name = name;
		this.area = area;
		this.address = address;
		this.roomcount = roomcount;
		this.size = size;
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");     
		this.createdate = sdf.format(new Date());
		this.price = price;
	}
	
	
}
