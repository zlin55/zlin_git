package cn.zlin.demo.controller;

import cn.zlin.demo.config.CustomRealm;
import cn.zlin.demo.config.Log;
import cn.zlin.demo.config.ShiroConfig;
import cn.zlin.demo.dao.MeanMapper;
import cn.zlin.demo.dao.UserMapper;
import cn.zlin.demo.domain.*;
import cn.zlin.demo.service.*;
import cn.zlin.demo.util.Constant;
import cn.zlin.demo.util.MD5util;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.apache.ibatis.session.RowBounds;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.ModularRealmAuthorizer;
import org.apache.shiro.mgt.RealmSecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {
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
    /**
     * 管理员视图
     * @param map
     * @return
     */
    @RequestMapping("/index")
    public String admain(ModelMap map){
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        User bo = userMapper.getPassword(user.getUserName());
        List<Mean> mean = meanMapper.selectUserMean(user.getUserId());
        UserDomain userDomain =computerInfoService.getMoneyByUserid(bo.getUserId());
        Long  count =0l;
        if(userDomain!=null){
            count= userDomain.getMoneyCount();
        }
        map.addAttribute("count",count);
        map.addAttribute("mean",mean);
        bo.setUserRoleCN(dictionaryService.CodeTsfName(Constant.USERROLE,bo.getUserRole()));
        map.addAttribute("user",bo);

        return "admain";
    }

    @ResponseBody
    @RequestMapping(value = "/page")
    public Object page() {
        ResultObj resultObj = new ResultObj(true,"您拥有管理员权限，可以获取该接口信息！");
        return resultObj;
    }

    /**
     * 用户管理
     * @return
     */
    @RequestMapping(value = "/user")
    public Object user(ModelMap map) {
        List<Dictionary> dictionary = dictionaryService.getDictionaryByCode(Constant.USERROLE);
        map.addAttribute("dictionary",dictionary);
        return "user";
    }

    /**
     * 用户列表数据
     */
    @ResponseBody
    @RequestMapping(value = "/ListUser")
    public Object ListUser(User user) {
        EUDGPagination pagination = adminService.ListUser(user);
        return pagination;
    }

    /**
     * 删除用户
     */
    @ResponseBody
    @RequestMapping(value = "/deleteUser")
    @Log("删除用户")
    public Object deleteUser(User user) {
        ResultObj resultObj = new ResultObj(false,null,user);
        User bo = (User) SecurityUtils.getSubject().getPrincipal();
        user.setUpdator(bo.getUserId());
        Boolean bool = adminService.deleteUser(user);
        resultObj.setSuccess(bool);
        return resultObj;
    }

    /**
     * 修改密码
     */
    @RequestMapping(value = "/editUserPwd")
    public Object editUserPwd(ModelMap map) {
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        User bo = userMapper.getPassword(user.getUserName());
        map.addAttribute("userId",bo.getUserId());
        return "editPwd";
    }

    /**
     * 用户管理
     * @return
     */
    @RequestMapping(value = "/grant")
    public Object grant() {
        return "grant";
    }

    /**
     * 授权
     * @param roleIds
     * @param userId
     * @return
     */
    @ResponseBody
    @RequestMapping("/grantRole")
    @Log("用户授权")
    public Object grantRole(String roleIds,Long userId){
        ResultObj resultObj = new ResultObj(false,null,roleIds);
        try {
            Boolean bool = adminService.grantRole(roleIds, userId);
            resultObj.setSuccess(bool);
        }catch(Exception e){
            resultObj.setMessage(e.getMessage());
            return resultObj;
        }
        return resultObj;
    }

    /**
     * 修改密码
     */
    @ResponseBody
    @RequestMapping("/resetPwd")
    @Log("修改密码")
    public Object resetPwd(User user){
        ResultObj resultObj = new ResultObj(false,null,user);
        Boolean bool = adminService.resetPwd(user);
        resultObj.setSuccess(bool);
        return resultObj;
    }

    /**
     * 修改用户角色
     */
    @ResponseBody
    @RequestMapping("/alterUserRole")
    @Log("修改用户角色")
    public Object alterUserRole(User user){
        ResultObj resultObj = new ResultObj(false,null,user);
        User bo = (User) SecurityUtils.getSubject().getPrincipal();
        user.setUpdator(bo.getUserId());
        Boolean bool = adminService.alterUserRole(user);
        resultObj.setSuccess(bool);
        return resultObj;
    }


    /**
     * 新建用户页面
     * @return
     */
    @RequestMapping("/addUserRole")
    public String addUserRole(ModelMap map){
        List<Dictionary> dictionary = dictionaryService.getDictionaryByCode(Constant.USERROLE);
        map.addAttribute("dictionary",dictionary);
        return "addRole";
    }

    /**
     * 新建用户
     */
    @ResponseBody
    @RequestMapping("/formUser")
    @Log("新建用户")
    public Object formUser(User user){
        ResultObj resultObj = new ResultObj(false,null,user);
        try {
            Boolean bool = adminService.addUser(user);
            resultObj.setSuccess(bool);
        }catch (Exception e){
            resultObj.setMessage(e.getMessage());
        }
        return resultObj;
    }
}
