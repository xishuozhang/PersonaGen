package com.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.neo4j.driver.types.Node;
import org.neo4j.ogm.annotation.*;



/**
 *  * @classDesc:
 *  * @Date 2023/3/7 19:33
 *  * @Author:zxs
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@RelationshipEntity(type = "Relation")
public class PersonRelation {
    @Id
    private Long id;

    private Node startNode;

    private Node  endNode;
    @Property
    private String relation;

}
