package cn.zlin.demo.service.impl;

import cn.zlin.demo.dao.SysLogMapper;
import cn.zlin.demo.domain.EUDGPagination;
import cn.zlin.demo.domain.SysLog;
import cn.zlin.demo.service.SysLogService;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.List;

@Service
public class SysLogServiceImpl implements SysLogService {
    @Autowired
    private SysLogMapper sysLogMapper;


    @Override
    public Long insertLog(SysLog entity) {
        return sysLogMapper.insert(entity);
    }

    @Override
    public EUDGPagination ListLog(SysLog bo) {
        RowBounds rowBounds = new RowBounds((bo.getPage() - 1) * bo.getLimit(), bo.getLimit());

        SimpleDateFormat sdt = new SimpleDateFormat("yyyy-MM-dd");
        if(bo.getCreateTimeStr() != null && !"".equals(bo.getCreateTimeStr())){
            try{
                bo.setCreateTime(sdt.parse(bo.getCreateTimeStr()));
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        List<SysLog> list = sysLogMapper.ListLog(rowBounds,bo);
        for(SysLog sysLog : list){
            sysLog.setCreateTimeStr(sdf.format(sysLog.getCreateTime()));
        }
        Long count = sysLogMapper.countList(rowBounds,bo);
        EUDGPagination pagination = new EUDGPagination(0L,"",count,list);
        return pagination;
    }

    @Override
    public Long clearLog() {
        Long count = sysLogMapper.clearLog();
        return count;
    }
}
