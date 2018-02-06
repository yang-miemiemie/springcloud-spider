package com.mie.spider.pojo.po;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "lianjia_old")  
public class LianjiaOld {
	@Id
	private String id;
	private String lianjiaid;
	private String region;
	private String viliage;
	private String area;
	private int roomcount;
	private int hallcount;
	private BigDecimal price;
	private String publicdate;
	private String link;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getLianjiaid() {
		return lianjiaid;
	}
	public void setLianjiaid(String lianjiaid) {
		this.lianjiaid = lianjiaid;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public String getViliage() {
		return viliage;
	}
	public void setViliage(String viliage) {
		this.viliage = viliage;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public int getRoomcount() {
		return roomcount;
	}
	public void setRoomcount(int roomcount) {
		this.roomcount = roomcount;
	}
	public int getHallcount() {
		return hallcount;
	}
	public void setHallcount(int hallcount) {
		this.hallcount = hallcount;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public String getPublicdate() {
		return publicdate;
	}
	public void setPublicdate(String publicdate) {
		this.publicdate = publicdate;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public LianjiaOld(String lianjiaid, String region, String viliage, String area, int roomcount, int hallcount,
			BigDecimal price, String publicdate, String link) {
		super();
		this.lianjiaid = lianjiaid;
		this.region = region;
		this.viliage = viliage;
		this.area = area;
		this.roomcount = roomcount;
		this.hallcount = hallcount;
		this.price = price;
		this.publicdate = publicdate;
		this.link = link;
	}
	
}
