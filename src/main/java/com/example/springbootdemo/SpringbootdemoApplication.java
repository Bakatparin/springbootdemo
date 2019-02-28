package com.example.springbootdemo;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;


@SpringBootApplication
@EnableCaching
public class SpringbootdemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootdemoApplication.class, args);
        //craler3();
        //craler4();
        /*getMaxAfterDelete(535851212,3);*/
    }

    /**
     * 将一个数a，删除b位之后求最大值
     * @param a
     * @param b
     * @return 返回最大值
     * 思路分析：删除b位，相当于在a中选取l-b位，选取第1位数后，后面至少剩下l-b-1位数，那么第1位数的索引在0-》l-b-1
     */
    public static int getMaxAfterDelete(int a ,int b){
        //将int a转换成字符串
        String str = String.valueOf(a);
        int l = str.length();
        //将str转换成int[]
        int[] intArr = strToArr(str);
        StringBuilder strb = new StringBuilder();
        int star = 0;
        int end = b;
        int max = 0;
        int maxValueIndex = 0;
        for(int index=0;index<=l-b-1;index++){
            for(int i = star;i<=end&&end<l;i++){
                if(intArr[i]>max){
                    max = intArr[i];
                    maxValueIndex = i;
                }
            }
            strb.append(max);
            max = 0;
            star=maxValueIndex+1;
            end++;
        }
        System.out.print(strb);
        return new Integer(strb.toString());
    }
    /*
     *字符串转int数组
     */
    public static int[] strToArr(String str){
        int l = str.length();
        int[] intArr = new int[l];
        for(int i=0; i<l; i++){
            char a = str.charAt(i);
            intArr[i]= a-'0';
        }
        return intArr;
    }



    /**
     * 有一条彩色宝石项链，是由很多种不同的宝石组成的，包括红宝石，蓝宝石，钻石，翡翠，珍珠等。有一天国王
     * 把项链赏赐给了一个学者，并跟他说，你可以带走这条项链，但是王后很喜欢红宝石，蓝宝石，紫水晶，翡翠和
     * 钻石这五种，我要你从项链中截取连续的一小段还给我，这一段中必须包含所有的这五种宝石，
     * 剩下的部分你可以带走。如果无法找到则一个也无法带走。请帮助学者找出如何切分项链才能够拿到最多的宝石。
     * 输入描述：我们用每种字符代表一种宝石，A表示红宝石，B表示蓝宝石，C代表紫水晶，D代表翡翠，E代表钻石，F代表玉石，G代表玻璃等等，
     * 我们用一个全部为大写字母的字符序列表示项链的宝石序列，注意项链是首尾相接的。每行代表一种情况。
     */
    public static int test(String str){
        char[] chArr = str.toCharArray();
        Map<Character,Integer> map = new TreeMap(
                new Comparator<Map.Entry<Character,Integer>>() {
                    public int compare(Map.Entry<Character,Integer> a,Map.Entry<Character,Integer> b) {
                        return a.getValue().compareTo(b.getValue());
                    }
                });

        int minLength = chArr.length;
        char x = chArr[0];
        for(int i=0;i<chArr.length;i++){


            if(map.containsKey("a")&&map.containsKey("b")&&map.containsKey("c")&&
                    map.containsKey("d")&&map.containsKey("e")&&map.containsKey("f")&&map.containsKey("g")){
                /*if(chArr[i] == (map.keySet())){
                    if(minLength>)
                }*/

            }

            map.put(chArr[i],i);
        }
        return 0;
    }




    /**
     * 爬取湖南省科技厅数据
     */
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

    /**
     * 爬取成果图片
     */
    public static void craler3(){
        File file = new File("E:/cxc专利导入数据.xlsx");
        Workbook wb = null;
        InputStream is = null;
        try {
            is = new FileInputStream(file);
            /** 使用WorkbookFactory不用再去判断Excel因为版本不同而使用不同的方法 */
            wb = WorkbookFactory.create(is);
            is.close();

            /** 得到第一个shell */
            Sheet sheet = wb.getSheetAt(0);
            /** 得到Excel的总行数 */
            int rowCount = sheet.getPhysicalNumberOfRows();
            //创建httpclient
            CloseableHttpClient httpclient =  HttpClients.createDefault();

            Set s = new HashSet<String>();
            /** 循环Excel的行 */
            for(int i=1;i<=rowCount;i++){
                Row row = sheet.getRow(i);
                if (null == row) {
                    continue;
                }
                Cell imgPathCell = row.getCell(23);
                if(imgPathCell!=null){
                    String imgName = imgPathCell.getStringCellValue();
                    if(StringUtils.isNotBlank(imgName)){
                        String imgUrl = "https://www.innocity.com"+imgName;

                        //输出一下
                        System.out.println(imgUrl+"=="+i);
                        //若重复就会抛异常
                        try {
                            s.add(imgUrl.trim());
                        } catch (Exception e) {
                            System.out.println("地址重复："+imgUrl.trim());
                        }

                        HttpGet PicturehttpGet = new HttpGet(imgUrl.trim());
                        //设置传输超时时间 SocketTimeout:数据传输过程中数据包之间间隔的最大时间
                        RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(20000).setConnectTimeout(20000).build();
                        PicturehttpGet.setConfig(requestConfig);
                        CloseableHttpResponse pictureResponse = httpclient.execute(PicturehttpGet);
                        HttpEntity pictureEntity = pictureResponse.getEntity();
                        InputStream inputStream = pictureEntity.getContent();
                        //判断图片是否为空
                        if(pictureEntity.getContentLength()>0){
                            // 使用 common-io 下载图片到本地，注意图片名不能重复
                            int a = imgName.lastIndexOf("/");
                            String fileName = imgName.substring(a+1);
                            File desFile = new File("E:/cxc专利图片/"+fileName.trim());
                            FileUtils.copyToFile(inputStream,desFile);
                            if(!desFile.exists()){
                                System.out.println("图片"+fileName.trim()+"下载失败下载失败下载失败下载失败下载失败下载失败下载失败下载失败");
                            }
                        }else{
                            System.out.println("===========111======================================="+imgUrl+"===============================111===================");
                        }
                        pictureResponse.close();
                    }else{
                        System.out.println("===========222======================================="+imgName+"===============================222===================");
                    }
                }else{
                    System.out.println("===================333========================"+i+"=========================================333==================");
                }

            }
            System.out.println(s);
            httpclient.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    //验证重复的数据
    public static void craler4() {
        File file = new File("E:/cxc专利导入数据.xlsx");
        Workbook wb = null;
        InputStream is = null;
        try {
            is = new FileInputStream(file);
            /** 使用WorkbookFactory不用再去判断Excel因为版本不同而使用不同的方法 */
            wb = WorkbookFactory.create(is);
            is.close();

            /** 得到第一个shell */
            Sheet sheet = wb.getSheetAt(0);
            /** 得到Excel的总行数 */
            int rowCount = sheet.getPhysicalNumberOfRows();
            Set s = new HashSet<String>();
            List l = new ArrayList<String>();
            //遍歷一行
            for(int i=1;i<=rowCount;i++) {
                Row row = sheet.getRow(i);
                if (null == row) {
                    continue;
                }
                Cell imgPathCell = row.getCell(23);
                String imgName = imgPathCell.getStringCellValue();
                s.add(imgName);
                if(l.contains(imgName)){
                    System.out.println("已存在："+imgName);
                }else{
                    l.add(imgName);
                }
            }

            System.out.println(s);
            System.out.println(l);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        }
    }















}
