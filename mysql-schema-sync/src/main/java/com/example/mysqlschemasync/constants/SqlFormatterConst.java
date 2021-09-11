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
     * 修改列
     * alter table test_table.tb_user add/modify user_id varchar(64) default '' not null comment '唯一id';
     */
    String COLUMN = "ALTER TABLE {schemaName}.`{tableName}` %s COLUMN `{columnName}` {columnType} {isNullable} default '{columnDefault}' comment '{columnComment}';";
    String MODIFY_COLUMN = String.format(COLUMN, "MODIFY");
    String ADD_COLUMN = String.format(COLUMN, "ADD");

    /**
     * 删除索引
     */
    String DROP_INDEX = "ALTER TABLE {schemaName}.`{tableName}` DROP INDEX {indexName};";
    /**
     * 添加索引
     */
    String ADD_INDEX = "ALTER TABLE {schemaName}.`{tableName}` ADD INDEX {indexName}(columnName);";

    /**
     * 删除主键
     */
    String DROP_UNIQUEKEY = "ALTER TABLE {schemaName}.`{tableName}` DROP PRIMARY KEY;";

    /**
     * 新增主键
     */
    String ADD_UNIQUEKEY = "ALTER TABLE {schemaName}.`{tableName}` ADD PRIMARY KEY ({columnName}});";

}
