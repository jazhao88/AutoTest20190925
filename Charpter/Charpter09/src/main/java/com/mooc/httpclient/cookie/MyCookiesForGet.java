package com.mooc.httpclient.cookie;

import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class MyCookiesForGet {
    private String url;
    private ResourceBundle bundle;
    private CookieStore store;
    @BeforeTest
    public void beforeTest(){
        bundle = ResourceBundle.getBundle("application", Locale.CHINA);
        url = bundle.getString("test.url");
    }
    @Test
    public void testGetCookie() throws IOException { //获取cookie
        //读取配置文件
        String result;
        this.store = new BasicCookieStore();
        String testUrl = this.url + bundle.getString("getCookies.uri");
        //测试逻辑代码
        CloseableHttpClient client = HttpClients.custom().setDefaultCookieStore(store).build();
        //CloseableHttpClient client = HttpClients.createDefault();
        HttpGet get = new HttpGet(testUrl);
        CloseableHttpResponse response = client.execute(get);
        result = EntityUtils.toString(response.getEntity(),"utf-8");
        System.out.println(result);

        //获取cookies信息
        List<Cookie> cookieList = this.store.getCookies();
        for (Cookie cookie:cookieList){
            String name = cookie.getName();
            String value = cookie.getValue();
            System.out.println("cookies name = " + name + "\ncookie value = " + value);
        }
    }
}
