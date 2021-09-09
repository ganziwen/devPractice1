package com.example.mysqlschemasync.mapper;

import com.example.mysqlschemasync.model.ColumnsDo;
import com.example.mysqlschemasync.model.StatisticsDo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author steven01.gan
 * @version 1.0
 * @date 2021/9/9-20:39
 */
public interface StatisticsMapper extends BaseMapper {
    @Select("SELECT TABLE_SCHEMA,TABLE_NAME,INDEX_NAME,SEQ_IN_INDEX,COLUMN_NAME FROM information_schema.STATISTICS")
    List<StatisticsDo> selectAll();

    @Select("SELECT TABLE_SCHEMA,TABLE_NAME,INDEX_NAME,SEQ_IN_INDEX,COLUMN_NAME FROM information_schema.STATISTICS where 1=1 and table_schema = #{tableSchema}")
    List<StatisticsDo> selectByTableSchema(@Param("tableSchema") String tableSchema);

    @Select("SELECT TABLE_SCHEMA,TABLE_NAME,INDEX_NAME,SEQ_IN_INDEX,COLUMN_NAME FROM information_schema.STATISTICS where 1=1 and table_schema = #{tableSchema} and  TABLE_NAME = #{tableName}")
    List<StatisticsDo> selectByTable(@Param("tableSchema") String tableSchema, @Param("tableName") String tableName);

}
