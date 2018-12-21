package com.example.springbootdemo.datacraler;


import com.example.springbootdemo.utils.PoiUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Controller
public class DataCralerController {

    public static void main(String[] args) throws IOException {
        try {
            List<Article> articleList = new ArrayList<>();
            Document doc = Jsoup.connect("http://m.yiibai.com/jsoup/").get();
            //Elements content  = doc.select("div[role='main']").select("div[class='ui-grid-a ui-responsive']");
            //System.out.println(content.toString());
            //Elements lis = doc.select("div[data-role='panel']").select("ul[class='jqm-list ui-alt-icon ui-nodisc-icon ui-listview']");
            Elements lis = doc.getElementsByTag("ul").get(1).getElementsByTag("li");
            for (Element li : lis) {
                //String ab = li.attr("data-filtertext");
                if(!StringUtils.equals(li.attr("data-filtertext"),"demos homepage")){//除去 “返回主页” 这一li
                    Article article = new Article();
                    String title = li.text();
                    String url = "http://m.yiibai.com"+li.select("a").first().attr("href");
                    //获取内容
                    Document doc1 = Jsoup.connect(url).get();
                    Element contentDom  = doc1.select("div[role='main']").select("div[class='ui-grid-a ui-responsive']").first();
                    String content = contentDom.toString();

                    article.setTitle(title);
                    article.setUrl(url);
                    article.setContent(content);
                    articleList.add(article);
                    System.out.println(title);
                }

            }
            String[] headers = {"title","url","content"};
            String excelName = "article";
            PoiUtils.exportArticleExcel(articleList,headers,excelName);
        } catch (IOException e) {
            System.out.println("出异常了！");
        }
    }


    @RequestMapping("/a")
    public void craler1(){
        try {
            HttpClient httpClient = new DefaultHttpClient();
            HttpGet httpGet = new HttpGet("https://www.toutiao.com/");
            HttpResponse response = httpClient.execute(httpGet);
            HttpEntity entity = response.getEntity();
            StringBuffer sb=new StringBuffer();
            if (entity != null) {
                InputStream instream = entity.getContent();
                int l;
                byte[] tmp = new byte[2048];
                while ((l = instream.read(tmp)) != -1) {
                    System.out.println(new String(tmp, 0, l, "utf-8"));
                    sb.append(new String(tmp, 0, l, "utf-8"));
                }
                //终止请求
                httpGet.abort();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("/b")
    public String craler2(){
        HttpURLConnection httpURLConnection = null;
        try {
            //网络请求连接地址
            URL url = new URL("http://www.tuniu.com/?p=1400&cmpid=mkt_06002401&utm_source=baidu&utm_medium=brand&utm_campaign=brand");
            //得到网络连接
            httpURLConnection = (HttpURLConnection)url.openConnection();
            //设置网络连接超时时间
            httpURLConnection.setConnectTimeout(5*1000);
            //设置读取超时时间
            httpURLConnection.setReadTimeout(5*1000);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }



    @RequestMapping("/c")
    public String hello(){
        return "index";
    }


    @RequestMapping("/cralerHN")
    public String cralerData(){

        String url = "http://kjt.hunan.gov.cn/xxgk/zcfg/fggz/";
        try {
            Document doc = Jsoup.connect(url).get();
            Elements elements = doc.getElementsByTag("tr");
            Elements elements1 = doc.getElementsByAttributeValue("class","table");
            Element element = doc.getElementById("CBody");
            ArrayList<Article> articleList = new AnalysisData().analysisArticle(doc);
            for(Article al:articleList){
                //将list导出为excel表
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping("/cralerHN1")
    public String cralerData1(){

        return null;
    }


}
