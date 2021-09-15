package com.example.mysqlschemasync.mapper;

import com.example.mysqlschemasync.model.ColumnsDo;
import com.example.mysqlschemasync.model.StatisticsDo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Set;

/**
 * @author steven01.gan
 * @version 1.0
 * @date 2021/9/9-20:39
 */
public interface StatisticsMapper extends BaseMapper {
    @Select("SELECT TABLE_SCHEMA,TABLE_NAME,INDEX_NAME,SEQ_IN_INDEX,COLUMN_NAME FROM information_schema.STATISTICS")
    Set<StatisticsDo> selectAll();

    @Select("SELECT TABLE_SCHEMA,TABLE_NAME,INDEX_NAME,SEQ_IN_INDEX,COLUMN_NAME FROM information_schema.STATISTICS where 1=1 and table_schema = #{tableSchema}")
    Set<StatisticsDo> selectByTableSchema(@Param("tableSchema") String tableSchema);

    @Select("SELECT TABLE_SCHEMA,TABLE_NAME,INDEX_NAME,SEQ_IN_INDEX,COLUMN_NAME FROM information_schema.STATISTICS where 1=1 and table_schema = #{tableSchema} and  TABLE_NAME = #{tableName}")
    Set<StatisticsDo> selectByTable(@Param("tableSchema") String tableSchema, @Param("tableName") String tableName);

    /**
     * 将索引进行聚合
     *
     * @param tableSchema
     * @param tableName
     * @return
     */
    @Select("select any_value(TABLE_SCHEMA)  as TABLE_SCHEMA,any_value(TABLE_NAME) as TABLE_NAME,any_value(NON_UNIQUE) as NON_UNIQUE,any_value(INDEX_NAME) as INDEX_NAME,any_value(SEQ_IN_INDEX) as SEQ_IN_INDEX,group_concat(COLUMN_NAME order by SEQ_IN_INDEX asc separator ',') as COLUMN_NAME,any_value(INDEX_TYPE) as INDEX_TYPE from information_schema.STATISTICS where 1=1 and table_schema = #{tableSchema} and  TABLE_NAME = #{tableName} group by INDEX_NAME;")
    Set<StatisticsDo> selectByTableGroupBy(@Param("tableSchema") String tableSchema, @Param("tableName") String tableName);
}
