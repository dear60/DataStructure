package com.mingful.www.datastructure;

import cn.hutool.http.HtmlUtil;
import cn.hutool.http.HttpUtil;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.util.HashMap;

/**
 * 名称： <br>
 * 功能： <br/>
 * <br/>
 *
 * @author fmf
 * @date 2020/6/22 16:36
 * @see
 * @since JDK 1.8
 */
public class Test {

    public static void main(String[] args) {


        String ltHtml = HttpUtil.post("http://oa.shinetechnology.com/cas/login", new HashMap<>());

        Document document = Jsoup.parse(ltHtml);
        String lt = document.body().getElementsByAttributeValue("name", "lt").val();

        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("username", "epbm");
        paramMap.put("password", "shineoa");
        paramMap.put("lt", lt);
        paramMap.put("_eventId", "submit");
        paramMap.put("submit", "登录");
        String result= HttpUtil.post("http://oa.shinetechnology.com/cas/login", paramMap);
        // _cE14D3D5E-405D-E872-866F-819A6EA06A1D_kD0236B67-CCC0-A1BA-50C0-9E95EB227A9E
        System.out.println(result);

        HashMap<String, Object> paramMap1 = new HashMap<>();
        paramMap1.put("city", "北京");

        System.out.println("======================================");
        String result1= HttpUtil.post("http://oa.shinetechnology.com/cit/m/issue/query_todoIssueList", paramMap1);
        System.out.println(result1);
    }
}
