package com.example.demo.Entity;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="mongoSequence")
public class MongoSequence {

    // 表名
    @Indexed(unique=true)
    private String tableName;

    // 自增的序列
    private int seq;

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public int getSeq() {
        return seq;
    }

    public void setSeq(int seq) {
        this.seq = seq;
    }
}
