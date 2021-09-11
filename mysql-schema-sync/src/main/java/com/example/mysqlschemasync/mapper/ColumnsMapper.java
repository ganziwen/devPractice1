package com.example.mysqlschemasync.mapper;

import com.example.mysqlschemasync.model.ColumnsDo;
import com.example.mysqlschemasync.model.TablesDo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Set;

/**
 * @author steven01.gan
 * @version 1.0
 * @date 2021/9/9-20:10
 */
public interface ColumnsMapper extends BaseMapper {
    @Select("select * from information_schema.COLUMNS")
    Set<ColumnsDo> selectAll();

    @Select("SELECT TABLE_SCHEMA,TABLE_NAME,COLUMN_NAME,COLUMN_KEY,COLUMN_TYPE,COLUMN_COMMENT,DATA_TYPE,COLUMN_DEFAULT,CHARACTER_SET_NAME,IS_NULLABLE,NUMERIC_PRECISION,NUMERIC_SCALE FROM information_schema.`COLUMNS` where 1=1 and table_schema = #{tableSchema}")
    Set<ColumnsDo> selectByTableSchema(@Param("tableSchema") String tableSchema);

    @Select("SELECT TABLE_SCHEMA,TABLE_NAME,COLUMN_NAME,COLUMN_KEY,COLUMN_TYPE,COLUMN_COMMENT,DATA_TYPE,COLUMN_DEFAULT,CHARACTER_SET_NAME,IS_NULLABLE,NUMERIC_PRECISION,NUMERIC_SCALE FROM information_schema.`COLUMNS` where 1=1 and table_schema = #{tableSchema} and  TABLE_NAME = #{tableName}")
    Set<ColumnsDo> selectByTable(@Param("tableSchema") String tableSchema, @Param("tableName") String tableName);

}
