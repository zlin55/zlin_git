package cn.zlin.demo.controller;

import cn.zlin.demo.dao.UserMapper;
import cn.zlin.demo.domain.ResultObj;
import cn.zlin.demo.domain.User;
import cn.zlin.demo.util.MD5util;
import com.alibaba.fastjson.JSONObject;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
public class LoginController {
    @Autowired
    private UserMapper userMapper;

    @RequestMapping(value = "/notRole")
    public String notRole() {
        return "403";
    }

    @RequestMapping(value = "/logout")
    public String logout() {
        Subject subject = SecurityUtils.getSubject();
        //注销
        subject.logout();
        return "login";
    }

    /**
     * 登陆
     *
     * @param user 用户实体
     */
    @ResponseBody
    @RequestMapping(value = "/login")
    public Object login(User user, String imageCode, String model, String code, HttpSession session) {
        ResultObj resultObj = new ResultObj(false,null);
        if("1".equals(model)){
            if(!session.getAttribute("checkcode").equals(imageCode)){
                resultObj.setMessage("验证码有误!");
                return resultObj;
            }
            User bo = userMapper.getPassword(user.getUserName());
            String rpwd;
            if(bo != null){
                //二次加盐加密的密码
                rpwd = MD5util.formPassToDBPass(user.getUserPwd(),bo.getSalt());
            }else{
                rpwd = user.getUserPwd();
            }
            user.setUserPwd(rpwd);
        }else{
            JSONObject userCode = (JSONObject)session.getAttribute("code");
            if(userCode == null){
                resultObj.setMessage("请先获取验证码!");
                return resultObj;
            }
            if(!userCode.get("code").equals(code) || !userCode.get("memPhone").equals(user.getMemPhone())){
                resultObj.setMessage("验证码有误!");
                return resultObj;
            }
            User sendUser = userMapper.getUserInfoByPhone(user.getMemPhone());
            if(sendUser == null){
                resultObj.setMessage("该手机号还未绑定用户!");
                return resultObj;
            }
            user.setUserName(sendUser.getUserName());
            user.setUserPwd(sendUser.getUserPwd());
            session.removeAttribute("code");
        }
        // 从SecurityUtils里边创建一个 subject
        Subject subject = SecurityUtils.getSubject();
        // 在认证提交前准备 token（令牌）
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUserName(), user.getUserPwd());
        // 执行认证登陆
        subject.login(token);
        //根据权限，指定返回数据
        String role = userMapper.getRole(user.getUserName());
        resultObj.setData(role);
        resultObj.setSuccess(true);
        resultObj.setMessage("权限错误!");
        return resultObj;
    }
}

