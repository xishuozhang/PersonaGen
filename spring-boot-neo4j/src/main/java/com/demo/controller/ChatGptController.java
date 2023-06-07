package com.demo.controller;

import com.csvreader.CsvReader;
import com.demo.config.FileCheck;
import com.demo.entity.GPTResponse;
import com.demo.entity.Person;
import com.demo.entity.ResultMessage;
import com.demo.enums.FileTypeEnum;
import com.demo.service.PersonService;
import com.demo.service.impl.ChatGPT;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 *  * @classDesc:
 *  * @Date 2023/4/22 21:55
 *  * @Author:zxs
 *
 *
 */

@Api(tags = "ChatGptController")
@RestController
@RequestMapping("/gpt")
public class ChatGptController {
    @Resource
    private ChatGPT chatGPT;
    @Autowired
    PersonService service;

    @ApiOperation("测试gpt")
    @PostMapping("/test")
    public String test(String input) {
        if (input.equals("你是谁")){
            return "我是王星星";
        }
        GPTResponse response = chatGPT.send(input);
        String output = response.getChoices().get(0).getText();
        System.out.println(response.getChoices().get(0).getText());
        return output;
    }

    @ApiOperation("测试gpt读取csv")
    @PostMapping("/test/csv")
    public String test() {
        List<String> list = readCsvByCsvReader();
        GPTResponse response = chatGPT.send(list.toString());
        String output = response.getChoices().get(0).getText();
        System.out.println(response.getChoices().get(0).getText());
        return output;
    }
    @ApiOperation(value="数据处理")
    @PostMapping (value="/ca/importExcelDeptStaff",produces = {"text/html;charset=utf-8"})
    @FileCheck(message = "不支持的文件格式",supportedFileTypes = {FileTypeEnum.XLS,FileTypeEnum.XLSX},type = FileCheck.CheckType.MAGIC_NUMBER)
    public ResultMessage importExcelDeptStaff( MultipartFile file, HttpServletRequest request, HttpServletResponse response) throws Exception {

        ResultMessage resultMessage = service.importExcelDeptStaff(file, request, response);
        return resultMessage;

    }
    /**
     * 测试读取csv文件
     */
    public List<String> readCsvByCsvReader() {
        String filePath = "C:\\Users\\zxs\\Desktop\\relationship.csv";
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
            //arrList.size()
            for (int row = 1; row < arrList.size(); row++) {
                // 组装String字符串
                // 如果不知道有多少列，则可再加一个循环
                String ele = arrList.get(row)[0] + " " + arrList.get(row)[1] + " " + arrList.get(row)[2];
                System.out.println(ele);
                strList.add(ele);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return strList;

    }


}