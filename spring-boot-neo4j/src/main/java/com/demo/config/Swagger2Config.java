package com.demo.config;

import cn.weiguangfu.swagger2.plus.annotation.EnableSwagger2Plus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.ArrayList;

/**
 *  * @classDesc:
 *  * @Date 2022/7/7 10:40
 *  * @Author:zxs
 *
 * @copyright
 */
@Configuration
@EnableSwagger2Plus
public class Swagger2Config {
    @Bean
    public Docket docket()
    {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(new ApiInfo(
                        "接口文档",       					 // swagger页面标题
                        "该文档描述了**项目接口信息",   	// swagger页面描述
                        "1.1",          					// 标题右边的版本号
                        "",      							// 留空
                        new Contact("", "", ""),   			// 作者联系方式
                        "",									// license
                        "",									// license的url
                        new ArrayList()))
                .groupName("group1")  // 分组名称
                // 指定扫描接口的包，select和build成组出现
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.demo.controller"))
                .build();
    }

}
