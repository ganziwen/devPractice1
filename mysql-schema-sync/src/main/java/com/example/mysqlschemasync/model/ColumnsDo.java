package com.example.mysqlschemasync.model;

import lombok.Data;

/**
 * @author Ganziwen
 * @version 1.0
 * @ClassName ColumnsDo
 * @Description
 * @date 2021/9/4 11:02
 */
@Data
public class ColumnsDo {
    // TABLE_SCHEMA: 数据库名
    // TABLE_NAME: 表名
    // COLUMN_NAME: 列名
    // COLUMN_KEY: 列对应索引类型 PRI,MUL
    // COLUMN_TYPE: 列的数据类型 char varchar int bigint
    // COLUMN_COMMENT: 注释信息
    // DATA_TYPE: 列数据类型
    // COLUMN_DEFAULT:列中默认值
    // CHARACTER_SET_NAME: 数据库对应字符列默认字符编码, utf8
    // IS_NULLABLE: 是否为null
    // NUMERIC_PRECISION: 列中数字长度(整数部分)
    // NUMERIC_SCALE: 列中数字长度(浮点数部分)
    private String tableSchema;
    private String tableName;
    private String columnName;
    private String columnKey;
    private String columnType;
    private String columnComment;
    private String columnDefault;
    private String characterSetName;
    private String isNullable;
    private String numericPrecision;
    private String numericScale;
}
