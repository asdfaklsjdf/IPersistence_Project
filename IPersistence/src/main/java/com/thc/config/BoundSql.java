package com.thc.config;

import com.thc.utils.ParameterMapping;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : tanghaochen
 * create at:  2020-03-01  18:27
 * @program IPersistence_test
 * @description:
 */
public class BoundSql {

    /**
     * 解析出的sql
     */
    private String sqltext;

    private List<ParameterMapping> list = new ArrayList<>();

    public BoundSql(String sqltext, List<ParameterMapping> list) {
        this.sqltext = sqltext;
        this.list = list;
    }

    public String getSqltext() {
        return sqltext;
    }

    public void setSqltext(String sqltext) {
        this.sqltext = sqltext;
    }

    public List<ParameterMapping> getList() {
        return list;
    }

    public void setList(List<ParameterMapping> list) {
        this.list = list;
    }
}
