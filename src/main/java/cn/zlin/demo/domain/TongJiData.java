package cn.zlin.demo.domain;

/**
 * 项目名称：
 * 类名称：TongJiData
 * 类描述：
 * 创建人：linTH
 * 创建时间：
 * 修改人：
 * 修改时间：2019/10/7、17:27
 * 修改备注：
 * 版本号：1.0
 **/
public class TongJiData {

     private  Long  userId;
     private  String  userName;
     private  Long   computerNum;//所销售的电脑数量

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

     public Long getComputerNum() {
          return computerNum;
     }

     public void setComputerNum(Long computerNum) {
          this.computerNum = computerNum;
     }
}
