package com.example.mysqlschemasync.mapper;

import com.example.mysqlschemasync.model.TablesDo;
import lombok.Data;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author steven01.gan
 * @version 1.0
 * @date 2021/8/31-21:23
 * @Description 数据库表信息
 */
public interface TablesMapper extends BaseMapper {

    @Select("select TABLE_SCHEMA,TABLE_NAME,ENGINE,TABLE_ROWS,DATA_LENGTH,INDEX_LENGTH,TABLE_COMMENT from information_schema.TABLES")
    List<TablesDo> selectAllTables();

    @Select("select TABLE_SCHEMA,TABLE_NAME,ENGINE,TABLE_ROWS,DATA_LENGTH,INDEX_LENGTH,TABLE_COMMENT from information_schema.TABLES where 1=1 and table_schema = #{tableSchema} and TABLE_NAME = #{tableName}")
    List<TablesDo> selectByTable(@Param("tableSchema") String tableSchema, @Param("tableName") String tableName);
}

