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

    /**
     *
     */
    private String tableCatalog;

    /**
     *
     */
    private String tableSchema;

    /**
     *
     */
    private String tableName;

    /**
     *
     */
    private Long nonUnique;

    /**
     *
     */
    private String indexSchema;

    /**
     *
     */
    private String indexName;

    /**
     *
     */
    private Long seqInIndex;

    /**
     *
     */
    private String columnName;

    /**
     *
     */
    private String collation;

    /**
     *
     */
    private Long cardinality;

    /**
     *
     */
    private Long subPart;

    /**
     *
     */
    private String packed;

    /**
     *
     */
    private String nullable;

    /**
     *
     */
    private String indexType;

    /**
     *
     */
    private String comment;

    /**
     *
     */
    private String indexComment;

    private static final long serialVersionUID = 1L;

}
