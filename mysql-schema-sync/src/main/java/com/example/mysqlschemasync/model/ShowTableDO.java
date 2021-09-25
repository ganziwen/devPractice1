package com.example.mysqlschemasync.model;

import lombok.Data;

import java.awt.print.PrinterAbortException;
import java.lang.reflect.ParameterizedType;

/**
 * @author Ganziwen
 * @version 1.0
 * @ClassName ShowTableDO
 * @Description
 * @date 2021/9/25 11:25
 */
@Data
public class ShowTableDO {
    private String table;
    private String createTable;
}
