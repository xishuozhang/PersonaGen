package com.demo.enums;

public enum ResultStatus {
    SUCCESS("操作成功","0"),
    FAIL("操作失败", "3"),
    EXCEPTION("访问报错了","4"),
    FAIL_DELETE_NODE("当前节点有子节点存在,无法删除","5"),
    FAIL_LOGIN_NO_USER("未查询到当前登录用户","6"),
    FAIL_LOGIN_ERR_PSSSWORD("用户名或密码错误","7"),
    FAIL_ADDACCOUNT_REPEAT("账户号不能重复","8"),
    FAIL_ADDACCOUNT_NOACCOUNT("用户名不能为空","9"),
    FAIL_FILE_READER("文件读取失败,请检查文件格式","10"),
    FAIL_FILE_ADD("批量添加用户的时候报错了","11");



    private String status; // 状态
    private String msg; // 状态说明

    ResultStatus(String msg, String status) {
        this.status = status;
        this.msg = msg;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
