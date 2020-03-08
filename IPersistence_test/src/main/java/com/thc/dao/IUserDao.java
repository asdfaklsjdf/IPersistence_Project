package com.thc.dao;

import com.thc.pojo.User;

import java.util.List;

/**
 * @author : tanghaochen
 * create at:  2020-03-01  20:40
 * @program IPersistence_test
 * @description:
 */
public interface IUserDao {

    List<User> findAll() throws Exception;

    User findByCondition(User user) throws Exception;

    Integer modifyUser(User user) throws Exception;

    Integer addUser(User user) throws Exception;

    Integer updateUser(User user) throws Exception;

    Integer daleteUser(User user) throws Exception;
}
