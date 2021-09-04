package com.example.mysqlschemasync.mapper;

import com.example.mysqlschemasync.model.ColumnsDo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author Ganziwen
 * @version 1.0
 * @ClassName ColumnMapper
 * @Description
 * @date 2021/9/4 11:18
 */
public interface ColumnMapper extends BaseMapper {

    @Select("select TABLE_SCHEMA,TABLE_NAME,COLUMN_NAMEl,COLUMN_KEY,COLUMN_TYPE,COLUMN_COMMENT,DATA_TYPE,COLUMN_DEFAULT,CHARACTER_SET_NAME,IS_NULLABLE,NUMERIC_PRECISION,NUMERIC_SCALE from information_schame.columns")
    List<ColumnsDo> selectAll();

    List<ColumnsDo> selectByTableSchema(String tableSchema);

    @Select("select TABLE_SCHEMA,TABLE_NAME,COLUMN_NAME,COLUMN_KEY,COLUMN_TYPE,COLUMN_COMMENT,DATA_TYPE,COLUMN_DEFAULT,CHARACTER_SET_NAME,IS_NULLABLE,NUMERIC_PRECISION,NUMERIC_SCALE from information_schema.columns where 1=1 and `table_schema`= #{tableSchema} and `table_name` = #{tableName}")
    List<ColumnsDo> selectByTable(@Param("tableSchema") String tableSchema, @Param("tableName") String tableName);

}
