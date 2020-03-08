package com.thc.dao;//package com.com.thc.dao;
//
//import com.com.thc.io.Resources;
//import com.com.thc.pojo.User;
//import com.com.thc.sqlSession.SqlSession;
//import com.com.thc.sqlSession.SqlSessionFactory;
//import com.com.thc.sqlSession.SqlSessionFactoryBuilder;
//
//import java.io.InputStream;
//import java.util.List;
//
///**
// * @author : tanghaochen
// * create at:  2020-03-01  20:42
// * @program IPersistence_test
// * @description:
// */
//public class UserDaoImpl implements IUserDao {
//
//    @Override
//    public List<User> findAll() throws Exception{
//        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
//        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
//        SqlSession sqlSession = sqlSessionFactory.openSession();
//        User user = new User();
////        user.setId("1");
////        user.setUsername("张三");
//        List<User> users = sqlSession.selectList("user.selectList", null);
//        return users;
//
//    }
//
//    @Override
//    public User findByCondition(User user) throws Exception{
//        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
//        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
//        SqlSession sqlSession = sqlSessionFactory.openSession();
//        User userResult = sqlSession.selectOne("user.selectOne", user);
//        return userResult;
//    }
//}
