package com.thc.sqlSession;

import com.thc.config.BoundSql;
import com.thc.pojo.Configuration;
import com.thc.pojo.MappedStatement;
import com.thc.utils.GenericTokenParser;
import com.thc.utils.ParameterMapping;
import com.thc.utils.ParameterMappingTokenHandler;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;

/**
 * @author : tanghaochen
 * create at:  2020-02-23  20:21
 * @program IPersistence_test
 * @description:
 */
public class SimpleExecutor implements Executor {

    @Override
    public Object query(Configuration configuration, MappedStatement statement, Object... param) throws Exception {
        //注册驱动，获取链接
        Connection connection = configuration.getDataSource().getConnection();

        //获取sql 替换#{}中的内容
        String sql = statement.getSql();
        BoundSql boundSql = getBoundSql(sql);

        //获取预处理对象
        PreparedStatement preparedStatement = connection.prepareStatement(boundSql.getSqltext());
        String paramterType = statement.getParamterType();
        Class<?> paramterTypeClass = getClassType(paramterType);
        //设置参数
        List<ParameterMapping> list = boundSql.getList();
        for (int i = 0; i < list.size(); i++) {
            ParameterMapping mapping = list.get(i);
            String content = mapping.getContent();
            //反射
            Field declaredField = paramterTypeClass.getDeclaredField(content);
            //访问私有属性
            declaredField.setAccessible(true);
            Object o = declaredField.get(param[0]);
            preparedStatement.setObject(i + 1, o);
        }
        ResultSet resultSet = null;
        if(boundSql.getSqltext().trim().toLowerCase().startsWith("insert") ||
                boundSql.getSqltext().trim().toLowerCase().startsWith("delete") ||
                boundSql.getSqltext().trim().toLowerCase().startsWith("update")) {
            return preparedStatement.executeUpdate();
        } else {
            //执行sql
            resultSet = preparedStatement.executeQuery();


            String resultType = statement.getResultType();
            Class<?> resultTypeClass = getClassType(resultType);
            ArrayList<Object> objects = new ArrayList<>();

            //封装结果集
            while (resultSet.next()) {
                Object o = resultTypeClass.newInstance();
                //元数据
                ResultSetMetaData metaData = resultSet.getMetaData();
                for (int i = 1; i < metaData.getColumnCount() + 1; i++) {
                    //字段名
                    String columnName = metaData.getColumnName(i);
                    //字段值
                    Object object = resultSet.getObject(i);
                    //使用反射，封装结果集
                    PropertyDescriptor propertyDescriptor = new PropertyDescriptor(columnName, resultTypeClass);
                    Method writeMethod = propertyDescriptor.getWriteMethod();
                    writeMethod.invoke(o, object);
                }
                objects.add(o);
            }

            return objects;
        }
    }

    private Class<?> getClassType(String paramterType) throws ClassNotFoundException {
        if (paramterType != null) {
            Class<?> type = Class.forName(paramterType);
            return type;
        }
        return null;
    }

    /**
     * 解析sql中的#{}->? 解析{}中的值进行存储
     *
     * @param sql
     * @return
     */
    private BoundSql getBoundSql(String sql) {
        //标记处理器
        ParameterMappingTokenHandler parameterMappingTokenHandler = new ParameterMappingTokenHandler();
        GenericTokenParser genericTokenParser = new GenericTokenParser("#{", "}", parameterMappingTokenHandler);
        //解析出的sql
        String parseSql = genericTokenParser.parse(sql);
        //解析出来的参数名称
        List<ParameterMapping> parameterMappings = parameterMappingTokenHandler.getParameterMappings();

        BoundSql boundSql = new BoundSql(parseSql, parameterMappings);
        return boundSql;
    }
}
