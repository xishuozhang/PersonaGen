package com.demo.service.impl;

import com.csvreader.CsvReader;
import com.demo.entity.*;
import com.demo.repository.PersonRepository;
import com.demo.service.PersonService;
import com.demo.utils.ExcelUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 *  * @classDesc:
 *  * @Date 2023/3/27 19:37
 *  * @Author:zxs
 *
 * @copyright
 */
@Slf4j
@Service
public class PersonServiceImpl implements PersonService {
    @Autowired
    PersonRepository repository;
    @Resource
    private ChatGPT chatGPT;
    @Override
    public List<Person> getAll() {
        List<Person> list = repository.getAll();
        // 获取职位信息
        List<position> positionInfo = repository.getPositionInfo();

        for(Person pe: list){
            //去除name之间的空格
            String name=pe.getName().replaceAll("\\s+","");
            //添加邮箱信息
            String email = generateEmail(name);
            pe.setEmail(email);
            //拼接图片url使得浏览器能够访问
            pe.setImg("https://images.weserv.nl/?url="+pe.getImg());
            // 添加职位id
            positionInfo.forEach(item ->{
                if (pe.getPosition().equals(item.getName())){
                    pe.setPositionId(item.getId());
                }
            });
        }
        Iterator<Person> it = list.iterator();
        while(it.hasNext()) {
            if("DBA".equals(it.next().getPosition())) {
                it.remove();
            }
        }


        return list;
    }


    @Override
    public void importActorsFromCSV() {
        repository.importActorsFromCSV();
    }

    @Override
    /**
     * 导出数据
     */
    public void exportExcel(HttpServletRequest request, HttpServletResponse response,List person) {
            ExcelDomain excelDomain = new ExcelDomain();
            excelDomain.setBookTitle("用户画像数据");
            List<ExcelSheetDomain> excelSheetDomainList = new ArrayList<>();
            ExcelSheetDomain excelSheetDomain = new ExcelSheetDomain();
            excelSheetDomain.setSheetName("用户画像");
            Map<String, String> titleMap = new LinkedHashMap<>();
            titleMap.put("name", "name");
            titleMap.put("age", "age");
            titleMap.put("gender", "gender");
            titleMap.put("position", "position");
            titleMap.put("educationalExperience", "educationalExperience");
            titleMap.put("isType", "isType");
            titleMap.put("feedBack", "feedBack");
            titleMap.put("motivation", "motivation");
            titleMap.put("requirementForApplication", "requirementForApplication");
            excelSheetDomain.setTitleMap(titleMap);
            List<BaseData> list = person;
            List<Map<String, String>> sheetDataList = new ArrayList<>();
            for (BaseData domain : list) {
                Map<String, String> dataMap = new HashMap<>();
                dataMap.put("name", domain.getName());
                dataMap.put("age", String.valueOf(domain.getAge()));
                dataMap.put("gender", domain.getGender());
                dataMap.put("position", domain.getPosition());
                dataMap.put("educationalExperience", domain.getEducationalExperience());
                dataMap.put("isType", domain.getIsType());
                dataMap.put("feedBack", domain.getFeedBack());
                dataMap.put("motivation", domain.getMotivation());
                dataMap.put("requirementForApplication", domain.getRequirementForApplication());

                sheetDataList.add(dataMap);
            }
            excelSheetDomain.setSheetDataList(sheetDataList);
            excelSheetDomainList.add(excelSheetDomain);
            excelDomain.setSheetDomainList(excelSheetDomainList);
            try {
                ExcelUtils.exportExcel(request, response, excelDomain);
            } catch (Exception e) {
                e.printStackTrace();
            }


    }

