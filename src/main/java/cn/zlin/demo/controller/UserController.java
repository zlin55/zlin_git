package cn.zlin.demo.controller;

import cn.zlin.demo.dao.MeanMapper;
import cn.zlin.demo.dao.UserMapper;
import cn.zlin.demo.domain.*;
import cn.zlin.demo.service.AdminService;
import cn.zlin.demo.service.ComputerInfoService;
import cn.zlin.demo.service.DictionaryService;
import cn.zlin.demo.service.ShiroService;
import cn.zlin.demo.util.Constant;
import cn.zlin.demo.util.DateUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private MeanMapper meanMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private AdminService adminService;
    @Autowired
    private ShiroFilterFactoryBean shiroFilterFactoryBean;
    @Autowired
    private ShiroService shiroService;
    @Autowired
    private DictionaryService dictionaryService;
    @Autowired
    private ComputerInfoService computerInfoService; //注入电脑参数表模块服务

    @RequestMapping("/index")
    public String userIndex(ModelMap map){
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        User bo = userMapper.getPassword(user.getUserName());
        List<Mean> mean = meanMapper.selectUserMean(user.getUserId());
        map.addAttribute("mean",mean);
        bo.setUserRoleCN(dictionaryService.CodeTsfName(Constant.USERROLE,bo.getUserRole()));
        UserDomain userDomain =computerInfoService.getMoneyByUserid(bo.getUserId());
        Long  count =0l;
        if(userDomain!=null){
            count= userDomain.getMoneyCount();
        }
        map.addAttribute("count",count);
        map.addAttribute("user",bo);
        return "computerPage/user";
    }



}
