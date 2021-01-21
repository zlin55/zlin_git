package cn.zlin.demo.domain;

import java.util.Date;

public class Source extends Pages{
    private static final long serialVersionUID = 8920337014985659667L;
    private Long fileId;
    private String fileName;
    private String realName;
    private String fileType;
    private String fileTypeCN;
    private String fileFlod;
    private String fileFlodCN;
    private Date createTime;
    private String createTimeStr;
    private Date updateTime;
    private String updateTimeStr;
    private String updator;

    public Long getFileId() {
        return fileId;
    }

    public void setFileId(Long fileId) {
        this.fileId = fileId;
    }

    public String getFileName() {
        return fileName;
    }

    public String getFileFlod() {
        return fileFlod;
    }

    public void setFileFlod(String fileFlod) {
        this.fileFlod = fileFlod;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
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

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdateTimeStr() {
        return updateTimeStr;
    }

    public void setUpdateTimeStr(String updateTimeStr) {
        this.updateTimeStr = updateTimeStr;
    }

    public String getUpdator() {
        return updator;
    }

    public void setUpdator(String updator) {
        this.updator = updator;
    }

    public String getFileTypeCN() {
        return fileTypeCN;
    }

    public void setFileTypeCN(String fileTypeCN) {
        this.fileTypeCN = fileTypeCN;
    }

    public String getFileFlodCN() {
        return fileFlodCN;
    }

    public void setFileFlodCN(String fileFlodCN) {
        this.fileFlodCN = fileFlodCN;
    }

    @Override
    public String toString() {
        return "Source{" +
                "fileId=" + fileId +
                ", fileName='" + fileName + '\'' +
                ", realName='" + realName + '\'' +
                ", fileType='" + fileType + '\'' +
                ", createTime=" + createTime +
                ", createTimeStr='" + createTimeStr + '\'' +
                ", updateTime=" + updateTime +
                ", updateTimeStr='" + updateTimeStr + '\'' +
                ", updator=" + updator +
                '}';
    }
}
