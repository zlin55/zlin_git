package cn.zlin.demo.controller;

import cn.zlin.demo.config.Log;
import cn.zlin.demo.dao.MeanMapper;
import cn.zlin.demo.domain.*;
import cn.zlin.demo.service.DictionaryService;
import cn.zlin.demo.service.MeanService;
import cn.zlin.demo.service.RoleService;
import cn.zlin.demo.service.ShiroService;
import cn.zlin.demo.util.Constant;
import org.apache.ibatis.session.RowBounds;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class MeanController {
    @Autowired
    private MeanMapper meanMapper;
    @Autowired
    private MeanService meanService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private ShiroFilterFactoryBean shiroFilterFactoryBean;
    @Autowired
    private ShiroService shiroService;
    @Autowired
    private DictionaryService dictionaryService;

    /**
     * 菜单管理
     * @return
     */
    @RequestMapping(value = "/mean")
    public Object mean(ModelMap map) {
        List<Dictionary> dictionary = dictionaryService.getDictionaryByCode(Constant.ISSHOW);
        map.addAttribute("dictionary",dictionary);
        return "mean";
    }

    /**
     * 菜单数据接口
     * @param mean
     * @return
     */
    @ResponseBody
    @RequestMapping("/meanList")
    public Object meanList(Mean mean){
        EUDGPagination eudgPagination = meanService.meanList(mean);
        return eudgPagination;
    }

    /**
     * 更新菜单
     * @param mean
     * @return
     */
    @ResponseBody
    @RequestMapping("/updateMean")
    @Log("更新菜单")
    public Object updateMean(Mean mean){
        ResultObj resultObj = new ResultObj(false,null,mean);
        //获取登陆信息
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        mean.setUserId(user.getUserId());
        Boolean bool = meanService.updateMean(mean);
        resultObj.setSuccess(bool);
        //更新权限
        shiroService.updatePermission(shiroFilterFactoryBean);
        return resultObj;
    }

    /**
     * 删除菜单
     * @param mean
     * @return
     */
    @ResponseBody
    @RequestMapping("/deleteMean")
    @Log("删除菜单")
    public Object deleteMean(Mean mean){
        ResultObj resultObj = new ResultObj(false,null,mean);
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        mean.setUserId(user.getUserId());
        Boolean bool = meanService.deleteMean(mean);
        resultObj.setSuccess(bool);
        //更新权限
        shiroService.updatePermission(shiroFilterFactoryBean);
        return resultObj;
    }

    /**
     * 添加菜单页面
     * @param map
     * @return
     */
    @RequestMapping(value = "/addMean")
    public String addMean(ModelMap map) {
        List<Role> role = roleService.roleList(new RowBounds(),new Role());
        List<Dictionary> dictionary = dictionaryService.getDictionaryByCode(Constant.ISSHOW);
        map.addAttribute("dictionary",dictionary);
        map.addAttribute("role",role);
        return "addMean";
    }

    /**
     * 新增菜单
     * @return
     */
    @ResponseBody
    @RequestMapping("/formMean")
    @Log("新增菜单")
    public Object formMean(Mean bo){
        ResultObj resultObj = new ResultObj(false,null,bo);
        User user = (User)SecurityUtils.getSubject().getPrincipal();
        bo.setUserId(user.getUserId());
        try {
            Boolean bool = meanService.addMean(bo);
            resultObj.setSuccess(bool);
        }catch (Exception e){
            resultObj.setMessage(e.getMessage());
        }
        //更新权限
        shiroService.updatePermission(shiroFilterFactoryBean);
        return resultObj;
    }


    /**
     * 菜单权限修改页面
     * @param map
     * @return
     */
    @RequestMapping(value = "/meanRole")
    public String meanRole(ModelMap map) {
        List<Role> role = roleService.roleList(new RowBounds(),new Role());
        map.addAttribute("role",role);
        return "meanRole";
    }

    /**
     * 修改菜单权限
     * @param mean
     * @return
     */
    @ResponseBody
    @RequestMapping("alterRole")
    @Log("菜单权限配置")
    public Object alterRole(Mean mean){
        ResultObj resultObj = new ResultObj(false,null,mean);
        User user = (User)SecurityUtils.getSubject().getPrincipal();
        mean.setUserId(user.getUserId());
        Boolean bool = meanService.alterRole(mean);
        resultObj.setSuccess(bool);
        //更新权限
        shiroService.updatePermission(shiroFilterFactoryBean);
        return  resultObj;
    }
}
