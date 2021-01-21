package cn.zlin.demo.service.impl;

import cn.zlin.demo.dao.MeanMapper;
import cn.zlin.demo.domain.Mean;
import cn.zlin.demo.service.DictionaryService;
import cn.zlin.demo.service.ShiroService;
import org.apache.ibatis.session.RowBounds;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.mgt.DefaultFilterChainManager;
import org.apache.shiro.web.filter.mgt.PathMatchingFilterChainResolver;
import org.apache.shiro.web.servlet.AbstractShiroFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class ShiroServiceImpl implements ShiroService {
    @Autowired
    private MeanMapper meanMapper;

    /**
     * 初始化权限
     */
    @Override
    public Map<String, String> loadFilterChainDefinitions() {
        // 设置拦截器
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
        List<Mean> meanList = meanMapper.selectMeanList(new RowBounds(),new Mean());
        //不能改变顺序，否则会导致权限失效
        for(Mean mean : meanList){
            if(mean.getRoleId() != null && !"".equals(mean.getRoleId())){
                filterChainDefinitionMap.put(mean.getMeanUrl(),"perms[" + mean.getRoleId() + "]");
            }
            //System.out.println(mean.getMeanUrl() +"---- roles[" + mean.getRoleId() + "]");
        }
        //游客，开发权限
        filterChainDefinitionMap.put("/guest/**", "anon");
        filterChainDefinitionMap.put("/code", "anon");
        filterChainDefinitionMap.put("/fitness/code", "anon");
        //用户，需要角色权限 “user”
        filterChainDefinitionMap.put("/user/**", "roles[user]");
        //管理员，需要角色权限 “admin”
        filterChainDefinitionMap.put("/admin/**", "roles[admin]");
        //filterChainDefinitionMap.put("/admin/**", "anon");
        //开放登陆接口
        filterChainDefinitionMap.put("/login", "anon");
        filterChainDefinitionMap.put("/drawImage", "anon");
        filterChainDefinitionMap.put("/static/**", "anon");
        //其余接口一律拦截
        //主要这行代码必须放在所有权限设置的最后，不然会导致所有 url 都被拦截
        filterChainDefinitionMap.put("/**", "authc");
        return filterChainDefinitionMap;
    }

    /**
     * 在对角色进行增删改操作时，需要调用此方法进行动态刷新
     * @param shiroFilterFactoryBean
     */
    @Override
    public void updatePermission(ShiroFilterFactoryBean shiroFilterFactoryBean) {
        synchronized (this) {
            AbstractShiroFilter shiroFilter;
            try {
                shiroFilter = (AbstractShiroFilter) shiroFilterFactoryBean.getObject();
            } catch (Exception e) {
                throw new RuntimeException("get ShiroFilter from shiroFilterFactoryBean error!");
            }
            System.out.println("————更新权限————");
            PathMatchingFilterChainResolver filterChainResolver = (PathMatchingFilterChainResolver) shiroFilter.getFilterChainResolver();
            DefaultFilterChainManager manager = (DefaultFilterChainManager) filterChainResolver.getFilterChainManager();
            // 清空老的权限控制
            manager.getFilterChains().clear();
            shiroFilterFactoryBean.getFilterChainDefinitionMap().clear();
            shiroFilterFactoryBean.setFilterChainDefinitionMap(loadFilterChainDefinitions());
            // 重新构建生成
            Map<String, String> chains = shiroFilterFactoryBean.getFilterChainDefinitionMap();
            for (Map.Entry<String, String> entry : chains.entrySet()) {
                String url = entry.getKey();
                String chainDefinition = entry.getValue().trim().replace(" ", "");
                manager.createChain(url, chainDefinition);
            }

        }
    }
}
