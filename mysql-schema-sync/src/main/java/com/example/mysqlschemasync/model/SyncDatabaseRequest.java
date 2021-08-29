package com.example.mysqlschemasync.model;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.Valid;

/**
 * @author Ganziwen
 * @version 1.0
 * @ClassName SyncDatabaseRequest
 * @Description
 * @date 2021/8/29 15:41
 */
@Data
@Valid
public class SyncDatabaseRequest extends SyncInstaceRequest {
    @NotBlank(message = "dbName can't be blank")
    private String dbName;
}
