package com.example.mysqlschemasync.mapper;

import com.example.mysqlschemasync.model.TablesDo;
import lombok.Data;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author steven01.gan
 * @version 1.0
 * @date 2021/8/31-21:23
 * @Description 数据库表信息
 */
public interface TablesMapper extends BaseMapper {

    /**
     * 其实还应该加上 schema的筛选
     *
     * @return
     */
    @Select("select TABLE_SCHEMA,TABLE_NAME,ENGINE,TABLE_ROWS,DATA_LENGTH,INDEX_LENGTH,TABLE_COMMENT from information_schema.TABLES")
    List<TablesDo> selectAllTables();

    @Select("select TABLE_SCHEMA,TABLE_NAME,ENGINE,TABLE_ROWS,DATA_LENGTH,INDEX_LENGTH,TABLE_COMMENT from information_schema.TABLES where TABLE_NAME = #{tableName}")
    List<TablesDo> selectByTableName(String tableName);
}

