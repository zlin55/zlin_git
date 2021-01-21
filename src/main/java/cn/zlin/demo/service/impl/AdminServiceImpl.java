package cn.zlin.demo.service.impl;

import cn.zlin.demo.dao.UserMapper;
import cn.zlin.demo.domain.EUDGPagination;
import cn.zlin.demo.domain.Role;
import cn.zlin.demo.domain.User;
import cn.zlin.demo.domain.UserPermiss;
import cn.zlin.demo.service.AdminService;
import cn.zlin.demo.util.MD5util;
import org.apache.ibatis.session.RowBounds;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class AdminServiceImpl implements AdminService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public EUDGPagination ListUser(User bo) {
        String str = "";
        RowBounds rowBounds = new RowBounds((bo.getPage() - 1) * bo.getLimit(), bo.getLimit());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        List<User> list = userMapper.ListUser(rowBounds,bo);
        for(User user : list){
            user.setCreateTimeStr(sdf.format(user.getCreateTime()));
            List<UserPermiss> permisses = userMapper.ListPermiss(user);
            for(UserPermiss userPermiss : permisses){
                str += "【" + userPermiss.getRoleName() + "】";
            }
            user.setPermissesStr(str);
            user.setPermisses(permisses);
            str = "";
        }
        Long count = userMapper.countList(rowBounds,bo);
        EUDGPagination pagination = new EUDGPagination(0L,"",count,list);
        return pagination;
    }

    @Override
    public Boolean deleteUser(User user) {
        Long count = userMapper.deleteUser(user);
        return count > 0 ? true :false;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean grantRole(String roleIds,Long userId) throws Exception{
        String[] roleId = roleIds.split(",");
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        try {
            //先删除该用户的所有权限
            Long result = userMapper.delUserRole(userId, user.getUserId());
            //再插入权限
            Role role = null;
            List<Role> list = new ArrayList<Role>();
            for (int i = 0; i < roleId.length; i++) {
                role = new Role(userId, Long.valueOf(roleId[i]), user.getUserId());
                list.add(role);
            }
            Long count = userMapper.insertRole(list);
        }catch (Exception e){
            throw new Exception("授权失败");
        }
        return true;
    }

    @Override
    public Boolean resetPwd(User user) {
        User bo = (User) SecurityUtils.getSubject().getPrincipal();
        //生成随机盐
        String salt = MD5util.createSalt();
        user.setUpdator(bo.getUserId());
        user.setSalt(salt);
        user.setUserPwd(MD5util.formPassToDBPass(user.getUserPwd(),salt));
        Long count = userMapper.resetPwd(user);
        return count > 0 ? true : false;
    }

    @Override
    public Boolean alterUserRole(User user) {
        Long count = userMapper.alterUserRole(user);
        return count > 0 ? true : false;
    }

    @Override
    public Boolean addUser(User user) throws Exception{
        Long result = userMapper.userExits(user);
        if(result > 0L){
            throw new Exception("该用户名已被使用");
        }
        User bo = (User) SecurityUtils.getSubject().getPrincipal();
        //生成随机盐
        String salt = MD5util.createSalt();
        user.setSalt(salt);
        user.setUserPwd(MD5util.formPassToDBPass(user.getUserPwd(),salt));
        user.setUpdator(bo.getUserId());
        Long count = userMapper.addUser(user);
        return count > 0 ? true : false;
    }
}
