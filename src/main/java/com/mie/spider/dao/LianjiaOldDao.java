package com.mie.spider.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import com.mie.spider.pojo.po.Lianjia;
import com.mie.spider.pojo.po.LianjiaOld;

@Repository("lianjiaOldDao")
public class LianjiaOldDao {

    @Autowired
    private MongoTemplate mongoTemplate;

	public void save(LianjiaOld entity) {
		mongoTemplate.save(entity);
	}
}
