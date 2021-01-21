package cn.zlin.demo.dao;

import cn.zlin.demo.domain.Mean;
import cn.zlin.demo.domain.Role;
import cn.zlin.demo.domain.User;
import cn.zlin.demo.domain.UserPermiss;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

public interface UserMapper {

    /**
     * 获取密码
     * @param username
     * @return
     */
    User getPassword(String username);

    /**
     * 获取用户类型
     * @param usernameuserExits
     * @return
     */
    String getRole(String username);

    /**
     * 获取用户权限
     * @param userName
     * @return
     */
    List<Role> getAllRole(String userName);

    /**
     * 用户列表数据
     * @param rowBounds
     * @param bo
     * @return
     */
    List<User> ListUser(RowBounds rowBounds, User bo);

    /**
     * 用户列表数据条目
     * @param rowBounds
     * @param bo
     * @return
     */
    Long countList(RowBounds rowBounds, User bo);

    /**
     * 用户权限列表
     * @param user
     * @return
     */
    List<UserPermiss> ListPermiss(User user);

    /**
     * 删除用户
     * @param user
     * @return
     */
    Long deleteUser(User user);

    /**
     * 先删除该用户的所有权限
     * @param userId
     * @return
     */
    Long delUserRole(@Param("userId") Long userId,@Param("updator") Long updator);

    /**
     * 插入用户权限
     * @param list
     * @return
     */
    Long insertRole(List<Role> list);

    /**
     * 重置密码
     * @param user
     * @return
     */
    Long resetPwd(User user);

    /**
     * 修改用户角色
     * @param user
     * @return
     */
    Long alterUserRole(User user);

    /**
     * 新增用户
     * @param user
     * @return
     */
    Long addUser(User user);

    /**
     *用户名是否存在
     * @param user
     * @return
     */
    Long userExits(User user);

    /**
     * 短信登录获取用户信息
     * @return
     */
    User getUserInfoByPhone(String memPhone);
}
