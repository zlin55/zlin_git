package cn.zlin.demo.controller;

import cn.zlin.demo.config.Log;
import cn.zlin.demo.dao.MeanMapper;
import cn.zlin.demo.domain.EUDGPagination;
import cn.zlin.demo.domain.ResultObj;
import cn.zlin.demo.domain.Role;
import cn.zlin.demo.domain.User;
import cn.zlin.demo.service.MeanService;
import cn.zlin.demo.service.RoleService;
import cn.zlin.demo.service.ShiroService;
import org.apache.ibatis.session.RowBounds;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class RoleController {
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

    /**
     * 用户管理
     * @return
     */
    @RequestMapping(value = "/role")
    public String role() {
        return "role";
    }

    /**
     * 权限列表
     * @return
     */
    @ResponseBody
    @RequestMapping("/roleList")
    public Object roleList(Role bo){
        RowBounds rowBounds = new RowBounds((bo.getPage() - 1) * bo.getLimit(), bo.getLimit());
        List<Role> list = roleService.roleList(rowBounds,bo);
        Long count = roleService.countList(rowBounds,bo);
        EUDGPagination pagination = new EUDGPagination(0L,"",count,list);
        return pagination;
    }

    /**
     * 更新权限
     * @param role
     * @return
     */
    @ResponseBody
    @RequestMapping("/updateRole")
    @Log("更新权限")
    public Object updateRole(Role role){
        ResultObj resultObj = new ResultObj(false,null,role);
        //获取登陆信息
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        role.setUserId(user.getUserId());
        Boolean bool = roleService.updateRole(role);
        resultObj.setSuccess(bool);
        return resultObj;
    }

    /**
     * 删除权限
     * @param role
     * @return
     */
    @ResponseBody
    @RequestMapping("/deleteRole")
    @Log("删除权限")
    public Object deleteRole(Role role){
        ResultObj resultObj = new ResultObj(false,null,role);
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        role.setUserId(user.getUserId());
        String resultMap = roleService.deleteRole(role);
        resultObj.setSuccess("".equals(resultMap));
        resultObj.setMessage(resultMap);
        return resultObj;
    }

    /**
     * 新增权限
     * @return
     */
    @ResponseBody
    @RequestMapping("/addRole")
    @Log("新增权限")
    public Object addRole(Role bo){
        ResultObj resultObj = new ResultObj(false,null,bo);
        User user = (User)SecurityUtils.getSubject().getPrincipal();
        bo.setUserId(user.getUserId());
        Boolean bool = roleService.addRole(bo);
        resultObj.setSuccess(bool);
        return resultObj;
    }
}
