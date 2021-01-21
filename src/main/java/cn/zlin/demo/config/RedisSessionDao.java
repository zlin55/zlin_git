package cn.zlin.demo.config;

import java.io.Serializable;
import java.util.Collection;
import java.util.concurrent.TimeUnit;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.EnterpriseCacheSessionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * redis实现session共享
 * @author Administrator
 *
 */
@Component
public class RedisSessionDao extends EnterpriseCacheSessionDAO {

    //session在redis中的过期时间:30分钟 30*60s
    private static final int expireTime = 3600;

    //redis中session名称前缀
    private static String prefix = "sessionId:";

    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;

    // 创建session，保存到数据库
    @Override
    protected Serializable doCreate(Session session) {
        Serializable sessionId = super.doCreate(session);
        System.out.println("创建session:"+session.getId());
        redisTemplate.opsForValue().set(prefix + sessionId.toString(), session);
        return sessionId;
    }

    // 获取session
    @Override
    protected Session doReadSession(Serializable sessionId) {
        System.out.println("读取session:"+sessionId);
        // 先从缓存中获取session，如果没有再去数据库中获取
        Session session = super.doReadSession(sessionId);
        if(session == null){
            session = (Session) redisTemplate.opsForValue().get(prefix + sessionId.toString());
        }
        return session;
    }

    // 更新session的最后一次访问时间
    @Override
    protected void doUpdate(Session session) {
        super.doUpdate(session);
        System.out.println("更新session:"+session.getId());
        String key = prefix + session.getId().toString();
        if (!redisTemplate.hasKey(key)) {
            redisTemplate.opsForValue().set(key, session);
        }
        redisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
    }

    //删除session
    @Override
    protected void doDelete(Session session) {
        System.out.println("删除session:"+session.getId());
        super.doDelete(session);
        redisTemplate.delete(prefix + session.getId().toString());
    }

    //获取当前活动的session
    @Override
    public Collection<Session> getActiveSessions() {
        return super.getActiveSessions();
    }

}
