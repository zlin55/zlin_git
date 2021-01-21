package cn.zlin.demo.domain;

/**
 * 项目名称：余额计算用到的参数
 * 类名称：UserDomain
 * 类描述：
 * 创建人：linTH
 * 创建时间：
 * 修改人：
 * 修改时间：2019/10/7、17:17
 * 修改备注：
 * 版本号：1.0
 **/
public class UserDomain {

    private  Long  moneyCount;
    private  Long  userId;
    private  String  userName;

    public Long getMoneyCount() {
        return moneyCount;
    }

    public void setMoneyCount(Long moneyCount) {
        this.moneyCount = moneyCount;
    }

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
}
