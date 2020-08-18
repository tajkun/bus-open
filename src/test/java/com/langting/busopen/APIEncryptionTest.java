package com.langting.busopen;

import com.langting.busopen.entity.User;
import com.langting.busopen.utils.CommonUtils;
import com.langting.busopen.vo.Result;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @program: bus-open
 * @description:
 * @author: Jiakun
 * @create: 2020-08-14 11:26
 **/
@SpringBootTest
public class APIEncryptionTest {

    private RestTemplate restTemplate = new RestTemplate();

    private final String URL = "http://localhost:8080/user/getUserByEmail";

    @Test
    public void encryptionGetTest() {
        // todo 接口参数加密
        String secretKey = "31174C87874045DF862DDF1E2270AD12";

//        String nonce = CommonUtils.generateCode(8);
        String nonce = "12345678";

        Map<String, Object> mapParams = new LinkedHashMap<>();
        mapParams.put("accessKey", "5tZZo5Bj");
        mapParams.put("email", "123@163.com");
        mapParams.put("name", "jiakun");
        mapParams.put("home", "哈哈哈");
        mapParams.put("timestamp", System.currentTimeMillis());
        mapParams.put("nonce", nonce);

        System.out.println("****: "+mapParams.toString());

        String a = mapParams.toString().replace(", ","&")
                .substring(1)
                .replace("}","")
                .concat("&secretKey="+secretKey);

        System.out.println("clientA: "+a);
        String sign = CommonUtils.md5(a).toUpperCase();
        mapParams.put("sign",sign);

        Result result = restTemplate.getForObject(URL
                        + "?email={email}&name={name}&home={home}&accessKey={accessKey}&timestamp={timestamp}&nonce={nonce}&sign={sign}",
                Result.class, mapParams);
        System.out.println(result);
    }


}