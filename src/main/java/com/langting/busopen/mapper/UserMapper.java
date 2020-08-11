package com.langting.busopen.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.langting.busopen.entity.User;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @program: bus-open
 * @description:
 * @author: Jiakun
 * @create: 2020-08-10 14:08
 **/
public interface UserMapper extends BaseMapper<User> {

    @Select("select * from open_user order by id desc")
    List<User> getAllUsers();
}