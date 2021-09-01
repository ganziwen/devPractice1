package com.example.mysqlschemasync.mapper;

import com.example.mysqlschemasync.model.SchemataDo;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author Ganziwen
 * @version 1.0
 * @ClassName SchemaMapper
 * @Description 数据库信息
 * @date 2021/8/29 17:15
 */
public interface SchemaMapper extends BaseMapper {
    @Select("select SCHEMA_NAME,DEFAULT_CHARACTER_SET_NAME from information_schema.SCHEMATA")
    List<SchemataDo> selectAllSchame();

    @Select("select SCHEMA_NAME,DEFAULT_CHARACTER_SET_NAME from information_schema.SCHEMATA where SCHEMA_NAME = #{schemaName}")
    List<SchemataDo> selectSchameByName(String schemaName);
}
