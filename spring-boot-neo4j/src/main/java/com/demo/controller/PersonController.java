package com.demo.controller;

import com.demo.entity.Person;
import com.demo.entity.PositionDemandType;
import com.demo.entity.demand;
import com.demo.entity.position;
import com.demo.service.PersonService;
import com.sun.org.apache.xml.internal.resolver.helpers.PublicId;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 *  * @classDesc:
 *  * @Date 2023/3/27 19:58
 *  * @Author:zxs
 *
 * @copyright
 */
@Api(tags = "Person")
@RestController
@RequestMapping("/person")
public class PersonController {
    @Autowired
    PersonService service;
    @ApiOperation("获取每个职位的第一个用户节点")
    @GetMapping("/getAll")
    public List<Person>getAll(){
        return service.getAll();
    }

    @ApiOperation("根据用户id查询用户信息")
    @GetMapping("/getPersonById")
    public List<Person> getPersonById(Long id){
        return service.getPersonById(id);
    }
    @ApiOperation("根据用户id查询用户提出的问题")
    @GetMapping("/getProblemById")
    public List<demand> getProblemById(Long id){
        return service.getProblemById(id);
    }


    @ApiOperation("获取职位信息")
    @GetMapping("/getPositionInfo")
    public List<position> getPositionInfo(){
        return service.getPositionInfo();
    }

    @ApiOperation("获取职位提出的问题类型数量")
    @GetMapping("/getPositionDemandType")
    public List<PositionDemandType> getPositionDemandTYpe(){
        return service.getPositionDemandType();
    }

    @ApiOperation("测试导入节点数据")
    @GetMapping("/import")
    public void importActorsFromCSV(){
        service.importActorsFromCSV();
    }










}
