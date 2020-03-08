package com.thc.sqlSession;

import com.thc.pojo.Configuration;
import com.thc.pojo.MappedStatement;

public interface Executor {

    public Object query(Configuration configuration, MappedStatement statement, Object... param) throws Exception;
}
