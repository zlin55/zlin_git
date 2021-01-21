package cn.zlin.demo.domain;

import java.io.Serializable;
import java.util.List;

public class Dictionary extends Pages{
    private Long Id;
    private String parCode;
    private String dicCode;
    private String dicName;
    private String picCode;
    private List<Dictionary> dicContent;
    private String dicContentStr;
    private Long updator;
    private String isDic;

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getParCode() {
        return parCode;
    }

    public void setParCode(String parCode) {
        this.parCode = parCode;
    }

    public String getDicCode() {
        return dicCode;
    }

    public void setDicCode(String dicCode) {
        this.dicCode = dicCode;
    }

    public String getDicName() {
        return dicName;
    }

    public void setDicName(String dicName) {
        this.dicName = dicName;
    }

    public List<Dictionary> getDicContent() {
        return dicContent;
    }

    public void setDicContent(List<Dictionary> dicContent) {
        this.dicContent = dicContent;
    }

    public String getDicContentStr() {
        return dicContentStr;
    }

    public void setDicContentStr(String dicContentStr) {
        this.dicContentStr = dicContentStr;
    }

    public String getPicCode() {
        return picCode;
    }

    public void setPicCode(String picCode) {
        this.picCode = picCode;
    }

    public Long getUpdator() {
        return updator;
    }

    public void setUpdator(Long updator) {
        this.updator = updator;
    }

    public String getIsDic() {
        return isDic;
    }

    public void setIsDic(String isDic) {
        this.isDic = isDic;
    }

    @Override
    public String toString() {
        return "Dictionary{" +
                "Id=" + Id +
                ", parCode='" + parCode + '\'' +
                ", dicCode='" + dicCode + '\'' +
                ", dicName='" + dicName + '\'' +
                ", picCode='" + picCode + '\'' +
                ", dicContent=" + dicContent +
                ", dicContentStr='" + dicContentStr + '\'' +
                ", updator=" + updator +
                ", isDic='" + isDic + '\'' +
                '}';
    }
}
