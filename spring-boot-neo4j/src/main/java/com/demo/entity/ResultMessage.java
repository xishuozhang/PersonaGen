package com.demo.entity;

import com.demo.enums.ResultStatus;

/**
 *  * @classDesc:
 *  * @Date 2023/5/19 16:07
 *  * @Author:zxs
 *
 * @copyright zxs
 */
public class ResultMessage {
    /**
     * //信息说明
     */
    private String msg;
    /**
     * 状态说明
     */
    private String status;

    /**
     * token
     * @return
     */

    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setSuccess(){
        this.status = ResultStatus.SUCCESS.getStatus();
        this.msg = ResultStatus.SUCCESS.getMsg();
    }

    public void setFail(){
        this.status = ResultStatus.FAIL.getStatus();
        this.msg = ResultStatus.FAIL.getMsg();
    }

    public void setFail(String msg){
        this.status = ResultStatus.FAIL.getStatus();
        this.msg = msg;
    }
}
