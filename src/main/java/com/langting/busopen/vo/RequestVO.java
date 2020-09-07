package com.langting.busopen.vo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;
import java.util.Map;

/**
 * @program: bus-open
 * @description:
 * @author: Jiakun
 * @create: 2020-09-03 14:20
 **/
@Data
@Accessors(chain = true)
public class RequestVO {
    private String email;
    private String name;
    private String home;
//    private String interesting;
}