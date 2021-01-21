package cn.zlin.demo.controller;

import cn.zlin.demo.domain.ResultObj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/guest")
public class GuestController{

    @RequestMapping(value = "/enter")
    public Object login() {
        ResultObj resultObj = new ResultObj(true,"欢迎进入，您的身份是游客！");
        return resultObj;
    }

    @RequestMapping(value = "/getMessage")
    public Object submitLogin() {
        ResultObj resultObj = new ResultObj(true,"您拥有获得该接口的信息的权限！");
        return resultObj;
    }
}

