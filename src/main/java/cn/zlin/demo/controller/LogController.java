package cn.zlin.demo.controller;

import cn.zlin.demo.domain.EUDGPagination;
import cn.zlin.demo.domain.ResultObj;
import cn.zlin.demo.domain.SysLog;
import cn.zlin.demo.service.SysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/admin")
public class LogController {
    @Autowired
    SysLogService sysLogService;

    /**
     * 日志页面
     * @return
     */
    @RequestMapping("/log")
    public String log(){
        return "log";
    }

    /**
     * 日志列表
     * @param sysLog
     * @return
     */
    @ResponseBody
    @RequestMapping("/logList")
    public Object logList(SysLog sysLog){
        EUDGPagination pagination = sysLogService.ListLog(sysLog);
        return pagination;
    }

    @ResponseBody
    @RequestMapping("/clearLog")
    public Object clearLog(HttpServletRequest request){
        ResultObj resultObj = new ResultObj(false,null,null);
        Long count = sysLogService.clearLog();
        resultObj.setSuccess(true);
        resultObj.setData(count);
        return resultObj;
    }
}
