package com.example.springbootdemo;

import com.sun.xml.internal.fastinfoset.util.CharArray;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootdemoApplicationTests {

    @Test
    public void contextLoads() {
        String str = "agnksanga";
        int len = str.length();
        String maxValue = Long.toString(Long.MAX_VALUE);
        //System.out.println(Long.MAX_VALUE);//9223372036854775807
        if(len>19){
            System.out.println("长度超标!");
        }else{
            for(int i=0;i<len;i++){
                if(Character.isDigit(str.charAt(i))){
                    if(len==19&&str.charAt(i)>maxValue.charAt(i)){
                        System.out.println("超过Long最大值!"+(i+1));
                        break;
                    }
                }else{
                    System.out.println("字符串第"+(i+1)+"位不为数字!");
                    break;
                }
            }
            if(Integer.parseInt(str)<1){
                System.out.println("小于最小值!");
            }
        }


    }


    @Test
    public void contextLoads1() {
        System.out.println(Integer.parseInt("0000000001")>=1);

    }

}
