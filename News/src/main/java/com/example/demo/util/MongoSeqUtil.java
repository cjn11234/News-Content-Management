package com.example.demo.util;


import com.example.demo.Entity.MongoSequence;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

public class MongoSeqUtil {

    public static int getNextSeq(MongoTemplate mongoTemplate, Class<?> clazz) {
        Document document = clazz.getAnnotation(Document.class);
        if (document == null) {
            throw new RuntimeException("请传入被@Document注解的类");
        }
        String tableName = document.collection();
        if (tableName.trim().length() == 0) {
            throw new RuntimeException("Document注解的表名不能为空");
        }
        Query query = new Query(Criteria.where("tableName").is(tableName));
        Update update = new Update();
        update.inc("seq", 1); // 自增1，也可以作为参数传进来
        FindAndModifyOptions options = new FindAndModifyOptions();
        options.upsert(true); // 设置为不存在，则插入
        options.returnNew(true); // 设置为返回更新后的数据
        MongoSequence mongoSequence = mongoTemplate.findAndModify(query, update, options, MongoSequence.class);
        return mongoSequence.getSeq();
    }
}
