package com.example.springbootdemo.freemaker;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.Template;
/**
 * Create by Bakatparin
 * on 2019/3/1
 */
    /**
     * 最常见的问题：
     *     java.io.FileNotFoundException: xxx does not exist. 解决方法：要有耐心
     *     FreeMarker jar 最新的版本（2.3.23）提示 Configuration 方法被弃用
     * 代码自动生产基本原理：
     *     数据填充 freeMarker 占位符
     */
public class TestFreemaker {

    private static final String TEMPLATE_PATH = "src/main/java/com/example/springbootdemo/freemaker/templates";
    private static final String CLASS_PATH = "src/main/java/com/example/springbootdemo/freemaker/generateCode";
    private static final String PACKAGE_PATH = "com.example.springbootdemo.freemaker.generateCode";

    public static void main(String[] args) {
        // step1 创建freeMarker配置实例
        Configuration configuration = new Configuration();
        Writer out = null;
        try {
            // step2 获取模版路径
            configuration.setDirectoryForTemplateLoading(new File(TEMPLATE_PATH));
            // step3 创建数据模型
            Map<String, Object> dataMap = new HashMap<String, Object>();
            dataMap.put("classPath", PACKAGE_PATH);
            dataMap.put("className", "Hello");
            dataMap.put("helloWorld", "通过简单的 <代码自动生产程序> 演示 FreeMarker的HelloWorld！");
            // step4 加载模版文件
            Template template = configuration.getTemplate("hello.ftl");
            // step5 生成数据
            File docFile = new File(CLASS_PATH + "\\" + "Hello.java");
            out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(docFile)));
            // step6 输出文件
            template.process(dataMap, out);
            System.out.println("文件创建成功 !");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != out) {
                    out.flush();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

}

















