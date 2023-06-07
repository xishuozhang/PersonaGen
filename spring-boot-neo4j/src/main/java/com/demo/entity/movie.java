package com.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;

/**
 *  * @classDesc:测试动态创建neo4j节点
 *  * @Date 2023/5/23 18:59
 *  * @Author:zxs
 *
 * @copyright zxs
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@NodeEntity
public class movie {
    @Property("movieName")
    private String movieName;
}
