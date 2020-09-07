package com.langting.busopen;

import com.alibaba.fastjson.JSON;
import com.langting.busopen.utils.CommonUtils;
import com.langting.busopen.vo.RequestVO;
import com.langting.busopen.vo.Result;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Field;
import java.util.*;

/**
 * @program: bus-open
 * @description:
 * @author: Jiakun
 * @create: 2020-09-03 14:12
 **/
@Log4j2
@SpringBootTest
public class ReflexTest {
    @Test
    public void testRequest() throws NoSuchFieldException, IllegalAccessException {

        //认证码accessKey
        final String accessKey = "5tZZo5Bj";
        //密钥
        final String secretKey = "31174C87874045DF862DDF1E2270AD12";
        //接口url
        final String url = "http://localhost:8081/user/getUserByEmail";

        //接口参数
        RequestVO requestVO = new RequestVO();
        requestVO.setName("jialkun").setEmail("123@163.com").setHome("taian");

        //调用公用方法，访问接口
        busOpen(accessKey, secretKey, url, requestVO);
    }

    /**
    * @Description: 接口调用通用方法，在调用方进行参数规范与参数加密
    * @Param: accessKey 统一身份认证标识
    * @Param: secretKey 密钥
    * @Param: url 接口地址
    * @Param: obj 接口对应的参数对象
    * @return: 待定
    * @Author: Jiakun
    * @time: 2020/9/3 15:42
    */
    public static <T> void busOpen(String accessKey, String secretKey, String url,  T obj) throws NoSuchFieldException, IllegalAccessException {
        //HTTP Client
        RestTemplate restTemplate = new RestTemplate();
        //TreeMap用于参数排序
        Map<String, Object> treeMap = new TreeMap<>();
        //利用反射拿到对象的字段名与值
        Class<?> aClass = obj.getClass();
        System.out.println(aClass);
        Field[] declaredFields = aClass.getDeclaredFields();
        for (Field field : declaredFields) {
            field.setAccessible(true);
            //判断请求对象中是否含有List类型的属性
//            Class<?> filedClass = field.get(obj).getClass();
//            if (filedClass.getName().contains("List")) {
//                   String filedList = JSON.toJSONString(field.get(obj));
//                   treeMap.put(field.getName(), filedList);
//                   continue;
//            }
            treeMap.put(field.getName(), field.get(obj));

        }

        //请求身份标识
        treeMap.put("accessKey", accessKey);
        //请求密钥
        treeMap.put("secretKey", secretKey);
        //请求时间戳
        treeMap.put("timestamp", System.currentTimeMillis());
        //请求唯一编号
        treeMap.put("nonce", CommonUtils.generateCode(8));

        //拼接规范参数串
        String paramString = treeMap.toString().replace(", ","&")
                .substring(1)
                .replace("}","");

        log.warn("paramString: {}", paramString);
        //生成签名sign
        String sign = CommonUtils.md5(paramString).toUpperCase();
        log.warn("sign: {}", sign);
        treeMap.put("sign", sign);

        //转为MultiValueMap
        MultiValueMap<String, Object> paramMap = new LinkedMultiValueMap<>();
        Set<Map.Entry<String, Object>> entries = treeMap.entrySet();
        for (Map.Entry<String, Object> entry : entries) {
            paramMap.add(entry.getKey(), entry.getValue());
        }
        //调用接口
        Result result = restTemplate.postForObject(url, paramMap, Result.class);
        log.warn("result: {}", result);
    }
}