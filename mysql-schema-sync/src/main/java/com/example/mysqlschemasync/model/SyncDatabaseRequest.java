package com.example.mysqlschemasync.model;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @author Ganziwen
 * @version 1.0
 * @ClassName SyncDatabaseRequest
 * @Description
 * @date 2021/8/29 15:41
 */
@Data
public class SyncDatabaseRequest extends SyncInstaceRequest {
    @NotBlank(message = "dbName can't not be blank")
    private String dbName;
}
