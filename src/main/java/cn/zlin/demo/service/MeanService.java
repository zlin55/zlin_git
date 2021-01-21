package cn.zlin.demo.service;

import cn.zlin.demo.domain.EUDGPagination;
import cn.zlin.demo.domain.Mean;
import cn.zlin.demo.domain.Role;

public interface MeanService {
    /**
     * 菜单数据接口
     * @param mean
     * @return
     */
    public EUDGPagination meanList(Mean mean);

    /**
     * 更新菜单
     * @param mean
     * @return
     */
    Boolean updateMean(Mean mean);

    /**
     * 删除菜单
     * @param mean
     * @return
     */
    Boolean deleteMean(Mean mean);

    /**
     * 添加菜单
     * @param bo
     * @return
     */
    Boolean addMean(Mean bo) throws Exception;

    /**
     * 更改菜单权限
     * @param mean
     * @return
     */
    Boolean alterRole(Mean mean);
}
