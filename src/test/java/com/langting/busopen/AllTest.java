package com.langting.busopen;

import com.langting.busopen.utils.CommonUtils;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import org.apache.commons.lang3.time.DateFormatUtils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @program: bus-open
 * @description:
 * @author: Jiakun
 * @create: 2020-08-12 19:16
 **/
public class AllTest {
    public static void main(String[] args) throws InterruptedException {
//
//       Long timeStamp = System.currentTimeMillis();
//        TimeUnit.SECONDS.sleep(65);
////        Long timeStamp2 = System.currentTimeMillis();
////        Long betweenMills = timeStamp2 - timeStamp1;
//////
////       long totalSec = betweenMills/1000;
////        System.out.println("间隔时间："+totalSec);
////       long totalMin = totalSec/60;
////        System.out.println(totalMin);
//
//        Long interval = (System.currentTimeMillis() - timeStamp)/(1000 * 60);
//        System.out.println(interval);
//
//        List<String> nonces = new ArrayList<>();
//        nonces.add("sddd");


//        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//
//        String format = DateFormatUtils.format(timeStamp, "yyyy-MM-dd HH:mm:ss");

         String a = "accessKey=5tZZo5Bj&name=jiakun&nonce=jhfdau&email=123@163.com&home=哈哈哈&timestamp=1597393425570&secretKey=31174C87874045DF862DDF1E2270AD12";
         String b = "accessKey=5tZZo5Bj&name=jiakun&nonce=jhfdau&email=123@163.com&home=哈哈哈&timestamp=1597393425570&secretKey=31174C87874045DF862DDF1E2270AD12";
        System.out.println(CommonUtils.md5(a).equals(CommonUtils.md5(b)));


    }
}