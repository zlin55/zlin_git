package cn.zlin.demo.domain;

import javax.xml.crypto.Data;
import java.io.Serializable;
import java.util.Date;

/**
 * @Description: 电脑参数表模块bo对象
 * @Author: linTH
 * @Date: 10-07 17:10:40
 * @Copyright: 2019 福富软件
 */
public class ComputerInfo extends Pages  {

	private Long conId;
	private String conXinghao;
	private String comCpu;
	private String comXianKa;
	private String comNeiCun;
	private String comYingPan;
	private String comZhuangTai;
	private String comBaoYou;
	private String comPinPai;
	private String comWaiGuan;
	private Double comBugPrice;
	private Double comSolePrice;
	private Date createDate;
	private Long createUser;
	private String createUserName;
	private Date udateDate;
	private Long updateUser;
	private String isvalue;
	private  String startTime;	//	开始时间
	private  String  endTime; 	//结束时间
	private  String  createDateStr; //收购日期
	private  String  udateDateStr; //卖出日期
    private  Date startTimeDate;	//	开始时间
    private  Date  endTimeDate;
    private   Date  knockdownDate ;//成交时间
	private   	String  comXuLie; //序列
	private   Double  comZheJia; //折价
	private  String   comYunDan;
	private   String   comShiJiWaiGuan;

	public String getComYunDan() {
		return comYunDan;
	}

	public void setComYunDan(String comYunDan) {
		this.comYunDan = comYunDan;
	}

	public String getComShiJiWaiGuan() {
		return comShiJiWaiGuan;
	}

	public void setComShiJiWaiGuan(String comShiJiWaiGuan) {
		this.comShiJiWaiGuan = comShiJiWaiGuan;
	}

	public Double getComZheJia() {
		return comZheJia;
	}

	public void setComZheJia(Double comZheJia) {
		this.comZheJia = comZheJia;
	}

	public String getComXuLie() {
		return comXuLie;
	}

	public void setComXuLie(String comXuLie) {
		this.comXuLie = comXuLie;
	}

	public Date getKnockdownDate() {
		return knockdownDate;
	}

	public void setKnockdownDate(Date knockdownDate) {
		this.knockdownDate = knockdownDate;
	}

	public Date getStartTimeDate() {
        return startTimeDate;
    }

    public void setStartTimeDate(Date startTimeDate) {
        this.startTimeDate = startTimeDate;
    }

    public Date getEndTimeDate() {
        return endTimeDate;
    }

    public void setEndTimeDate(Date endTimeDate) {
        this.endTimeDate = endTimeDate;
    }

    public String getCreateDateStr() {
		return createDateStr;
	}

	public void setCreateDateStr(String createDateStr) {
		this.createDateStr = createDateStr;
	}

	public String getUdateDateStr() {
		return udateDateStr;
	}

	public void setUdateDateStr(String udateDateStr) {
		this.udateDateStr = udateDateStr;
	}

	public Long getConId() {
		return conId;
	}

	public void setConId(Long conId) {
		this.conId = conId;
	}

	public String getConXinghao() {
		return conXinghao;
	}

	public void setConXinghao(String conXinghao) {
		this.conXinghao = conXinghao;
	}

	public String getComCpu() {
		return comCpu;
	}

	public void setComCpu(String comCpu) {
		this.comCpu = comCpu;
	}

	public String getComXianKa() {
		return comXianKa;
	}

	public void setComXianKa(String comXianKa) {
		this.comXianKa = comXianKa;
	}

	public String getComNeiCun() {
		return comNeiCun;
	}

	public void setComNeiCun(String comNeiCun) {
		this.comNeiCun = comNeiCun;
	}

	public String getComYingPan() {
		return comYingPan;
	}

	public void setComYingPan(String comYingPan) {
		this.comYingPan = comYingPan;
	}

	public String getComZhuangTai() {
		return comZhuangTai;
	}

	public void setComZhuangTai(String comZhuangTai) {
		this.comZhuangTai = comZhuangTai;
	}

	public String getComBaoYou() {
		return comBaoYou;
	}

	public void setComBaoYou(String comBaoYou) {
		this.comBaoYou = comBaoYou;
	}

	public String getComPinPai() {
		return comPinPai;
	}

	public void setComPinPai(String comPinPai) {
		this.comPinPai = comPinPai;
	}

	public String getComWaiGuan() {
		return comWaiGuan;
	}

	public void setComWaiGuan(String comWaiGuan) {
		this.comWaiGuan = comWaiGuan;
	}

	public Double getComBugPrice() {
		return comBugPrice;
	}

	public void setComBugPrice(Double comBugPrice) {
		this.comBugPrice = comBugPrice;
	}

	public Double getComSolePrice() {
		return comSolePrice;
	}

	public void setComSolePrice(Double comSolePrice) {
		this.comSolePrice = comSolePrice;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Long getCreateUser() {
		return createUser;
	}

	public void setCreateUser(Long createUser) {
		this.createUser = createUser;
	}

	public String getCreateUserName() {
		return createUserName;
	}

	public void setCreateUserName(String createUserName) {
		this.createUserName = createUserName;
	}

	public Date getUdateDate() {
		return udateDate;
	}

	public void setUdateDate(Date udateDate) {
		this.udateDate = udateDate;
	}

	public Long getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(Long updateUser) {
		this.updateUser = updateUser;
	}

	public String getIsvalue() {
		return isvalue;
	}

	public void setIsvalue(String isvalue) {
		this.isvalue = isvalue;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}





}