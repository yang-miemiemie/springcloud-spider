package com.mie.spider.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import com.mie.spider.pojo.po.Lianjia;

@Repository
public class LianjiaDao {

    @Autowired
    private MongoTemplate mongoTemplate;

	public void save(Lianjia anjuke) {
		mongoTemplate.save(anjuke);
	}
}
