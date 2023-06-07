package com.demo.entity;

import lombok.*;
import org.neo4j.ogm.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.QueryResult;

/**
 *  * @classDesc:
 *  * @Date 2023/3/22 14:35
 *  * @Author:zxs
 *
 * @copyright
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@QueryResult
public class PositionAndName {
    private String positionName;
    private String names;
}
