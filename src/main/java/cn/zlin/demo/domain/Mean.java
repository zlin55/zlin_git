package cn.zlin.demo.domain;

import java.io.Serializable;

public class Mean extends Pages {
    private Long meanId;
    private String meanName;
    private String meanUrl;
    private Long roleId;
    private String roleName;
    private Long userId;
    private String isMean;
    private String isMeanCN;

    public Long getMeanId() {
        return meanId;
    }

    public void setMeanId(Long meanId) {
        this.meanId = meanId;
    }

    public String getMeanName() {
        return meanName;
    }

    public void setMeanName(String meanName) {
        this.meanName = meanName;
    }

    public String getMeanUrl() {
        return meanUrl;
    }

    public void setMeanUrl(String meanUrl) {
        this.meanUrl = meanUrl;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getIsMean() {
        return isMean;
    }

    public void setIsMean(String isMean) {
        this.isMean = isMean;
    }

    public String getIsMeanCN() {
        return isMeanCN;
    }

    public void setIsMeanCN(String isMeanCN) {
        this.isMeanCN = isMeanCN;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    @Override
    public String toString() {
        return "Mean{" +
                "meanId=" + meanId +
                ", meanName='" + meanName + '\'' +
                ", meanUrl='" + meanUrl + '\'' +
                ", roleId=" + roleId +
                ", roleName='" + roleName + '\'' +
                ", userId=" + userId +
                ", isMean='" + isMean + '\'' +
                ", isMeanCN='" + isMeanCN + '\'' +
                '}';
    }
}
