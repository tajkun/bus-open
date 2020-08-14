package com.langting.busopen;

import com.langting.busopen.entity.User;
import com.langting.busopen.exception.BusOpenException;
import com.langting.busopen.service.IUserService;
import com.langting.busopen.utils.KeyGenerator;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @program: bus-open
 * @description:
 * @author: Jiakun
 * @create: 2020-08-11 11:09
 **/
@SpringBootTest
public class UserTest {

    final SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Autowired
    private IUserService userService;

    @Test
    public void userServiceTest() {
        System.out.println("-----------------------");
        List<User> userList = userService.allUsers();
        System.out.println(userList);
    }

    @Test
    public void userServiceTest1() throws BusOpenException {
        System.out.println("-----------------------");
        User user = new User();
        user.setUsername("xiaolan").setPassword("123456").setCompanyName("啊啊")
                .setStatus(1).setMobile("19861800992").setEmail("123478@126.com")
                .setAccessKey(KeyGenerator.getAccessKey())
                .setCreateTime(df.format(new Date())).setUpdateTime(user.getCreateTime());
        System.out.println(user);
        userService.addUser(user);
    }

    @Test
    public void userServiceTest2() throws BusOpenException {
        System.out.println("-----------------------");
        User user = userService.getUserByEmail("1234@126.com");

        System.out.println(userService.deleteUser(user.getId()));
        System.out.println(System.currentTimeMillis());
    }



}