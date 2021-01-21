package cn.zlin.demo.dao;

import cn.zlin.demo.domain.Source;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

public interface FileMapper {

    /**
     * 上传附件信息保存至附件表
     * @param source
     * @return
     */
    Long insertFile(Source source);

    /**
     * 附件列表
     * @param rowBounds
     * @param bo
     * @return
     */
    List<Source> fileList(RowBounds rowBounds, Source bo);

    /**
     * 附件列表数据数量
     * @param rowBounds
     * @param bo
     * @return
     */
    Long countList(RowBounds rowBounds, Source bo);

    /**
     * 删除附件
     * @param source
     * @return
     */
    Long delFile(Source source);

    /**
     * 重命名
     * @param source
     * @return
     */
    Long rename(Source source);
}
