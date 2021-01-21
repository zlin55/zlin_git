package cn.zlin.demo.controller;

import cn.zlin.demo.config.Log;
import cn.zlin.demo.domain.Dictionary;
import cn.zlin.demo.domain.EUDGPagination;
import cn.zlin.demo.domain.ResultObj;
import cn.zlin.demo.domain.Source;
import cn.zlin.demo.service.DictionaryService;
import cn.zlin.demo.service.FileService;
import cn.zlin.demo.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class FileManageController {
    @Autowired
    private DictionaryService dictionaryService;
    @Autowired
    private FileService fileService;

    @RequestMapping("/manage")
    public String index(ModelMap map){
        List<Dictionary> dictionary1 = dictionaryService.getDictionaryByCode(Constant.FILETYPE);
        List<Dictionary> dictionary2 = dictionaryService.getDictionaryByCode(Constant.FILEFOLD);
        map.addAttribute("dictionary1",dictionary1);
        map.addAttribute("dictionary2",dictionary2);
        return "file_manager";
    }

    /**
     * 上传附件
     * @param map
     * @return
     */
    @RequestMapping("/addfile")
    public String addFile(ModelMap map){
        List<Dictionary> dictionary1 = dictionaryService.getDictionaryByCode(Constant.FILETYPE);
        List<Dictionary> dictionary2 = dictionaryService.getDictionaryByCode(Constant.FILEFOLD);
        map.addAttribute("dictionary1",dictionary1);
        map.addAttribute("dictionary2",dictionary2);
        return "fileload";
    }

    /**
     * 附件列表
     * @param source
     * @return
     */
    @ResponseBody
    @RequestMapping("fileList")
    public Object fileList(Source source){
        EUDGPagination pagination = fileService.fileList(source);
        return pagination;
    }

    /**
     * 删除附件
     * @param source
     * @return
     */
    @ResponseBody
    @RequestMapping("delFile")
    @Log(value="删除附件")
    public Object delFile(Source source){
        ResultObj resultObj = new ResultObj(false,null,source);
        try{
            Boolean bool = fileService.delFile(source);
            resultObj.setSuccess(bool);
        }catch (Exception e){
            e.printStackTrace();
            resultObj.setSuccess(false);
            return resultObj;
        }
        return resultObj;
    }

    //视频播放页面
    @RequestMapping("/vadio")
    public String vadio(ModelMap map, String filePath){
        String suffix  = filePath.substring(0,filePath.lastIndexOf("."));
        map.addAttribute("filePath",suffix);
        return "vadio";
    }

    /**
     * 重命名
     * @param source
     * @return
     */
    @ResponseBody
    @RequestMapping("/rename")
    public Object rename(Source source){
        ResultObj resultObj = new ResultObj(false,null,source);
        Boolean bool = fileService.rename(source);
        resultObj.setSuccess(bool);
        return  resultObj;
    }

    /**
     * 保存到个人文件夹
     * @param source
     * @return
     */
    @ResponseBody
    @RequestMapping("/saveMe")
    public Object saveMe(Source source){
        ResultObj resultObj = new ResultObj(false,null,source);
        try{
            Boolean bool = fileService.saveMe(source);
            resultObj.setSuccess(bool);
        }catch (Exception e){
            resultObj.setSuccess(false);
            resultObj.setMessage(e.getMessage());
            return resultObj;
        }
        return  resultObj;
    }

}
