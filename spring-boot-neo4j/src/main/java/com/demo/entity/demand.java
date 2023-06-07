package com.demo.entity;

import lombok.*;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;
import org.springframework.context.annotation.Primary;
import org.springframework.data.neo4j.annotation.QueryResult;

import java.io.Serializable;

/**
 *  * @classDesc:
 *  * @Date 2023/4/5 13:44
 *  * @Author:zxs
 *
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@NodeEntity("demand")
public class demand implements Serializable {
    @Id
    @GeneratedValue
    private Long id;
    @Property("feedBack")
    private String feedBack;
    @Property("requirementForApplication")
    private String requirementForApplication;
    @Property("motivation")
    private String motivation;
    @Property("isType")
    private String isType;
}
