package com.example.demo;

import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class test {

    	/**
                 * 方法描述：方法描述：获取节假日 访问接口，根据返回值判断当前日期是否为工作日，
            * 返回结果：检查具体日期是否为节假日，工作日对应结果为 0, 休息日对应结果为 1, 节假日对应的结果为 2；
            * @author xyl
	 */
    @Test
    public static  String  getHoliday(String  time) {
        time = "2018-10-01";
        String dc = "http://tool.bitefu.net/jiari/?d=";
        String  httpUrl=new StringBuffer().append(dc).append(time).toString();
        BufferedReader reader = null;
        String result = null;
        StringBuffer sbf = new StringBuffer();
        try {
            URL url = new URL(httpUrl);
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            InputStream is = connection.getInputStream();
            reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            String strRead = null;
            while ((strRead = reader.readLine()) != null) {
                sbf.append(strRead);
            }
            reader.close();
            result = sbf.toString();
        } catch (Exception e) {

        }
        return result;
    }
}
