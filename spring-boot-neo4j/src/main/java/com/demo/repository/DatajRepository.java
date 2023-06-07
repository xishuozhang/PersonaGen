package com.demo.repository;

import org.springframework.data.neo4j.repository.Neo4jRepository;

/**
 *  * @classDesc:创建实体节点和导入数据
 *  * @Date 2023/5/23 19:40
 *  * @Author:zxs
 *
 * @copyright zxs
 */
public interface DatajRepository<T> extends Neo4jRepository<T, Long> {


}
