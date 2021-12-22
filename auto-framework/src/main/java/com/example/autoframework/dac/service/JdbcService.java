package com.example.autoframework.dac.service;

import cn.hutool.log.StaticLog;
import com.dingtalk.api.request.OapiBipaasDiAgentRequest;
import com.example.autoframework.dac.factory.DataSourceFactory;
import com.example.autoframework.model.FieldEntity;
import com.example.autoframework.model.RowEntity;
import com.example.autoframework.util.RequiredUtils;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.net.ssl.ExtendedSSLSession;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Ganziwen
 * @version 1.0
 * @ClassName JdbcService
 * @Description
 * @date 2021/12/22 17:07
 */
public final class JdbcService {
    /**
     * 改数据
     *
     * @param sql
     * @param params
     * @return
     */
    public int modify(String sql, Map<String, Object> params) {
        RequiredUtils.requireNotNullOrEmpty(sql, "sql should not be null or empty");
        NamedParameterJdbcTemplate jdbcTemplate = new NamedParameterJdbcTemplate(DataSourceFactory.of().getDataSource());
        int effectRows = jdbcTemplate.update(sql, params);
        StaticLog.info("sql = %s,effectRows = %s", sql, effectRows);
        return effectRows;
    }

    /**
     * 查数据
     *
     * @param sql
     * @param params
     * @return
     */
    public List<RowEntity> query(String sql, Map<String, Object> params) {
        RequiredUtils.requireNotNullOrEmpty(sql, "sql should not be null or empty");
        NamedParameterJdbcTemplate jdbcTemplate = new NamedParameterJdbcTemplate(DataSourceFactory.of().getDataSource());
        List<Map<String, Object>> mapList = jdbcTemplate.queryForList(sql, params);
        return mapList.stream().map(data -> {

            List<FieldEntity> fieldEntities = data
                    .entrySet()
                    .stream()
                    .map(entry -> FieldEntity.builder().fieldName(entry.getKey()).fieldValue(entry.getValue()).build())
                    .collect(Collectors.toList());
            return RowEntity.builder().fields(fieldEntities).build();
        }).collect(Collectors.toList());

    }

}
