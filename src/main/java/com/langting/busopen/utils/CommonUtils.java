package com.langting.busopen.utils;

import com.langting.busopen.exception.BusOpenException;
import org.apache.commons.codec.digest.DigestUtils;

import java.text.ParseException;
import java.util.Date;
import java.util.Random;

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

    /**
     * 生成指定位数的随机数字
     * @param len 随机数的位数
     * @return 生成的随机数
     */
    public static String generateCode(int len){
        len = Math.min(len, 8);
        int min = Double.valueOf(Math.pow(10, len - 1)).intValue();
        int num = new Random().nextInt(
                Double.valueOf(Math.pow(10, len + 1)).intValue() - 1) + min;
        return String.valueOf(num).substring(0,len);
    }

}