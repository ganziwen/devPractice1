package com.example.mysqlschemasync.mapper;

import com.example.mysqlschemasync.model.SchemataDo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Set;

/**
 * @author Ganziwen
 * @version 1.0
 * @ClassName SchemaMapper
 * @Description 数据库信息
 * @date 2021/8/29 17:15
 */
public interface SchemaMapper extends BaseMapper {
    @Select("select SCHEMA_NAME,DEFAULT_CHARACTER_SET_NAME from information_schema.SCHEMATA")
    Set<SchemataDo> selectAllSchame();

    @Select("select SCHEMA_NAME,DEFAULT_CHARACTER_SET_NAME from information_schema.SCHEMATA where SCHEMA_NAME = #{schemaName}")
    Set<SchemataDo> selectSchameByName(@Param("schemaName") String schemaName);


}
