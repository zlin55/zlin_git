package cn.zlin.demo.service.impl;

import cn.zlin.demo.dao.FileMapper;
import cn.zlin.demo.domain.EUDGPagination;
import cn.zlin.demo.domain.Source;
import cn.zlin.demo.domain.User;
import cn.zlin.demo.service.FileService;
import cn.zlin.demo.util.Constant;
import cn.zlin.demo.util.FileUploadController;
import org.apache.ibatis.session.RowBounds;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.*;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class FileServiceImpl implements FileService {
    @Autowired
    private FileMapper fileMapper;

    @Override
    public EUDGPagination fileList(Source bo) {
        if("1".equals(bo.getFileFlod())){
            User user = (User) SecurityUtils.getSubject().getPrincipal();
            bo.setUpdator(user.getUserName());
        }
        RowBounds rowBounds = new RowBounds((bo.getPage() - 1) * bo.getLimit(), bo.getLimit());
        List<Source> list = fileMapper.fileList(rowBounds,bo);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for(Source source : list){
            source.setCreateTimeStr(sdf.format(source.getCreateTime()));
        }
        Long count = fileMapper.countList(rowBounds,bo);
        EUDGPagination pagination = new EUDGPagination(0L,"",count,list);
        return pagination;
    }

    @Override
    public Boolean delFile(Source source) throws Exception{
        User bo = (User) SecurityUtils.getSubject().getPrincipal();
        source.setUpdator(bo.getUserName());
        try {
            //查询附件的信息
            source.setPage(1);
            source.setLimit(10);
            RowBounds rowBounds = new RowBounds((source.getPage() - 1) * source.getLimit(), source.getLimit());
            List<Source> list = fileMapper.fileList(rowBounds, source);
            String filePath = Constant.filePath;
            if("1".equals(source.getFileFlod())){
                filePath = filePath + bo.getUserName() + "/";
            }
            File file = new File(filePath + list.get(0).getFileName());
            //删除数据库记录
            fileMapper.delFile(source);
            //删除磁盘文件
            file.delete();
        }catch(Exception e){
            e.printStackTrace();
            throw new Exception("删除附件失败");
        }
        return true;
    }

    @Override
    public Boolean rename(Source source) {
        User bo = (User) SecurityUtils.getSubject().getPrincipal();
        source.setUpdator(bo.getUserName());
        Long count = fileMapper.rename(source);
        return count > 0 ? true : false;
    }

    @Override
    public Boolean saveMe(Source source) throws Exception{
        try {
            //查询附件的信息
            source.setPage(1);
            source.setLimit(10);
            RowBounds rowBounds = new RowBounds((source.getPage() - 1) * source.getLimit(), source.getLimit());
            List<Source> list = fileMapper.fileList(rowBounds, source);
            //插入附件表
            User bo = (User) SecurityUtils.getSubject().getPrincipal();
            source.setRealName(list.get(0).getRealName());
            String suffix = list.get(0).getFileName().substring(list.get(0).getFileName().lastIndexOf(".") + 1);
            String fileName = String.valueOf(System.currentTimeMillis()) + FileUploadController.CreateRandomCode(8) + "." + suffix;
            source.setFileName(fileName);
            source.setFileType(list.get(0).getFileType());
            source.setFileFlod("1");
            source.setUpdator(bo.getUserName());
            fileMapper.insertFile(source);
            //在个人文件夹新生成一个文件
            File old = new File(Constant.filePath + list.get(0).getFileName());
            File file = new File(Constant.filePath + bo.getUserName() + "\\" + fileName);
            File path = new File(Constant.filePath + bo.getUserName() + "\\");
            if (!path.exists() && !path.isDirectory()) {
                path.mkdirs();
            }
            Files.copy(old.toPath(),file.toPath());
        }catch (Exception e){
            e.printStackTrace();
            throw new Exception("保存到个人文件夹时失败");
        }
        return true;
    }
}
