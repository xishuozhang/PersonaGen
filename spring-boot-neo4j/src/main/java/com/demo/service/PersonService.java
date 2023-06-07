package com.demo.service;


import com.demo.entity.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;


public interface PersonService {
    List<Person> getAll();
    List<Person> getPersonById(Long id);
    List<demand> getProblemById(Long id);

    List<position>getPositionInfo();

    List<Person> getOtherPerson(Long id, Long positionId);

    List<PositionDemandType> getPositionDemandType();


    //处理文件
    ResultMessage importExcelDeptStaff(MultipartFile xlsfile, HttpServletRequest request, HttpServletResponse response) throws Exception;

    // 导出数据
    void exportExcel( HttpServletRequest request, HttpServletResponse response,List list);


    //测试导入neo4j节点数据
    void importActorsFromCSV();






}
