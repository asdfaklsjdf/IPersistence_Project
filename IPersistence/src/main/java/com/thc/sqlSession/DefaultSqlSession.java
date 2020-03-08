package com.thc.sqlSession;

import com.thc.pojo.Configuration;
import com.thc.pojo.MappedStatement;

import java.lang.reflect.*;
import java.util.List;

/**
 * @author : tanghaochen
 * create at:  2020-02-23  19:54
 * @program IPersistence_test
 * @description:
 */
public class DefaultSqlSession implements SqlSession {

    private Configuration configuration;

    public DefaultSqlSession(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public <E> List<E> selectList(String statementId, Object... params) throws Exception {
        SimpleExecutor executor = new SimpleExecutor();
        MappedStatement mappedStatement = configuration.getMap().get(statementId);
        List<Object> query = (List<Object>) executor.query(configuration, mappedStatement, params);
        return (List<E>) query;
    }

    @Override
    public <T> T selectOne(String statement, Object... param) throws Exception {
        List<Object> list = selectList(statement, param);
        if (list.size() == 1) {
            return (T) list.get(0);
        } else {
            throw new RuntimeException("查询结果为空或者查询结果过多");
        }
    }

    @Override
    public <T> T getMapper(Class<?> mapperClass) {
        //使用jdk动态代理，为Dao生成代理对象

        Object o = Proxy.newProxyInstance(DefaultSqlSession.class.getClassLoader(), new Class[]{mapperClass},
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

                        String methodName = method.getName();
                        String className = method.getDeclaringClass().getName();
                        String statementId = className + "." + methodName;
                        Type genericReturnType = method.getGenericReturnType();
                        if (genericReturnType instanceof ParameterizedType) {
                            return selectList(statementId, args);
                        } else if (genericReturnType.getTypeName().equalsIgnoreCase("java.lang.Integer")) {
                            return modifyUser(statementId, args);
                        } else {
                            return selectOne(statementId, args);
                        }

                    }
                });
        return (T) o;

    }

    @Override
    public Integer modifyUser(String statement, Object... params) throws Exception {
        SimpleExecutor executor = new SimpleExecutor();
        MappedStatement mappedStatement = configuration.getMap().get(statement);
        return (Integer) executor.query(configuration, mappedStatement, params);
    }


    @Override
    public Integer addUser(String statement, Object... params) throws Exception {
        return modifyUser(statement, params);
    }

    @Override
    public Integer updateUser(String statement, Object... params) throws Exception {
        return modifyUser(statement, params);
    }

    @Override
    public Integer daleteUser(String statement, Object... params) throws Exception {
        return modifyUser(statement, params);
    }
}
