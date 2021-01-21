package cn.zlin.demo.service.impl;

import cn.zlin.demo.dao.DictionaryMapper;
import cn.zlin.demo.domain.Dictionary;
import cn.zlin.demo.domain.EUDGPagination;
import cn.zlin.demo.domain.User;
import cn.zlin.demo.service.DictionaryService;
import org.apache.ibatis.session.RowBounds;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class DictionaryServiceImpl implements DictionaryService {
    @Autowired
    private DictionaryMapper dictionaryMapper;

    @Override
    public List<Dictionary> getDictionaryByCode(String Code) {
        List<Dictionary> dictionaries = dictionaryMapper.getDictionaryByCode(Code);
        return dictionaries;
    }

    @Override
    public String CodeTsfName(String parCode,String dicCode) {
        String Name = dictionaryMapper.CodeTsfName(parCode,dicCode);
        return Name;
    }

    @Override
    public EUDGPagination dicList(Dictionary bo) {
        String str = "";
        RowBounds rowBounds = new RowBounds((bo.getPage() - 1) * bo.getLimit(), bo.getLimit());
        List<Dictionary> list = dictionaryMapper.dicList(rowBounds,bo);
        for(Dictionary dictionary : list){
            List<Dictionary> diclist = dictionaryMapper.contentList(dictionary);
            for(Dictionary dic : diclist){
                str += "【" + dic.getDicCode() + "：" + dic.getDicName() + "】";
            }
            dictionary.setDicContent(diclist);
            dictionary.setDicContentStr(str);
            str = "";
        }
        Long count = dictionaryMapper.countList(rowBounds,bo);
        EUDGPagination pagination = new EUDGPagination(0L,"",count,list);
        return pagination;
    }

    @Override
    public Boolean insertDict(Dictionary dictionary) throws Exception{
        User bo = (User) SecurityUtils.getSubject().getPrincipal();
        dictionary.setUpdator(bo.getUserId());
        Long result;
        dictionary.setIsDic("1");
        dictionary.setPicCode(dictionary.getParCode());
        Long count = dictionaryMapper.checkDict(dictionary);
        if(count > 0L){
            throw new Exception("该字典编码已被使用");
        }
        try {
            result = dictionaryMapper.insertDict(dictionary);
            if(result > 0L) {
                int index = 0;
                for (Dictionary dict : dictionary.getDicContent()) {
                    if (dict.getDicCode() != null) {
                        String str;
                        index++;
                        if(String.valueOf(index).length() < 3){
                            str = String.format("%03d", index);
                        }else{
                            str = String.valueOf(index);
                        }
                        dict.setIsDic("0");
                        dict.setUpdator(bo.getUserId());
                        dict.setParCode(dictionary.getParCode());
                        dict.setPicCode(dictionary.getParCode() + str);
                        dictionaryMapper.insertDict(dict);
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            throw new Exception("新增字典失败");
        }
        return result > 0 ? true : false;
    }

    @Override
    public Boolean updateDict(Dictionary dictionary) throws Exception{
        try{
            //先删除原有的字典
            deleteDict(dictionary);
            //再新增修改的字典
            insertDict(dictionary);
        }catch (Exception e){
            e.printStackTrace();
            throw new Exception("更新字典失败");
        }
        return true;
    }

    @Override
    public Boolean deleteDict(Dictionary dictionary) {
        User bo = (User) SecurityUtils.getSubject().getPrincipal();
        dictionary.setUpdator(bo.getUserId());
        Long count = dictionaryMapper.deleteDict(dictionary);
        return count > 0 ? true : false;
    }
}
