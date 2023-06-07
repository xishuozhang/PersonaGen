package com.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.neo4j.annotation.QueryResult;

/**
 *  * @classDesc:
 *  * @Date 2023/4/8 17:34
 *  * @Author:zxs
 *
 * @copyright
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@QueryResult
public class PositionDemandType {
    private String position;
    private String type;
    private Long num;
}