    @Override
    public ResultMessage importExcelDeptStaff(MultipartFile xlsfile, HttpServletRequest request, HttpServletResponse response) throws Exception {
        log.info("数据转换开始！！！");
        InputStream in = xlsfile.getInputStream();
        Workbook workbook = WorkbookFactory.create(in);
        Sheet sheet = workbook.getSheetAt(0);
        int totalRows = sheet.getPhysicalNumberOfRows();

        ResultMessage resultMessage = new ResultMessage();
        if(totalRows <= 1){
            resultMessage.setFail("数据信息为空！！！");
            return resultMessage;
        }

        Row row = sheet.getRow(0);
        short lastCellNum = row.getLastCellNum();
        int[] num =new int[10];
        for (int i=0;i<lastCellNum;i++){
            if(row.getCell(i).getStringCellValue().equals("creator")){
                num[0]=i;
            }
            if(row.getCell(i).getStringCellValue().equals("tag")){
                num[1]=i;
            }
            if(row.getCell(i).getStringCellValue().equals("issueType")){
                num[2]=i;
            }
            if(row.getCell(i).getStringCellValue().equals("summary")){
                num[3]=i;
            }
        }

        List<BaseData> list = new ArrayList<>();
        for (int i = 1; i < totalRows; i++) {
            Row currentRow = sheet.getRow(i);
            BaseData data = new BaseData();


            for (int j=0 ;j<lastCellNum;j++){
                // 全部设置为String类型
                currentRow.getCell(0).setCellType(CellType.STRING);
            }
            data.setName(currentRow.getCell(num[0]).getStringCellValue());
            data.setPosition(currentRow.getCell(num[1]).getStringCellValue());
            data.setIsType(currentRow.getCell(num[2]).getStringCellValue());
            // 根据职位随机设置年龄
            Random rand = new Random();
            if (data.getPosition().equals("Operation and maintenance")){
                data.setAge(rand.nextInt(31)+10);
            }
            else if (data.getPosition().equals("Advanced Development")){
                data.setAge(rand.nextInt(30)+10);
            }
            else if (data.getPosition().equals("UI")){
                data.setAge(rand.nextInt(21)+8);
            }
            else if (data.getPosition().equals("Test")){
                data.setAge(rand.nextInt(21)+10);
            }
            else if (data.getPosition().equals("Primary Development")){
                data.setAge(rand.nextInt(21)+4);
            }
            else if (data.getPosition().equals("Intermediate Development")){
                data.setAge(rand.nextInt(24)+5);
            }
            else if (data.getPosition().equals("DBA")){
                data.setAge(rand.nextInt(21)+10);
            } else if (data.getPosition().equals("Front-end Development")){
                data.setAge(rand.nextInt(21)+10);
            }
            // 随机生成性别
            int randNum = rand.nextInt(2);
            if (randNum==0){
                data.setGender("Male");
            }
            else {
                data.setGender("Female");
            }
            // 随机生成学历
            int education = rand.nextInt(2);
            if (education==0){
                data.setEducationalExperience("Master");
            }
            else {
                data.setEducationalExperience("Bachelors");
            }
            // 获取summery 调用chatgpt进行数据扩展
            String summery = currentRow.getCell(num[3]).getStringCellValue();
            String input =summery+"这句话的需求动机是什么以英文回答";
            GPTResponse gptResponse = chatGPT.send(input);
            String output = gptResponse.getChoices().get(0).getText();
            data.setMotivation(output);
            // 详细反馈
            String input2 =summery+"这句话的详细反馈是什么以英文回答";
            GPTResponse response2 = chatGPT.send(input2);
            String output2 = response2.getChoices().get(0).getText();
            data.setFeedBack(output2);
            // 人物需求背景
            String input3 =summery+"这句话的详人物需求背景是什么以英文回答";
            GPTResponse response3 = chatGPT.send(input3);
            String output3 = response3.getChoices().get(0).getText();
            data.setRequirementForApplication(output3);
            list.add(data);
        }

        if(!CollectionUtils.isEmpty(list)){
            //deptPersonInfoMapper.batchInsertPersonInfo(list);
        }
        in.close();
        log.info("数据转换完成！！！");
        // 对转化完成的数据以excel的形式输出
        exportExcel(request,response,list);
        log.info("数据导出完成！！！");
        resultMessage.setSuccess();
        return resultMessage;
    }

    @Override
    public List<PositionDemandType> getPositionDemandType() {
        //获取数据
        List<PositionDemandType> positionDemandType = repository.getPositionDemandType();
        // 转换数据格式
        Map<String,List<Object>> map =new HashMap<>();
        return positionDemandType;
    }

    @Override
    public List<Person> getOtherPerson(Long id, Long positionId) {
        List<Person> person = repository.getOtherPersonByPersonIDAndPositionId(id, positionId);
        // 获取职位信息
        List<position> positionInfo = repository.getPositionInfo();
        for (Person pe : person){
            pe.setImg("https://images.weserv.nl/?url="+pe.getImg());
            // 添加职位id
            positionInfo.forEach(item ->{
                if (pe.getPosition().equals(item.getName())){
                    pe.setPositionId(item.getId());
                }
            });
        }
        return person;
    }

    @Override
    public List<position> getPositionInfo() {
        return repository.getPositionInfo();
    }

    @Override
    public List<demand> getProblemById(Long id) {
        return  repository.getProblemById(id);
    }

    @Override
    public List<Person> getPersonById(Long id) {
        return repository.getPersonById(id);
    }

    /**
     * 根据姓名生成邮箱
     * @param name
     * @return
     */
    public String generateEmail(String name){
        String[] providers = {"gmail.com", "yahoo.com", "hotmail.com", "outlook.com"};
        Random random = new Random();
        int providerIndex = random.nextInt(providers.length);
        String provider = providers[providerIndex];
        int randomNumber = random.nextInt(10000);
        String email = name + randomNumber + "@" + provider;
        return email;
    }





}
