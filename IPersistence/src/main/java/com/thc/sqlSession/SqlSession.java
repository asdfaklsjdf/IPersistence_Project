package com.thc.sqlSession;

import java.util.List;

public interface SqlSession {

    //查询所有
    public <E> List<E> selectList(String statementId, Object... params) throws Exception;

    //条件单条查询
    public <T> T selectOne(String statement, Object... param) throws Exception;

    //为dao接口生成代理实现类
    public <T> T getMapper(Class<?> mapperClass);

    //修改操作
    public Integer modifyUser(String statement, Object... params) throws Exception;

    public Integer addUser(String statement, Object... params) throws Exception;

    public Integer updateUser(String statement, Object... params) throws Exception;

    public Integer daleteUser(String statement, Object... params) throws Exception;


}
