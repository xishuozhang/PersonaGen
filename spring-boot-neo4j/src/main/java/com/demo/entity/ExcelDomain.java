package com.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 *  * @classDesc:
 *  * @Date 2023/5/20 9:18
 *  * @Author:zxs
 *
 * @copyright zxs
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExcelDomain {
    // Excel标题
    private String bookTitle;

    // Sheet列表
    private List<ExcelSheetDomain> sheetDomainList;
}
