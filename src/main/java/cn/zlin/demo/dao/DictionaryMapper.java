package cn.zlin.demo.dao;

import cn.zlin.demo.domain.Dictionary;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

public interface DictionaryMapper {

    /**
     * 字典编码转化为名称
     * @param parCode
     * @return
     */
    String CodeTsfName(@Param("parCode") String parCode,@Param("dicCode") String dicCode);

    /**
     * 通过字典编码获取字典内容
     * @param code
     * @return
     */
    List<Dictionary> getDictionaryByCode(String code);

    /**
     * 字典列表
     * @param rowBounds
     * @param bo
     * @return
     */
    List<Dictionary> dicList(RowBounds rowBounds, Dictionary bo);

    /**
     * 字典列表数目
     * @param rowBounds
     * @param bo
     * @return
     */
    Long countList(RowBounds rowBounds, Dictionary bo);

    /**
     * 每个字典中的选项列表
     * @param dictionary
     * @return
     */
    List<Dictionary> contentList(Dictionary dictionary);

    /**
     * 插入字典
     * @param dictionary
     */
    Long insertDict(Dictionary dictionary);

    /**
     * 检查字典编码是否已经被使用
     * @param dictionary
     * @return
     */
    Long checkDict(Dictionary dictionary);

    /**
     * 删除字典
     * @param dictionary
     * @return
     */
    Long deleteDict(Dictionary dictionary);
}
