package cn.zlin.demo.controller;

import cn.zlin.demo.util.QRCodeUtil;
import com.google.zxing.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 二维码调用前端控制器
 */
@RequestMapping("/Qrcode")
@RestController
public class QrcodeController {
    /**
     * 生成二维码
     */
    @GetMapping("/index")
    public void productcode() {
        String fileName = QRCodeUtil.zxingCodeCreate("120.77.159.73:8088/tologin", "C:/Users/zlin/Tools/tempFile/",500,"C:/Users/zlin/Tools/tempFile/1558057148640irmt2qcp.jpg");
    }


    /**
     * 解析二维码
     */
    @GetMapping("/test")
    public void analysiscode() {
        Result result = QRCodeUtil.zxingCodeAnalyze("C:/Users/zlin/Tools/tempFile/374.jpg");
        System.err.println("二维码解析内容："+result.toString());
    }

}
