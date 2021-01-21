package cn.zlin.demo.service;

import cn.zlin.demo.domain.EUDGPagination;
import cn.zlin.demo.domain.SysLog;

public interface SysLogService {

    /**
     * 插入日志
     * @param entity
     * @return
     */
    Long insertLog(SysLog entity);

    /**
     * 日志列表数据
     * @param sysLog
     * @return
     */
    EUDGPagination ListLog(SysLog sysLog);

    /**
     * 清除日志（一周前）
     * @return
     */
    Long clearLog();
}
