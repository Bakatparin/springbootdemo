package com.example.springbootdemo.utils;

import com.example.springbootdemo.datacraler.Article;
import org.apache.poi.xssf.usermodel.*;

import java.io.*;
import java.util.List;

/**
 * Create by Bakatparin
 * on 2018/11/16
 */
public class PoiUtils {

    public static void exportArticleExcel(List<Article> list,String[] headers,String excelName){
        try {
            //1.创建excel
            XSSFWorkbook wb = new XSSFWorkbook();

            //2.创建sheet
            XSSFSheet sheet = wb.createSheet("article");

            //3.创建第一行（表头名）
            XSSFRow row = sheet.createRow(0);
            for (int i = 0; i < headers.length; i++) {
                XSSFCell cell = row.createCell(i);
                cell.setCellValue(headers[i]);
            }

            //4.添加数据
            for (int i = 0; i < list.size(); i++) {
                Article article =  list.get(i);
                XSSFRow row1 = sheet.createRow(i+1);
                row1.createCell(0).setCellValue(article.getTitle());
                row1.createCell(1).setCellValue(article.getUrl());
                row1.createCell(2).setCellValue(article.getContent());
            }

            //导出excel到指定文件夹
            File file = new File("C:/Users/Administrator/Desktop/表格/导出表格/"+excelName+".xlsx");
            FileOutputStream os = new FileOutputStream(file);
            wb.write(os);
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }




}
