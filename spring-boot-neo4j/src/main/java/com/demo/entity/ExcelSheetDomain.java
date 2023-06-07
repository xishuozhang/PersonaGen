package com.demo.entity;

import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 *  * @classDesc:
 *  * @Date 2023/5/20 9:19
 *  * @Author:zxs
 *
 * @copyright zxs
 */
@Data
public class ExcelSheetDomain {
    // Sheet名称
    private String sheetName;

    // Sheet标题列
    private Map<String,String> titleMap;

    //动态标题变量值
    private List<String> titleValues;

    // Sheet数据集合
    private List<Map<String,String>> sheetDataList;
}
