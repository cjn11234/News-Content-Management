package com.example.demo.Repository;

import com.example.demo.Entity.New;
import com.example.demo.util.MongoSeqUtil;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

import java.util.List;

@Repository
public class NewRepository {
    @Resource
    private MongoTemplate mongoTemplate;

    public void save(New article){
        article.setId(MongoSeqUtil.getNextSeq(mongoTemplate,New.class));
        mongoTemplate.save(article);

    }
    public New findByTitle(String title){
        Query query=new Query(Criteria.where("title").is(title));
        return mongoTemplate.findOne(query,New.class);
    }
    public List<New> findAll(){
        return mongoTemplate.findAll(New.class);
    }
    public  List<New> findByName(String name){
        Query query = new Query(Criteria.where("author").is(name));
        return mongoTemplate.find(query, New.class);

    }
    public boolean existsBytitle(String title){
        Query query=new Query(Criteria.where("title").is(title));
        return mongoTemplate.exists(query,New.class);
    }
    public List<New> findByString(String name){
        Query query=new Query(Criteria.where("title").regex(".*?"+name+".*","i"));
        return mongoTemplate.find(query,New.class);
    }

}
