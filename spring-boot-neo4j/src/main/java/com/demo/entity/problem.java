package com.demo.entity;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;

/**
 *  * @classDesc:
 *  * @Date 2023/3/22 11:18
 *  * @Author:zxs
 *
 * @copyright
 */
@Data
@Builder
@NodeEntity("problem")
@ToString
public class problem {
    @Id
    @GeneratedValue
    private Long id;

    @Property("name")
    private String name;
}
