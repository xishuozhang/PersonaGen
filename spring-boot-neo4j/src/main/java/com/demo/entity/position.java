package com.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.neo4j.ogm.annotation.NodeEntity;

/**
 *  * @classDesc:
 *  * @Date 2023/4/7 23:54
 *  * @Author:zxs
 *
 * @copyright
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@NodeEntity("position")
public class position {
    private Long id;
    private String name;
}
