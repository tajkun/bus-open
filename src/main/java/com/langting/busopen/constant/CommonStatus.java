package com.langting.busopen.constant;

import lombok.Getter;

@Getter
public enum CommonStatus {

    VALID(1, "有效状态"),
    INVALID(0, "无效状态");

    private Integer statusCode;
    private String desc;

    CommonStatus(Integer statusCode, String desc) {
        this.statusCode = statusCode;
        this.desc = desc;
    }

}