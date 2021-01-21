package cn.zlin.demo.dao;

import cn.zlin.demo.domain.SysLog;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

public interface SysLogMapper {
    /**
     * 插入日志
     * @param entity
     * @return
     */
    Long insert(SysLog entity);

    /**
     * 日志列表数据
     * @param rowBounds
     * @param bo
     * @return
     */
    List<SysLog> ListLog(RowBounds rowBounds, SysLog bo);

    /**
     * 日志列表数据总数
     * @param rowBounds
     * @param bo
     * @return
     */
    Long countList(RowBounds rowBounds, SysLog bo);

    /**
     * 清空日志（一周前）
     * @return
     */
    Long clearLog();
}
