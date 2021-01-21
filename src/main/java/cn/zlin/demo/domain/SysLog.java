package cn.zlin.demo.domain;

import java.io.Serializable;
import java.util.Date;

public class SysLog extends Pages {
    private static final long serialVersionUID = -2232972376370654714L;

    private Long id;
    private Long userId;
    private String userName;
    private String userAction;
    private Date createTime;
    private String createTimeStr;
    private String className;
    private String args;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserAction() {
        return userAction;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }



    public void setUserAction(String userAction) {
        this.userAction = userAction;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreateTimeStr() {
        return createTimeStr;
    }

    public void setCreateTimeStr(String createTimeStr) {
        this.createTimeStr = createTimeStr;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getArgs() {
        return args;
    }

    public void setArgs(String args) {
        this.args = args;
    }
}
