package com.demo.utils;

import javax.servlet.http.HttpServletRequest;

/**
 *  * @classDesc:
 *  * @Date 2023/5/20 9:22
 *  * @Author:zxs
 *
 * @copyright zxs
 */
public class ClientInfo {
    private String info = "";
    private HttpServletRequest request;
    private String explorerVer = "未知";
    private String OSVer = "未知";

    /*
     * 构造函数 参数：String request.getHeader("user-agent")
     *
     * IE7:Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 6.1; Trident/4.0;
     * SLCC2; .NET CLR 2.0.50727; .NET CLR 3.5.30729; .NET CLR 3.0.30729; Media
     * Center PC 6.0; .NET4.0C) IE8:Mozilla/4.0 (compatible; MSIE 8.0; Windows
     * NT 6.1; Trident/4.0; SLCC2; .NET CLR 2.0.50727; .NET CLR 3.5.30729; .NET
     * CLR 3.0.30729; Media Center PC 6.0; .NET4.0C) Maxthon:Mozilla/4.0
     * (compatible; MSIE 7.0; Windows NT 6.1; Trident/4.0; SLCC2; .NET CLR
     * 2.0.50727; .NET CLR 3.5.30729; .NET CLR 3.0.30729; Media Center PC 6.0;
     * .NET4.0C; Maxthon 2.0) firefox:Mozilla/5.0 (Windows; U; Windows NT 6.1;
     * zh-CN; rv:1.9.2.11) Gecko/20101012 Firefox/3.6.11 Chrome:Mozilla/5.0
     * (Windows; U; Windows NT 6.1; en-US) AppleWebKit/534.7 (KHTML, like Gecko)
     * Chrome/7.0.517.44 Safari/534.7 Opera:Opera/9.80 (Windows NT 6.1; U;
     * zh-cn) Presto/2.6.30 Version/10.63
     *
     * 操作系统： Win7 : Windows NT 6.1 WinXP : Windows NT 5.1
     */
    public ClientInfo(String info, HttpServletRequest request) {
        this.info = info;
        this.request = request;
    }

    /*
     * 获取核心浏览器名称
     */
    public String getExplorerName() {
        String str = "未知";
        if (info.indexOf("MSIE") != -1) {
            str = "IE"; // 微软IE

        } else if (info.indexOf("Firefox") != -1) {
            str = "Firefox"; // 火狐

        } else if (info.indexOf("Chrome") != -1) {
            str = "Chrome"; // Google

        } else if (info.indexOf("Opera") != -1) {
            str = "Opera"; // Opera

        } else if (info.indexOf("Apple") != -1) {
            str = "Safari";
        }
        return str;
    }

    /*
     * 获取核心浏览器版本
     */
    public String getExplorerVer() {
        return this.explorerVer;
    }

    /*
     * 获取浏览器插件名称（例如：遨游、世界之窗等）
     */
    public String getExplorerPlug() {
        String str = "无";
        if (info.indexOf("Maxthon") != -1)
            str = "Maxthon"; // 遨游
        return str;
    }


    /*
     * 获取操作系统版本
     */
    public String getOSVer() {
        return this.OSVer;
    }

    /*
     * 获取客户端的IP
     */

    public String getIp() {
        if (request.getHeader("x-forwarded-for") == null) {
            return request.getRemoteAddr().trim();
        }
        return request.getHeader("x-forwarded-for");
    }

    /*
     * 获取客户端访问的URL
     */
    public String getUrl() {

        HttpServletRequest httpRequest = request;
        String strBackUrl = "http://" + request.getServerName() // 服务器地址
                + ":" + request.getServerPort() // 端口号
                + httpRequest.getContextPath() // 项目名称
                + httpRequest.getServletPath() // 请求页面或其他地址
                + "?" + (httpRequest.getQueryString()); // 参数

        return strBackUrl.trim();
    }
}
