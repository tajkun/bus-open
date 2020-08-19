package com.langting.busopen.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.langting.busopen.constant.CommonStatus;
import com.langting.busopen.constant.Constants;
import com.langting.busopen.entity.User;
import com.langting.busopen.exception.BusOpenException;
import com.langting.busopen.mapper.UserMapper;
import com.langting.busopen.service.IUserService;
import com.langting.busopen.utils.CommonUtils;
import com.langting.busopen.utils.KeyGenerator;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @program: bus-open
 * @description:
 * @author: Jiakun
 * @create: 2020-08-10 14:18
 **/
@Service
public class UserServiceImpl implements IUserService {

    private final UserMapper userMapper;

    final SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Autowired
    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    @Transactional
    public int addUser(User user) throws BusOpenException {
        if (user.validate()) {
            throw new BusOpenException(Constants.ErrorMsg.REQUEST_PARAM_ERROR);
        }

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", user.getUsername());
        User oldUser = userMapper.selectOne(queryWrapper);
        if (oldUser != null) {
            throw new BusOpenException(Constants.ErrorMsg.SAME_NAME_USER_ERROR);
        }

        queryWrapper.clear();
        queryWrapper.eq("mobile", user.getMobile());
        User oldUser1 = userMapper.selectOne(queryWrapper);
        if (oldUser1 != null) {
            throw new BusOpenException(Constants.ErrorMsg.SAME_MOBILE_USER_ERROR);
        }

        queryWrapper.clear();
        queryWrapper.eq("email", user.getEmail());
        User oldUser2 = userMapper.selectOne(queryWrapper);
        if (oldUser2 != null) {
            throw new BusOpenException(Constants.ErrorMsg.SAME_EMAIL_USER_ERROR);
        }

        user.setAccessKey(KeyGenerator.getAccessKey());
        user.setPassword(CommonUtils.md5(user.getPassword()));
        user.setRole(0);
        user.setStatus(CommonStatus.VALID.getStatusCode());
        user.setCreateTime(df.format(new Date()));
        user.setUpdateTime(user.getCreateTime());
        System.out.println("^^^^^^^"+user);
        return userMapper.insert(user);
    }

    @Override
    @Transactional
    public int updateUser(User user) throws BusOpenException {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", user.getId());
        User oldUser = userMapper.selectOne(queryWrapper);
        if (oldUser == null) {
            throw new BusOpenException(Constants.ErrorMsg.CAN_NOT_FIND_RECORD);
        }
        //可以不更新全部字段
        if (user.getUsername() != null) {
            oldUser.setUsername(user.getUsername());
        }
        if (user.getEmail() != null) {
            oldUser.setEmail(user.getEmail());
        }
        if (user.getMobile() != null) {
            oldUser.setMobile(user.getMobile());
        }
        if (user.getPassword() != null) {
            oldUser.setPassword(user.getPassword());
        }
        if (user.getCompanyName() != null) {
            oldUser.setCompanyName(user.getCompanyName());
        }
        if (user.getAccessKey() != null) {
            oldUser.setAccessKey(user.getAccessKey());
        }
        if (user.getSecretKey() != null) {
            oldUser.setSecretKey(user.getSecretKey());
        }
        oldUser.setUpdateTime(df.format(new Date()));
        return userMapper.updateById(oldUser);
    }

    @Override
    @Transactional
    public int deleteUser(Long id) throws BusOpenException {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", id);
        User oldUser = userMapper.selectOne(queryWrapper);
        if (oldUser == null) {
            throw new BusOpenException(Constants.ErrorMsg.CAN_NOT_FIND_RECORD);
        }
        oldUser.setStatus(CommonStatus.INVALID.getStatusCode());
        return userMapper.updateById(oldUser);
    }

    @Override
    public List<User> allUsers() {
        return userMapper.getAllUsers();
    }

    @Override
    public User getUserById(Long id) {
        return userMapper.selectById(id);
    }

    @Override
    public User getUserByUsername(String name) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("username", name);
        return userMapper.selectOne(wrapper);
    }

    @Override
    public User getUserByMobile(String mobile) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("mobile", mobile);
        return userMapper.selectOne(wrapper);
    }

    @Override
    public User getUserByEmail(String email) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("email", email);
        return userMapper.selectOne(wrapper);
    }

    @Override
    public String getSecretKey(String accessKey) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("access_key", accessKey);
        String secretKey = userMapper.selectOne(wrapper).getSecretKey();
        return secretKey;
    }


}