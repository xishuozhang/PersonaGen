package com.demo.test;
import com.csvreader.CsvReader;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;

/**
 *  * @classDesc:
 *  * @Date 2023/4/26 16:48
 *  * @Author:zxs
 *
 * @copyright zxs
 */
public class Test {

    @org.junit.Test
    public void readCsvByCsvReader() {
        String filePath="C:\\Users\\zxs\\Desktop\\relationship.csv";
        ArrayList<String> strList = null;
        try {
            ArrayList<String[]> arrList = new ArrayList<String[]>();
            strList = new ArrayList<String>();
            CsvReader reader = new CsvReader(filePath, ',', Charset.forName("UTF-8"));
            while (reader.readRecord()) {
//                System.out.println(Arrays.asList(reader.getValues()));
                arrList.add(reader.getValues()); // 按行读取，并把每一行的数据添加到list集合
            }
            reader.close();
            System.out.println("读取的行数：" + arrList.size());
            // 如果要返回 String[] 类型的 list 集合，则直接返回 arrList
            // 以下步骤是把 String[] 类型的 list 集合转化为 String 类型的 list 集合
            for (int row = 0; row < 1; row++) {
                // 组装String字符串
                // 如果不知道有多少列，则可再加一个循环
                String ele = arrList.get(row)[0]+" "+arrList.get(row)[1]+" "+arrList.get(row)[2];
                System.out.println(ele);
                strList.add(ele);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(strList);
        System.out.println(strList.toString());
    }

    @org.junit.Test
    public void ExcelTets() throws IOException {
        FileInputStream fileInputStream = new FileInputStream("C:\\Users\\zxs\\Desktop\\test_poi.xlsx");

        // 2.创建工作区workbook
        XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);

        // 3.获取表sheet,这里sheet0代表获取下表为0的excel表,也就是第一个表
        XSSFSheet sheet = workbook.getSheetAt(0);

        // 4.获取数据
        // getLastRowNum() 获取一张sheet表中行的数量
        for(int i = 0 ; i <= sheet.getLastRowNum();i++){
            // 获取第i行的数据
            XSSFRow row = sheet.getRow(i);
            // getLastCellNum() 获取这一行中单元格的数量
            for(int j = 0; j < row.getLastCellNum(); j++){
                // 获取第i行第j列的单元格数据
                String cell = row.getCell(j).toString();

                // 5.打印单元格数据
                System.out.print(cell+" ");
            }
            // println换行
            System.out.println("");
        }

        // 6.关闭资源
        fileInputStream.close();
    }




}
