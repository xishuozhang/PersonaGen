package com.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 *  * @classDesc:
 *  * @Date 2023/4/22 21:50
 *  * @Author:zxs

 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GPTResponse {

    private String id;

    private String object;

    private String created;

    private String model;

    private List<GPTChoice> choices;
}
