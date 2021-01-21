package cn.zlin.demo.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class User extends Pages {
    private Long userId;
    private String userName;
    private String userPwd;
    private String userRole;
    private String permissesStr;
    private List<UserPermiss> permisses;
    private Long updator;
    private Date createTime;
    private String createTimeStr;
    private String salt;
    private String avatar;
    private String userRoleCN;
    private String memPhone;


    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public String getPermissesStr() {
        return permissesStr;
    }

    public void setPermissesStr(String permissesStr) {
        this.permissesStr = permissesStr;
    }

    public List<UserPermiss> getPermisses() {
        return permisses;
    }

    public void setPermisses(List<UserPermiss> permisses) {
        this.permisses = permisses;
    }

    public Long getUpdator() {
        return updator;
    }

    public void setUpdator(Long updator) {
        this.updator = updator;
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

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getUserRoleCN() {
        return userRoleCN;
    }

    public void setUserRoleCN(String userRoleCN) {
        this.userRoleCN = userRoleCN;
    }

    public String getMemPhone() {
        return memPhone;
    }

    public void setMemPhone(String memPhone) {
        this.memPhone = memPhone;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", userPwd='" + userPwd + '\'' +
                ", userRole='" + userRole + '\'' +
                ", permissesStr='" + permissesStr + '\'' +
                ", permisses=" + permisses +
                ", updator=" + updator +
                ", createTime=" + createTime +
                ", createTimeStr='" + createTimeStr + '\'' +
                '}';
    }
}
