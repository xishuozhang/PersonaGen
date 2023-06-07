package com.liuyuncen.config;

import org.neo4j.driver.AuthTokens;
import org.neo4j.driver.Driver;
import org.neo4j.driver.GraphDatabase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @belongsProject: SpringBoot-Neo4j
 * @belongsPackage: com.liuyuncen.config
 * @author: Xiang想
 * @createTime: 2022-09-28  15:56
 * @description: TODO
 * @version: 1.0
 */
@Configuration
public class Neo4jDriver {


    @Bean
    public Driver driver(){
        return GraphDatabase.driver("bolt://localhost:7687", AuthTokens.basic("neo4j","password"));
    }
}
