package com.demo.utils;

import com.demo.entity.ExcelDomain;
import com.demo.entity.ExcelSheetDomain;
import jxl.Workbook;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.write.*;
import jxl.write.Number;
import org.apache.commons.collections4.map.CaseInsensitiveMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 *  * @classDesc:
 *  * @Date 2023/5/20 9:20
 *  * @Author:zxs
 *
 * @copyright zxs
 */
public class ExcelUtils {
    /**
     * 导出Excel表
     *
     * @param request
     * @param response
     * @param excelDomain
     *            Excel业务模型
     * @return
     * @throws Exception
     */
    public static String exportExcel(HttpServletRequest request,
                                     HttpServletResponse response, ExcelDomain excelDomain)
            throws Exception {
        if (null != excelDomain) {
            String bookTitle = excelDomain.getBookTitle();
            String charsetStr = "UTF-8";
            ClientInfo clientInfo = new ClientInfo(
                    request.getHeader("user-agent"), request);
            String servlet = clientInfo.getExplorerName();
            if (servlet == "Firefox") {
                response.addHeader(
                        "Content-Disposition",
                        "attachment;filename="
                                + new String(bookTitle.getBytes(StandardCharsets.UTF_8),
                                StandardCharsets.ISO_8859_1)+".xls");
            } else {
                response.addHeader(
                        "Content-Disposition",
                        "attachment; filename="
                                + URLEncoder.encode(bookTitle, "UTF-8")+".xls");
            }
            response.setContentType("application/vnd.ms-excel;charset="
                    + charsetStr);
            OutputStream os = response.getOutputStream();
            WritableWorkbook book = createExcel(os);
            List<ExcelSheetDomain> sheetDomainList = excelDomain
                    .getSheetDomainList();
            for (ExcelSheetDomain sheetDomain : sheetDomainList) {
                String sheetName = sheetDomain.getSheetName();
                WritableSheet ws = addExcelSheet(book, sheetName);
                Map<String, String> titleMap = sheetDomain.getTitleMap();
                int rowIndex = 0;
                if (titleMap.size() != 0) {
                    Iterator<String> colKey = titleMap.keySet().iterator();
                    int colIndex = 0;
                    while (colKey.hasNext()) {
                        Object key = colKey.next();
                        ws.addCell(createCellTextHeader(colIndex,
                                rowIndex, titleMap.get(key)));
                        ++colIndex;
                    }
                    ++rowIndex;
                    ws.getSettings().setVerticalFreeze(1);
                }
                List<Map<String, String>> dataList = sheetDomain
                        .getSheetDataList();
                int dataSize = 0;
                if (null != dataList) {
                    dataSize = dataList.size();
                }
                for (int i = 0; i < dataSize; ++i) {
                    Map<String, String> map = dataList
                            .get(i);
                    CaseInsensitiveMap imap = new CaseInsensitiveMap(map);
                    int colIndex = 0;
                    Iterator<String> colKey = titleMap.keySet().iterator();
                    while (colKey.hasNext()) {
                        Object key = colKey.next();
                        Object value = imap.get(key);
                        if (value == null || "null".equals(value)) {
                            value = "";
                        }
                        ws.addCell(createCell(colIndex, rowIndex,
                                value));
                        ++colIndex;
                    }
                    ++rowIndex;
                }
                ws.getSettings().setProtected(false);
            }
            book.setProtected(false);
            book.write();
            book.close();
        }
        return null;
    }

    public static WritableWorkbook createExcel(OutputStream os) {
        WritableWorkbook book = null;
        try {
            book = Workbook.createWorkbook(os);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return book;
    }

    public static WritableSheet addExcelSheet(WritableWorkbook book,
                                              String sheetName) {
        WritableSheet ws = null;
        if ((null == sheetName) || (sheetName.trim().length() < 1)) {
            sheetName = "sheet" + (book.getNumberOfSheets() + 1);
        }
        ws = book.createSheet(sheetName, book.getNumberOfSheets());
        ws.getSettings().setDefaultColumnWidth(15);
        return ws;
    }

    public static WritableCell createCellTextHeader(int colIndex, int rowIndex,
                                                    String str) {
        WritableCellFormat format = new WritableCellFormat(new WritableFont(
                WritableFont.ARIAL, 11, WritableFont.BOLD));
        try {
            format.setAlignment(jxl.format.Alignment.CENTRE);
            format.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
            format.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN);
            format.setBackground(Colour.GRAY_25);
            format.setWrap(true);
            format.setLocked(true);
        } catch (WriteException e) {
            e.printStackTrace();
        }

        return createCellTextHeader(colIndex, rowIndex, str, format);
    }
    private static WritableCell createCellTextHeader(int colIndex,
                                                     int rowIndex, String str, WritableCellFormat format) {
        return new Label(colIndex, rowIndex, str, format);
    }

    public static WritableCell createCell(int colIndex, int rowIndex, Object obj) {
        if (null == obj){
            return createCellText(colIndex, rowIndex, "");
        }

        if ((obj instanceof Integer) || (obj instanceof Double)
                || (obj instanceof BigDecimal) || (obj instanceof Float)) {
            return createCellNumber(colIndex, rowIndex, obj.toString());
        }
        return createCellText(colIndex, rowIndex, obj.toString());
    }

    private static WritableCell createCellText(int colIndex, int rowIndex,
                                               String str) {
        WritableCellFormat format = new WritableCellFormat(new WritableFont(
                WritableFont.ARIAL, 10));
        try {
            format.setAlignment(jxl.format.Alignment.LEFT);
            format.setBorder(jxl.format.Border.ALL, BorderLineStyle.THIN);
        } catch (WriteException e) {
            e.printStackTrace();
        }
        return new Label(colIndex, rowIndex, str, format);
    }

    private static WritableCell createCellNumber(int colIndex, int rowIndex,
                                                 String str) {
        WritableCellFormat format = new jxl.write.WritableCellFormat(new WritableFont(
                WritableFont.ARIAL, 10));//设定带小数数字单元格格式，wcf见上
        try {
            format.setAlignment(jxl.format.Alignment.RIGHT);
            format.setBorder(Border.ALL, BorderLineStyle.THIN);
        } catch (WriteException e) {
            e.printStackTrace();
        }
        return new Number(colIndex, rowIndex, getNumber(str, 0.0D), format);
    }
    private static double getNumber(String str, double defNum) {
        try {
            return Double.parseDouble(str);
        } catch (NumberFormatException e) {

        }
        return defNum;
    }
}
