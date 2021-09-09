package com.example.mysqlschemasync.model;

import lombok.Data;

/**
 * @author steven01.gan
 * @version 1.0
 * @date 2021/9/9-20:37
 * 索引
 */
@Data
public class StatisticsDo {
    // TABLE_SCHEMA: 数据库名
    // TABLE_NAME: 表名
    // INDEX_NAME: 索引名
    // SEQ_IN_INDEX: 字段在索引中的顺序
    // COLUMN_NAME: 字段名

    private String tableSchema;
    private String tableName;
    private String indexName;
    private String seqInIndex;
    private String columnName;
}
