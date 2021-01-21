package cn.zlin.demo.service;

import cn.zlin.demo.domain.Role;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

public interface RoleService {
    /**
     * 获取权限列表
     * @return
     */
    public List<Role> roleList(RowBounds rowBounds,Role bo);

    /**
     * 权限列表数目
     * @param rowBounds
     * @param bo
     * @return
     */
    Long countList(RowBounds rowBounds, Role bo);

    /**
     * 更新权限
     * @param role
     * @return
     */
    Boolean updateRole(Role role);

    /**
     * 删除权限
     * @param role
     * @return
     */
    String deleteRole(Role role);

    /**
     * 新增权限
     * @param bo
     * @return
     */
    Boolean addRole(Role bo);
}
