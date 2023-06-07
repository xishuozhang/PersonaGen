package com.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *  * @classDesc:
 *  * @Date 2023/4/22 21:51
 *  * @Author:zxs
 *
 * @copyright
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GPTChoice {

    private String text;

    private Integer index;
}