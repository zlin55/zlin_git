package cn.zlin.demo.service;

import cn.zlin.demo.domain.EUDGPagination;
import cn.zlin.demo.domain.Source;

public interface FileService {
    /**
     * 文件列表
     * @param source
     * @return
     */
    EUDGPagination fileList(Source source);

    /**
     * 删除附件
     * @param source
     * @return
     */
    Boolean delFile(Source source) throws Exception;

    /**
     * 重命名
     * @param source
     * @return
     */
    Boolean rename(Source source);

    /**
     * 保存到个人文件夹
     * @param source
     * @return
     */
    Boolean saveMe(Source source) throws Exception;
}
