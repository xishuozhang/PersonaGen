package com.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *  * @classDesc:
 *  * @Date 2023/5/19 17:27
 *  * @Author:zxs
 *
 * @copyright zxs
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseData {
    private String name;
    private int age;
    private String gender;
    private String position;
    private String educationalExperience;
    private String isType;
    private String feedBack;
    private String motivation;
    private String requirementForApplication;
}
