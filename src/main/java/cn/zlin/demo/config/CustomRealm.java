package cn.zlin.demo.config;

import cn.zlin.demo.dao.UserMapper;
import cn.zlin.demo.domain.Role;
import cn.zlin.demo.domain.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class CustomRealm extends AuthorizingRealm {
    private UserMapper userMapper;

    @Autowired
    private void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

//    @Autowired
//    private RedisTemplate<String, String> redisTemplate;

//    //用户登录次数计数  redisKey 前缀
//    private String SHIRO_LOGIN_COUNT = "shiro-login-count";
//    //用户登录是否被锁定    一小时 redisKey 前缀
//    private String SHIRO_IS_LOCK = "shiro-is-lock";


    /**
     * 获取身份验证信息
     * Shiro中，最终是通过 Realm 来获取应用程序中的用户、角色及权限信息的。
     *
     * @param authenticationToken 用户身份信息 token
     * @return 返回封装了用户信息的 AuthenticationInfo 实例
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        String username = token.getUsername();
//        ValueOperations<String, String> opsForValue = redisTemplate.opsForValue();
//        opsForValue.increment(SHIRO_LOGIN_COUNT + username, 1);
//        //计数大于5时，设置用户被锁定一小时
//        if(Integer.parseInt(opsForValue.get(SHIRO_LOGIN_COUNT + username)) > 5){
//            opsForValue.set(SHIRO_IS_LOCK + username, "LOCK");
//            redisTemplate.expire(SHIRO_IS_LOCK + username, 1, TimeUnit.HOURS);
//        }
//        if ("LOCK".equals(opsForValue.get(SHIRO_IS_LOCK + username))){
//            throw new AccountException("由于密码输入错误次数过多，帐号已经禁止登录！需一小时之后自动解锁");
//        }
        // 从数据库获取对应用户名密码的用户
        User user = userMapper.getPassword(token.getUsername());
        if (null == user) {
            throw new AccountException("用户名不正确");
        } else if (!user.getUserPwd().equals(new String((char[]) token.getCredentials()))) {
//            throw new AccountException("密码不正确" + (5 - Integer.valueOf(opsForValue.get(SHIRO_LOGIN_COUNT + username))) + "次");
            throw new AccountException("密码不正确");
        }
        //清空登录计数
//        opsForValue.set(SHIRO_LOGIN_COUNT + username, "0");
        return new SimpleAuthenticationInfo(user, user.getUserPwd(), getName());
    }

    /**
     * 获取授权信息
     *
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        User user = (User) SecurityUtils.getSubject().getPrincipal();
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        //获得该用户角色
        String roleName =  userMapper.getRole(user.getUserName());
        Set<String> role = new HashSet<>();
        Set<String> permission = new HashSet<>();
        //需要将 role 封装到 Set 作为 info.setRoles() 的参数
        role.add(roleName);
        //获取该用户拥有的菜单权限
        List<Role> roleList = userMapper.getAllRole(user.getUserName());
        for(Role roles : roleList){
            permission.add(String.valueOf(roles.getRoleId()));
        }
        //设置该用户拥有的角色
        info.setRoles(role);
        info.setStringPermissions(permission);
        return info;
    }

}

