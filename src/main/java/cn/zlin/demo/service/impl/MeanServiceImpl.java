package cn.zlin.demo.service.impl;

import cn.zlin.demo.dao.MeanMapper;
import cn.zlin.demo.dao.RoleMapper;
import cn.zlin.demo.dao.UserMapper;
import cn.zlin.demo.domain.EUDGPagination;
import cn.zlin.demo.domain.Mean;
import cn.zlin.demo.service.DictionaryService;
import cn.zlin.demo.service.MeanService;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class MeanServiceImpl implements MeanService {
    @Autowired
    private MeanMapper meanMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private DictionaryService dictionaryService;

    @Override
    public EUDGPagination meanList(Mean bo) {
        RowBounds rowBounds = new RowBounds((bo.getPage() - 1) * bo.getLimit(), bo.getLimit());
        List<Mean> list = meanMapper.selectMeanList(rowBounds,bo);
        for(Mean mean :list){
            //字典转义
            mean.setIsMeanCN(dictionaryService.CodeTsfName("A001",mean.getIsMean()));
            //翻译权限名称
            mean.setRoleName(roleMapper.selectRoleName(mean.getRoleId()));
        }
        Long count = meanMapper.countList(rowBounds,bo);
        EUDGPagination pagination = new EUDGPagination(0L,"",count,list);
        return pagination;
    }

    @Override
    public Boolean updateMean(Mean mean){
        //更新菜单表
        Long count = meanMapper.updateMean(mean);
        return count > 0 ? true : false;
    }

    @Override
    public Boolean deleteMean(Mean mean) {
        Long count = meanMapper.deleteMean(mean);
        return count > 0 ? true : false;
    }

    @Override
    public Boolean addMean(Mean bo) throws Exception{
        Long result = meanMapper.isExist(bo);
        if(result > 0L){
            throw new Exception("您已经配置过该菜单。");
        }
        Long count = meanMapper.addMean(bo);
        return count > 0 ? true :false;
    }

    @Override
    public Boolean alterRole(Mean mean) {
        Long count = meanMapper.alterRole(mean);
        return count > 0 ? true : false;
    }
}
