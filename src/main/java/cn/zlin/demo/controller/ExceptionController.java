package cn.zlin.demo.controller;

import cn.zlin.demo.domain.ResultObj;
import org.apache.shiro.authc.AuthenticationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.security.auth.login.AccountException;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ExceptionController {

    // 捕捉 CustomRealm 抛出的异常
    @ExceptionHandler(AuthenticationException.class)
    public Object handleShiroException(Exception ex) {
        ResultObj resultObj = new ResultObj(false,ex.getMessage());
        return resultObj;
    }
}

