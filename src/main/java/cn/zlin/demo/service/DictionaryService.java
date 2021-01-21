package cn.zlin.demo.service;

import cn.zlin.demo.domain.Dictionary;
import cn.zlin.demo.domain.EUDGPagination;

import java.util.List;

public interface DictionaryService {
    /**
     * 通过字典编码获取字典内容
     * @param Code
     * @return
     */
    List<Dictionary> getDictionaryByCode(String Code);
    /**
     * 字典编码转化为字典名称
     * @return
     */
    String CodeTsfName(String parCode,String dicCode);

    /**
     * 字典列表
     * @param bo
     * @return
     */
    EUDGPagination dicList(Dictionary bo);

    /**
     * 新增字典
     * @param dictionary
     * @return
     */
    Boolean insertDict(Dictionary dictionary) throws Exception;

    /**
     * 更新字典
     * @param dictionary
     * @return
     */
    Boolean updateDict(Dictionary dictionary) throws Exception;

    /**
     * 删除字典
     * @param dictionary
     * @return
     */
    Boolean deleteDict(Dictionary dictionary);

}
