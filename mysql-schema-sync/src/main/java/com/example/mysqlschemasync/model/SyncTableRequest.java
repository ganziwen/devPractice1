package com.example.mysqlschemasync.model;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.Valid;

/**
 * @author Ganziwen
 * @version 1.0
 * @ClassName SyncTableRequest
 * @Description
 * @date 2021/8/29 15:42
 */
@Data
@Valid
public class SyncTableRequest extends SyncDatabaseRequest {
    @NotBlank(message = "tableName can't be blank")
    private String tableName;
}
