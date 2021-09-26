package com.example.mysqlschemasync.mapper;

import com.example.mysqlschemasync.model.SchemataDo;
import com.example.mysqlschemasync.model.ShowTableDO;
import com.example.mysqlschemasync.model.TablesDo;
import lombok.Data;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Set;

/**
 * @author steven01.gan
 * @version 1.0
 * @date 2021/8/31-21:23
 * @Description 数据库表信息
 */
public interface TablesMapper extends BaseMapper {

    @Select("select TABLE_SCHEMA,TABLE_NAME,ENGINE,TABLE_ROWS,DATA_LENGTH,INDEX_LENGTH,TABLE_COMMENT from information_schema.TABLES")
    Set<TablesDo> selectAllTables();

    @Select("select TABLE_SCHEMA,TABLE_NAME,ENGINE,TABLE_ROWS,DATA_LENGTH,INDEX_LENGTH,TABLE_COMMENT from information_schema.TABLES where 1=1 and table_schema = #{tableSchema} and TABLE_NAME = #{tableName}")
    Set<TablesDo> selectByTable(@Param("tableSchema") String tableSchema, @Param("tableName") String tableName);

    @Select("select TABLE_SCHEMA,TABLE_NAME,TABLE_TYPE,TABLE_COMMENT from information_schema.TABLES where TABLE_SCHEMA = #{schemaName}")
    Set<TablesDo> selectSchameByDataBaseName(@Param("schemaName") String schemaName);

    @Select("show create table `#{schemaName}`.`#{tableName}`;")
    Set<ShowTableDO> showTables(@Param("schemaName") String schemaName, @Param("tableName") String tableName);
}

