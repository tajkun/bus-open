package com.langting.busopen;

import com.langting.busopen.entity.User;
import com.langting.busopen.utils.CommonUtils;
import com.langting.busopen.vo.Result;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
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

    private final String URL = "http://localhost:8081/user/getUserByEmail/";

    @Test
    public void encryptionGetTest() {
        // todo 接口参数加密
        String secretKey = "31174C87874045DF862DDF1E2270AD12";

//        String nonce = CommonUtils.generateCode(8);
        String nonce = "12345678";

        MultiValueMap<String, Object> mapParams = new LinkedMultiValueMap<>();
        mapParams.add("accessKey", "5tZZo5Bj");
        mapParams.add("email", "123@163.com");
        mapParams.add("name", "jiakun");
        mapParams.add("home", "哈哈哈");
        mapParams.add("timestamp", System.currentTimeMillis());
        mapParams.add("nonce", nonce);

        System.out.println("****: "+mapParams.toString());

        String a = mapParams.toString().replace(", ","&")
                .substring(1)
                .replace("}","")
                .concat("&secretKey="+secretKey);

        System.out.println("clientA: "+a);
        String sign = CommonUtils.md5(a).toUpperCase();
        mapParams.add("sign",sign);

//        Result result = restTemplate.getForObject(URL
//                        + "?email={email}&name={name}&home={home}&accessKey={accessKey}&timestamp={timestamp}&nonce={nonce}&sign={sign}",
//                Result.class, mapParams);

        Result result = restTemplate.postForObject(URL, mapParams, Result.class);
        System.out.println(result);
    }


}