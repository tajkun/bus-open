package com.langting.busopen.service;

import com.langting.busopen.entity.User;
import com.langting.busopen.exception.BusOpenException;

import java.util.List;

/**
 * @program: bus-open
 * @description:
 * @author: Jiakun
 * @create: 2020-08-10 14:10
 **/
public interface IUserService {

    int addUser(User user) throws BusOpenException;
    int updateUser(User user) throws BusOpenException;
    int deleteUser(Long id) throws BusOpenException;
    List<User> allUsers();
    User getUserById(Long id);
    User getUserByUsername(String name);
    User getUserByMobile(String mobile);
    User getUserByEmail(String email);

}