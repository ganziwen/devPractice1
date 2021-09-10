package com.example.mysqlschemasync.model;


import lombok.Data;

@Data
public class SchemataDo {

    /**
     *
     */
    private String catalogName;

    /**
     *
     */
    private String schemaName;

    /**
     *
     */
    private String defaultCharacterSetName;

    /**
     *
     */
    private String defaultCollationName;

    /**
     *
     */
    private String sqlPath;
    private static final long serialVersionUID = 1L;

}
