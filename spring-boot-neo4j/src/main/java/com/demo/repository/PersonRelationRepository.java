package com.demo.repository;

import com.demo.entity.Person;
import com.demo.entity.PersonRelation;

import org.neo4j.ogm.model.Node;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;


import java.util.List;
@Repository
public interface PersonRelationRepository extends Neo4jRepository<PersonRelation,Long> {

    @Query("MATCH p=(n:Person)-[r:Relation]->(m:Person) " +
            "WHERE id(n)={startNode} and id(m)={endNode} and r.relation={relation}" +
            "RETURN p"
    )
    List<PersonRelation> getRelation(Node startNode, Node endNode, String relation);


}

