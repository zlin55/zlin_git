package cn.zlin.demo.controller;

import cn.zlin.demo.dao.UserMapper;
import cn.zlin.demo.domain.User;
import javafx.scene.Parent;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
public class DemoController {
    @Autowired
    UserMapper userMapper;

    @RequestMapping("/index")
    public String index(){
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("a","abc");
        User user = (User)SecurityUtils.getSubject().getPrincipal();
        return "index";
    }
}
