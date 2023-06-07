package com.demo.controller;

import com.demo.entity.*;
import com.demo.repository.PersonRelationRepository;
import com.demo.entity.PersonRelation;
import com.demo.repository.PersonRepository;
import com.demo.service.PersonService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api(tags = "neo4j测试")
@RestController
@RequestMapping("/neo4j")
public class Neo4jController {


    @Autowired
    PersonRepository repository;
    @Autowired
    PersonRelationRepository personRelationRepository;

    @Autowired
    PersonService service;


    @ApiOperation("获取全部节点")
    @GetMapping("/getAll")
    public List<Person> getAll(){
        return repository.getAll();
    }
    @ApiOperation("获取某一职位所有用户")
    @PostMapping ("/getPersonByPosition")
    public List<Person>getPersonByPosition(){
        return repository.getPersonByPosition();
    }

    @ApiOperation("根据用户提出的问题查询相视的问题")
    @PostMapping ("/getProblemByPerson")
    public List<problem> getProblemByPerson(Long id){
        return repository.getProblemByPerson(id);
    }


    @ApiOperation("查询所有职位以及每个职位对应的所有用户的信息")
    @GetMapping ("/getAllPersonByPosition")
     public List<PositionAndName> getAllPersonByPosition(){
        return  repository.getAllPersonByPosition();

    }


    @ApiOperation("根据用户推荐其它用户")
    @GetMapping("/getOtherPerson")
    public List<Person> getOtherPersonByPersonIDAndPositionId(Long personId, Long positionId){
        return  service.getOtherPerson(personId,positionId);
    }






}
