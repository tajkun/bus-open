package com.langting.busopen.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.text.DateFormat;
import java.util.Date;

/**
 * @program: bus-open
 * @description:
 * @author: Jiakun
 * @create: 2020-08-10 13:56
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@TableName(value = "open_user")
public class User {

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    @TableField(value = "username")
    private String username;
    @TableField(value = "password")
    private String password;
    @TableField(value = "mobile")
    private String mobile;
    @TableField(value = "email")
    private String email;
    @TableField(value = "company_name")
    private String companyName;
    @TableField(value = "access_key")
    private String accessKey;
    @TableField(value = "secret_key")
    private String secretKey;
    @TableField(value = "avatar")
    private String avatar;
    @TableField(value = "role")
    private Integer role;
    @TableField(value = "status")
    private Integer status;
    @TableField(value = "create_time")
    private String createTime;
    @TableField(value = "update_time")
    private String updateTime;
    @TableField(value = "secretkey_create_time")
    private String secretkeyCreateTime;

    public boolean validate() {
        return StringUtils.isEmpty(username) ||
                StringUtils.isEmpty(password) ||
                StringUtils.isEmpty(mobile) ||
                StringUtils.isEmpty(email) ||
                StringUtils.isEmpty(companyName);
    }

}