package cn.zlin.demo.service.impl;

import cn.zlin.demo.dao.MeanMapper;
import cn.zlin.demo.dao.RoleMapper;
import cn.zlin.demo.domain.Mean;
import cn.zlin.demo.domain.Role;
import cn.zlin.demo.service.RoleService;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private MeanMapper meanMapper;

    @Override
    public List<Role> roleList(RowBounds rowBounds,Role bo) {
        List<Role> roles = roleMapper.roleList(rowBounds,bo);
        return roles;
    }

    @Override
    public Long countList(RowBounds rowBounds, Role bo) {
        Long count = roleMapper.countList(rowBounds,bo);
        return count;
    }

    @Override
    public Boolean updateRole(Role role) {
        Long count = roleMapper.updateRole(role);
        return count > 0 ? true : false;
    }

    @Override
    public String deleteRole(Role role) {
        String resultMsg = "";
        List<Mean> mean = meanMapper.isUsing(role);
        if(!mean.isEmpty()){
            for(Mean bo : mean){
                resultMsg = "'" + bo.getMeanName() + "'";
            }
            return "该权限已经分配给菜单:" + resultMsg +"，请先更改菜单权限";
        }
        Long count = roleMapper.deleteRole(role);
        return count > 0 ? resultMsg : "删除失败";
    }

    @Override
    public Boolean addRole(Role bo) {
        Long count = roleMapper.addRole(bo);
        return count > 0 ? true : false;
    }

}
