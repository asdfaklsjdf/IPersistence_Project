package com.thc.pojo;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author : tanghaochen
 * create at:  2020-02-23  18:11
 * @program IPersistence_test
 * @description:
 */
public class Configuration {

    private DataSource dataSource;

    /**
     * statementId--namespace+id
     */
    Map<String, MappedStatement> map = new HashMap<>();

    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Map<String, MappedStatement> getMap() {
        return map;
    }

    public void setMap(Map<String, MappedStatement> map) {
        this.map = map;
    }
}
