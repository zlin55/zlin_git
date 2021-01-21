package cn.zlin.demo.util;

import cn.zlin.demo.config.Log;
import cn.zlin.demo.dao.FileMapper;
import cn.zlin.demo.dao.UserMapper;
import cn.zlin.demo.domain.Source;
import cn.zlin.demo.domain.User;
import com.alibaba.fastjson.JSON;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

@RequestMapping("/file")
@RestController
public class FileUploadController {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private FileMapper fileMapper;

    @RequestMapping("/upload")
    public Object upload(HttpServletRequest request) {
        User bo = (User) SecurityUtils.getSubject().getPrincipal();
        User user = new User();
        Map<String, Object> resultMap = new HashMap<String, Object>();
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
        List<MultipartFile> files = new ArrayList<MultipartFile>();
        for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
            MultipartFile mf = entity.getValue();
            files.add(mf);
        }
        List<String> avatarUrls = new ArrayList<String>();
        for (int i = 0; i < files.size(); i++) {
            try {
                String tmpPath = Constant.filePath;
                String filename = String.valueOf(System.currentTimeMillis()) + CreateRandomCode(8) + ".jpg";
                File tempFile = new File(tmpPath, filename);
                FileOutputStream out = new FileOutputStream(tempFile);
                out.flush();
                out.write(files.get(i).getBytes());
                out.close();
                resultMap.put("sourceUrl", "/files/" + filename);
                avatarUrls.add("/files/" + filename);
                user.setAvatar(filename);
                user.setUpdator(bo.getUserId());
                user.setUserId(bo.getUserId());
                userMapper.alterUserRole(user);
            } catch (Exception e) {
                resultMap.put("success", false);
                e.printStackTrace();
            }
        }
        resultMap.put("success", true);
        resultMap.put("avatarUrls", avatarUrls);
        return JSON.toJSONString(resultMap);
    }


    @RequestMapping("/fileUpload.do")
    @Log(value="上传附件")
    public Object fileUpload3(@RequestParam(value = "files") MultipartFile[] files, HttpServletRequest request, Source source) throws Exception{
        User bo = (User) SecurityUtils.getSubject().getPrincipal();
        source.setUpdator(bo.getUserName());
        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("success", true);
        File tmpPath;
        if("1".equals(source.getFileFlod())){
            tmpPath = new File(Constant.filePath + bo.getUserName() + "\\");
            //tmpPath = new File(Constant.filePath + bo.getUserName() + "//");
        }else{
            tmpPath = new File(Constant.filePath);
        }
        if (!tmpPath.exists() && !tmpPath.isDirectory()) {
            tmpPath.mkdirs();
        }
        List<Integer> index = new ArrayList<Integer>();
        for (int i = 0; i < files.length; i++) {
            try {
                //附件真实名称
                String fileName = files[i].getOriginalFilename();
                source.setRealName(fileName);
                //附件后缀
                String suffix  = fileName.substring(fileName.lastIndexOf(".") + 1);
                if("1".equals(source.getFileType())){
                    if(!"jpg".equals(suffix) && !"png".equals(suffix) && !"gif".equals(suffix)){
                        index.add(i);
                        throw new Exception("文件类型不正确:" + fileName);
                    }
                }else if("2".equals(source.getFileType())){
                    if(!"doc".equals(suffix) && !"txt".equals(suffix) && !"pdf".equals(suffix) && !"docx".equals(suffix) && !"pptx".equals(suffix)){
                        index.add(i);
                        throw new Exception("文件类型不正确:" + fileName);
                    }
                }else if("3".equals(source.getFileType())){
                    if(!"mp3".equals(suffix) && !"wav".equals(suffix) && !"aif".equals(suffix)){
                        index.add(i);
                        throw new Exception("文件类型不正确:" + fileName);
                    }
                }else if("4".equals(source.getFileType())){
                    if(!"mp4".equals(suffix) && !"webm".equals(suffix) && !"ogv".equals(suffix)){
                        index.add(i);
                        throw new Exception("文件类型不正确:" + fileName);
                    }
                }else if("5".equals(source.getFileType())){
                    if(!"xlsx".equals(suffix) && !"xls".equals(suffix) && !"xlsb".equals(suffix)){
                        index.add(i);
                        throw new Exception("文件类型不正确:" + fileName);
                    }
                }

                //附件保存名称
                fileName = String.valueOf(System.currentTimeMillis()) + CreateRandomCode(8) + "." + suffix;
                source.setFileName(fileName);
                File tempFile = new File(tmpPath, fileName);
                FileOutputStream out = new FileOutputStream(tempFile);
                out.flush();
                out.write(files[i].getBytes());
                out.close();
                fileMapper.insertFile(source);
            } catch (Exception e) {
                resultMap.put("msg",e.getMessage());
                resultMap.put("success", false);
                e.printStackTrace();
            }
        }
        resultMap.put("index",index);
        return resultMap;
    }

    public static String CreateRandomCode(int length) {
        String s = "1234567890abcdefghijklmnopqrstuvwxyz";
        String str = "";
        char[] c = s.toCharArray();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            str = str + c[random.nextInt(c.length)];
        }
        return str;
    }

}
