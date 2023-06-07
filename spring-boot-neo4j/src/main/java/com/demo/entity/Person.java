package com.demo.entity;

import lombok.*;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;

import java.io.Serializable;

@Data
@Builder
@NodeEntity("person")
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Person implements Serializable {
    @Id
    @GeneratedValue
    private Long id;

    @Property("name")
    private String name;

    @Property("email")
    private String email;

    private String img;
    @Property("email")
    private String position;
    @Property("age")
    private String age;
    @Property("gender")
    private String gender;
    @Property("educationalExperience")
    private String educationalExperience;

    private Long positionId;
}
