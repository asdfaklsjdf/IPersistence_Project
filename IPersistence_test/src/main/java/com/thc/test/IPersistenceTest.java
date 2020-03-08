package com.thc.test;

import com.thc.dao.IUserDao;
import com.thc.io.Resources;
import com.thc.pojo.User;
import com.thc.sqlSession.SqlSession;
import com.thc.sqlSession.SqlSessionFactory;
import com.thc.sqlSession.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.InputStream;
import java.util.List;

/**
 * @author : tanghaochen
 * create at:  2020-02-22  23:04
 * @program IPersistence_test
 * @description:
 */
public class IPersistenceTest {

    @Test
    public void test() throws Exception {
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        User user = new User();
        user.setId(1);
        user.setUsername("thc1");


        IUserDao userDao = sqlSession.getMapper(IUserDao.class);
        List<User> all = userDao.findAll();
        for (User user1 : all) {
            System.out.println(user1);
        }
//        System.out.println(userDao.addUser(user));
        System.out.println(userDao.updateUser(user));
        System.out.println(userDao.daleteUser(user));

    }
}
