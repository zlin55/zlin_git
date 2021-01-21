package cn.zlin.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.zhenzi.sms.ZhenziSmsClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Random;

@Controller
public class CodeController {
    private static final long serialVersionUID = 1L;
    //短信平台相关参数
    private String apiUrl = "https://sms_developer.zhenzikj.com";
    private String appId = "101740";
    private String appSecret = "98c357df-e643-439d-b78a-66346c9adfd8";



    @ResponseBody
    @GetMapping("/fitness/code")
    public boolean getCode(@RequestParam("memPhone") String memPhone, HttpSession httpSession){
            try {
                JSONObject json = null;
                String code = String.valueOf(new Random().nextInt(999999));
                ZhenziSmsClient client = new ZhenziSmsClient(apiUrl, appId, appSecret);
                String result = client.send(memPhone, "您的验证码为:" + code + "，该码有效期为5分钟，该码只能使用一次!");
                json = JSONObject.parseObject(result);
                if (json.getIntValue("code")!=0){//发送短信失败
                    return  false;
                }
                //将验证码存到session中,同时存入创建时间
                //以json存放，这里使用的是阿里的fastjson
                System.out.println(code);
                json = new JSONObject();
                json.put("memPhone",memPhone);
                json.put("code",code);
                json.put("createTime",System.currentTimeMillis());
                // 将认证码存入SESSION
                httpSession.setAttribute("code",json);
                return true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        return false;
    }
}
