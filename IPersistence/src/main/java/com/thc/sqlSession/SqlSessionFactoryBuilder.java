package com.thc.sqlSession;

import com.thc.config.XMLConfigBuilder;
import com.thc.pojo.Configuration;
import org.dom4j.DocumentException;

import java.beans.PropertyVetoException;
import java.io.InputStream;

/**
 * @author : tanghaochen
 * create at:  2020-02-23  18:20
 * @program IPersistence_test
 * @description:
 */
public class SqlSessionFactoryBuilder{

    public SqlSessionFactory build(InputStream inputStream) throws PropertyVetoException, DocumentException {
        // 使用dom4j解析配置文件，将解析出的内容封装到Configuration中
        XMLConfigBuilder xmlConfigBuilder = new XMLConfigBuilder();
        Configuration configuration = xmlConfigBuilder.parseConfig(inputStream);

        // 创建sqlSessionFactory对象，工厂类，生产sqlSeesion会话对象
        DefaultSqlSessionFactory defaultSqlSessionFactory = new DefaultSqlSessionFactory(configuration);

        return defaultSqlSessionFactory;
    }
}
