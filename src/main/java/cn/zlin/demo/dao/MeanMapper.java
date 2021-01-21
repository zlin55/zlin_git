package cn.zlin.demo.dao;

import cn.zlin.demo.domain.Mean;
import cn.zlin.demo.domain.Role;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

public interface MeanMapper {
    /**
     * 查询所有菜单
     * @return
     */
    List<Mean> selectMeanList(RowBounds rowBounds,Mean bo);

    /**
     *
     * @param userId
     */
    List<Mean> selectUserMean(Long userId);

    /**
     * 获取菜单数目
     * @param rowBounds
     * @return
     */
    Long countList(RowBounds rowBounds,Mean bo);

    /**
     * 更新菜单
     * @param mean
     * @return
     */
    Long updateMean(Mean mean);

    /**
     * 删除菜单
     * @param mean
     * @return
     */
    Long deleteMean(Mean mean);

    /**
     * 添加菜单
     * @param bo
     * @return
     */
    Long addMean(Mean bo);

    /**
     * 判断该权限是否已经分配给菜单
     * @param role
     * @return
     */
    List<Mean> isUsing(Role role);

    /**
     * 判断菜单是否已经存在
     * @param mean
     * @return
     */
    Long isExist(Mean mean);

    /**
     * 更改菜单权限
     * @param mean
     * @return
     */
    Long alterRole(Mean mean);
}
