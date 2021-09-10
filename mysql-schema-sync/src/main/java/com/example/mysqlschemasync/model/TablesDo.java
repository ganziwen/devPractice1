package com.example.mysqlschemasync.model;

import lombok.Data;

import java.util.Date;

/**
 * @author steven01.gan
 * @version 1.0
 * @date 2021/8/31-21:24
 */
@Data
public class TablesDo {
    // TABLE_SCHEMA: 数据库名
    // TABLE_NAME : 表名
    // ENGINE : 引擎
    // TABLE_ROWS : 表的行数
    // DATA_LENGTH: 记录表的大小
    // INDEX_LENGTH : 记录表的索引大小
    // TABLE_COMMENT : 记录备注

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
    private String tableType;

    /**
     *
     */
    private String engine;

    /**
     *
     */
    private Long version;

    /**
     *
     */
    private String rowFormat;

    /**
     *
     */
    private Long tableRows;

    /**
     *
     */
    private Long avgRowLength;

    /**
     *
     */
    private Long dataLength;

    /**
     *
     */
    private Long maxDataLength;

    /**
     *
     */
    private Long indexLength;

    /**
     *
     */
    private Long dataFree;

    /**
     *
     */
    private Long autoIncrement;

    /**
     *
     */
    private Date createTime;

    /**
     *
     */
    private Date updateTime;

    /**
     *
     */
    private Date checkTime;

    /**
     *
     */
    private String tableCollation;

    /**
     *
     */
    private Long checksum;

    /**
     *
     */
    private String createOptions;

    /**
     *
     */
    private String tableComment;

    private static final long serialVersionUID = 1L;

}
