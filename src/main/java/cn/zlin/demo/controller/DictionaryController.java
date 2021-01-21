package cn.zlin.demo.controller;

import cn.zlin.demo.config.Log;
import cn.zlin.demo.domain.Dictionary;
import cn.zlin.demo.domain.EUDGPagination;
import cn.zlin.demo.domain.ResultObj;
import cn.zlin.demo.service.DictionaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/admin")
public class DictionaryController {
    @Autowired
    private DictionaryService dictionaryService;

    /**
     * 字典列表页面
     * @return
     */
    @RequestMapping("/dictionary")
    public String dictionart(){
        return "dictionary";
    }

    /**
     * 字典列表
     */
    @ResponseBody
    @RequestMapping("/dicList")
    public Object dicList(Dictionary bo){
        EUDGPagination pagination = dictionaryService.dicList(bo);
        return pagination;
    }

    /**
     * 字典列表页面
     * @return
     */
    @RequestMapping("/addDict")
    public String addDict(ModelMap map, Dictionary dictionary){
        if(dictionary.getId() != null){
            dictionary.setPage(1);
            dictionary.setLimit(99999);
            EUDGPagination pagination = dictionaryService.dicList(dictionary);
            map.addAttribute("dict",pagination.getData().get(0));
        }
        return "addDict";
    }

    /**
     * 新增字典
     */
    @ResponseBody
    @RequestMapping("/saveDict")
    @Log("修改字典")
    public Object saveDict(Dictionary dictionary){
        ResultObj resultObj = new ResultObj(false,null,dictionary);
        try {
            if (dictionary.getId() == null || "".equals(dictionary.getId())) {
                Boolean bool = dictionaryService.insertDict(dictionary);
                resultObj.setSuccess(bool);
            } else {
                Boolean bool = dictionaryService.updateDict(dictionary);
                resultObj.setSuccess(bool);
            }
        }catch(Exception e){
            e.printStackTrace();
            resultObj.setMessage(e.getMessage());
            return resultObj;
        }
        return  resultObj;
    }

    /**
     * 删除字典
     * @param dictionary
     * @return
     */
    @ResponseBody
    @RequestMapping("/deleteDict")
    @Log("删除字典")
    public Object deleteDict(Dictionary dictionary){
        ResultObj resultObj = new ResultObj(false,null,dictionary);
        Boolean bool = dictionaryService.deleteDict(dictionary);
        resultObj.setSuccess(bool);
        return resultObj;
    }
}
