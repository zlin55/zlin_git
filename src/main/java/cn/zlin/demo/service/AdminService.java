package cn.zlin.demo.service;

import cn.zlin.demo.domain.EUDGPagination;
import cn.zlin.demo.domain.User;

import java.util.List;

public interface AdminService {
    /**
     * 用户列表数据接口
     * @return
     */
    EUDGPagination ListUser(User user);

    /**
     * 删除用户
     * @param user
     * @return
     */
    Boolean deleteUser(User user);

    /**
     * 授权处理
     * @param roleIds
     * @return
     */
    Boolean grantRole(String roleIds,Long userId)  throws Exception;

    /**
     * 重置密码
     * @param user
     * @return
     */
    Boolean resetPwd(User user);

    /**
     * 修改用户角色
     * @param user
     * @return
     */
    Boolean alterUserRole(User user);

    /**
     * 新增用户
     * @param user
     * @return
     */
    Boolean addUser(User user) throws Exception;
}
