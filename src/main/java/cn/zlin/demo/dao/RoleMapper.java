package cn.zlin.demo.dao;

import cn.zlin.demo.domain.Role;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

public interface RoleMapper {
    /**
     * 获取权限列表
     * @return
     */
    List<Role> roleList(RowBounds rowBounds , Role bo);


    /**
     * 权限列表数目
     * @param rowBounds
     * @param bo
     */
    Long countList(RowBounds rowBounds, Role bo);

    /**
     * 更新权限
     * @param role
     * @return
     */
    Long updateRole(Role role);

    /**
     * 删除权限
     * @param role
     * @return
     */
    Long deleteRole(Role role);

    /**
     * 新增权限
     * @param bo
     * @return
     */
    Long addRole(Role bo);

    /**
     * 权限ID查找名称
     * @param roleId
     * @return
     */
    String selectRoleName(Long roleId);
}
