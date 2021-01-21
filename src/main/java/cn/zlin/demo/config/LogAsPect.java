package cn.zlin.demo.config;

import cn.zlin.demo.domain.SysLog;
import cn.zlin.demo.domain.User;
import cn.zlin.demo.service.SysLogService;
import org.apache.shiro.SecurityUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * 切面日志配置
 */
@Aspect
@Component
public class LogAsPect {
    private final static Logger log = LoggerFactory.getLogger(LogAsPect.class);

    @Autowired
    private SysLogService sysLogService;

    //表示匹配带有自定义注解的方法
    @Pointcut("@annotation(cn.zlin.demo.config.Log)")
    public void pointcut() {}

    @Around("pointcut()")
    public Object around(ProceedingJoinPoint point) {
        Object result =null;
        long beginTime = System.currentTimeMillis();

        try {
            log.info("我在目标方法之前执行！");
            result = point.proceed();
            long endTime = System.currentTimeMillis();
            insertLog(point,endTime-beginTime);
        } catch (Throwable e) {
            log.info("记录操作日志失败。");
        }
        return result;
    }

    private void insertLog(ProceedingJoinPoint point ,long time) {
        MethodSignature signature = (MethodSignature)point.getSignature();
        Method method = signature.getMethod();
        SysLog sys_log = new SysLog();

        Log userAction = method.getAnnotation(Log.class);
        if (userAction != null) {
            // 注解上的描述
            sys_log.setUserAction(userAction.value());
        }

        // 请求的类名
        String className = point.getTarget().getClass().getName();
        // 请求的方法名
        String methodName = signature.getName();
        // 请求的方法参数值
        String args = Arrays.toString(point.getArgs());
        //从session中获取当前登陆人id
        User bo = (User) SecurityUtils.getSubject().getPrincipal();
        sys_log.setUserId(bo.getUserId());
        sys_log.setUserName(bo.getUserName());
        sys_log.setClassName(className + "." + methodName);
        sys_log.setArgs(args);

        log.info("当前登陆人：{},类名:{},方法名:{},参数：{}",bo.getUserId(), className, methodName, args);

        sysLogService.insertLog(sys_log);
    }

}
