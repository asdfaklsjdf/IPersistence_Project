package com.thc.sqlSession;

import com.thc.pojo.Configuration;

/**
 * @author : tanghaochen
 * create at:  2020-02-23  19:50
 * @program IPersistence_test
 * @description:
 */
public class DefaultSqlSessionFactory implements SqlSessionFactory{

    private Configuration configuration;

    public DefaultSqlSessionFactory(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public SqlSession openSession() {
        return new DefaultSqlSession(configuration);
    }
}
