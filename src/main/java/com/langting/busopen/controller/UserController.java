package com.langting.busopen.controller;

import com.alibaba.fastjson.JSONObject;
import com.langting.busopen.entity.User;
import com.langting.busopen.exception.BusOpenException;
import com.langting.busopen.service.IUserService;
import com.langting.busopen.utils.CommonUtils;
import com.langting.busopen.vo.Result;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @program: bus-open
 * @description:
 * @author: Jiakun
 * @create: 2020-08-10 16:15
 **/
@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    private final IUserService userService;

    @Autowired
    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @GetMapping("/allUsers")
    public Result getAllUsers() {
        log.warn("----------调用getAllUsers()-----------");
        List<User> userList = userService.allUsers();
        return Result.result(userList,"返回用户列表成功");
    }

    @PostMapping("/getUserById")
    public Result getUserById(@RequestParam("id") Long id) {
        log.warn("----------调用getUserById()-----------");
        User user = userService.getUserById(id);
        return Result.result(user, "返回用户数据成功");
    }

    @PostMapping("/getUserByUsername")
    public Result getUserByUsername(@RequestParam("name") String name) {
        log.warn("----------调用getUserById()-----------");
        User user = userService.getUserByUsername(name);
        if (user == null) {
            return Result.error_404("找不到记录");
        }
        return Result.result(user, "返回用户数据成功");
    }

    @PostMapping("/getUserByMobile")
    public Result getUserByMobile(@RequestParam("mobile") String mobile) {
        User user = userService.getUserByMobile(mobile);
        if (user == null) {
            return Result.error_404("找不到记录");
        }
        return Result.result(user, "返回用户数据成功");
    }

    // 接口加密测试
    @GetMapping("/getUserByEmail")
    public Result getUserByEmail(@RequestParam("email") String email,
                                 @RequestParam("name") String name,
                                 @RequestParam("home") String home,
                                 @RequestParam("accessKey") String accessKey,
                                 @RequestParam("timestamp") Long timestamp,
                                 @RequestParam("nonce") String nonce,
                                 @RequestParam("sign") String sign) {

        List<String> nonces = new ArrayList<String>(){{
            add("asd456");
            add("asd457");
            add("asd458");
        }};

        // 避免重放攻击
        Long interval = (System.currentTimeMillis() - timestamp)/(1000 * 60);
        if (interval > 15) {
            return Result.error_502("超出时间范围");
        } else if(nonces.contains(nonce)){
            return Result.error_501("重复请求");
        }

        // 参数加密校验
        String secretKey = userService.getSecretKey(accessKey);

        String signTemp = "accessKey="+accessKey+"&email="+email+"&name="+name+"&home="+home+"&timestamp="+timestamp
                +"&nonce="+nonce+"&secretKey="+secretKey;
        System.out.println("serverA: "+signTemp);
        String serverSign = CommonUtils.md5(signTemp).toUpperCase();
        System.out.println("sign: "+sign);
        System.out.println("serverSign: "+serverSign);
        if (serverSign.equals(sign)) {
            System.out.println("***********调用************");
            System.out.println("sign: "+sign);
            System.out.println("serverSign: "+serverSign);
            User user = userService.getUserByEmail(email);
            if (user == null) {
                return Result.error_404("找不到记录");
            }
            return Result.result(user, "返回用户数据成功");
        } else {
            return Result.error_500("无权限访问");
        }
    }

    @PostMapping("/addUser")
    public Result addUser(@RequestBody User user) throws BusOpenException {
        int i = userService.addUser(user);
        if (i == 0) {
            return Result.error(403, "添加用户失败");
        }
        return Result.result(user, "添加用户成功");
    }

    @PostMapping("/deleteUser")
    public Result deleteUser(@RequestParam("id") Long id) throws BusOpenException {
        int i = userService.deleteUser(id);
        if (i == 0) {
            return Result.error(403, "删除用户失败");
        }
        return Result.result("删除用户成功");
    }

    @PostMapping("/updateUser")
    public Result updateUser(@RequestBody User user) throws BusOpenException {
        int i = userService.updateUser(user);
        if (i == 0) {
            return Result.error(403, "更新用户失败");
        }
        User newUser = userService.getUserById(user.getId());
        return Result.result(newUser, "更新用户成功");
    }


}