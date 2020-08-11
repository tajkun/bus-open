package com.langting.busopen.utils;

import com.langting.busopen.exception.BusOpenException;
import org.apache.commons.codec.digest.DigestUtils;

import java.text.ParseException;
import java.util.Date;
import org.apache.commons.lang3.time.DateUtils;

public class CommonUtils {

    // 字符串转换满足条件
    private static String[] parsePatterns = {
            "yyyy-MM-dd", "yyyy/MM/dd","yyyy.MM.dd"
    };

    public static String md5(String value) {
        return DigestUtils.md5Hex(value).toUpperCase();
    }

    public static Date parseStringToDate(String value) throws BusOpenException {
        try {
            return DateUtils.parseDate(value, parsePatterns);
        } catch ( ParseException e) {
            throw new BusOpenException(e.getMessage());
        }
    }
}