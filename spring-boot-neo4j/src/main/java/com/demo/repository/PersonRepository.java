package com.demo.repository;

import com.demo.entity.*;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.annotation.QueryResult;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

import javax.jws.WebResult;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public interface PersonRepository extends Neo4jRepository<Person, Long> {

    @Query("MATCH (u:person)\n" +
            "WITH u.position AS position, collect(u)[0] AS person\n" +
            "RETURN person\n" +
            "ORDER BY position")
    List<Person> getAll();


    /**
     * 获取某一职位所有用户
     * @return
     */
    @Query("match(n:person) -[r:serve_as] ->(m:position {name:\"Operation and maintenance\"})\n" +
            "return n")
    List<Person> getPersonByPosition();


    /**
     *根据用户A提出为问题查询相视的问题
     * @return
     */
    @Query("MATCH (userA:person)-[:serve_as]->(positionA:position),(userB:person)-[:serve_as]->(positionA)," +
            "      (userA)-[:user_put_forward]->(questionA:problem)," +
            "      (userB)-[:user_put_forward]->(questionB:problem)" +
            "WHERE id(userA)= {0}" +
            "RETURN questionB")
    List<problem> getProblemByPerson(Long id);


    /**
     * 查询所有职位以及每个职位对应的所有用户
     * @return
     */
    @Query("MATCH (u:person)-[:serve_as]->(j:position)\n" +
            "RETURN j.name as positionName, COLLECT(u.name) as names")
    List<PositionAndName> getAllPersonByPosition();


    /**
     * 根据用户id查询用户信息
     * @param id
     * @return
     */
    //{0}为占位符
    @Query("match (n:person) where id(n)={0} return n")
    List<Person>getPersonById(Long id);


    /**
     * 根据用户id查询用户提出的问题
     * @param id
     * @return
     */
    //@Query("MATCH (u:person)-[:user_put_forward]->(p:demand) WHERE id(u)={0} RETURN p.isType as isType, p.feedBack as feedBack, p.requirementForApplication as requirementForApplication, p.motivation as motivation, id(p) as id")
    @Query("MATCH (u:person)-[:user_put_forward]->(p:demand) WHERE id(u)={0} RETURN p")
    List<demand> getProblemById(Long id);

    /**
     * 根据用户id和职位id推荐本职位其它用户信息
     * @param personID
     * @param positionId
     * @return
     */
    @Query("MATCH (user:person)-[:serve_as]->(p:position)\n" +
            "WHERE  id(user)<>{0}and id(p) = {1}\n" +
            "RETURN user")
    List<Person> getOtherPersonByPersonIDAndPositionId(Long personID,Long positionId);

    @Query("match (p:position) return p")
    List<position> getPositionInfo();

    @Query("MATCH (p:position)-[:put_forward]->(q:demand)-[:belong_to]->(t:type)\n" +
            "RETURN p.name as position, t.name as type,count(q) as num\n" +
            "order by position,type")
    List<PositionDemandType> getPositionDemandType();




    @Query("LOAD CSV FROM \"file:///movie.csv\" AS line\n" +
            "MERGE (n:movie {moviename: line[0]})")
    void importActorsFromCSV();






}


