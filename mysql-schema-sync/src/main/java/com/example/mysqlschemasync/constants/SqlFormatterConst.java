package com.example.mysqlschemasync.constants;

import org.omg.CosNaming.NamingContextExtPackage.StringNameHelper;

/**
 * @author Ganziwen
 * @version 1.0
 * @ClassName SqlTempleteConst
 * @Description
 * @date 2021/9/11 10:06
 */
public interface SqlFormatterConst {


    /**
     * 修改列模板
     * alter table test_table.tb_user add/modify user_id varchar(64) default '' not null comment '唯一id';
     */
    String COLUMN = "ALTER TABLE {schemaName}.`{tableName}` %s COLUMN `{columnName}` {columnType} {isNullable} default '{columnDefault}' comment '{columnComment}';";
    /**
     * 修改列
     */
    String MODIFY_COLUMN = String.format(COLUMN, "MODIFY");
    /**
     * 新增列
     */
    String ADD_COLUMN = String.format(COLUMN, "ADD");

    /**
     * 修改索引,索引可以随意删除重建,线下数据量也不会很大
     */
    String MODIFY_INDEX = "ALTER TABLE {schemaName}.`{tableName}` DROP INDEX {indexName};ALTER TABLE {schemaName}.`{tableName}` ADD INDEX {indexName}(`{columnName}`);";
    String MODIFY_PRIMARY_KEY = "ALTER TABLE {schemaName}.`{tableName}` DROP PRIMARY KEY;ALTER TABLE {schemaName}.`{tableName}` ADD PRIMARY KEY (`{columnName}`);";
    String MODIFY_UNIQUE_INDEX = "ALTER TABLE {schemaName}.`{tableName}` DROP KEY {indexName};ALTER TABLE {schemaName}.`{tableName}` ADD UNIQUE {indexName}(`{columnName}`);";
    String MODIFY_FULLTEXT = "ALTER {schemaName}.`{tableName}` DROP INDEX {indexName};ALTER TABLE {schemaName}.`{tableName}` ADD FULLTEXT {indexName}(`{columnName}`);";
}
