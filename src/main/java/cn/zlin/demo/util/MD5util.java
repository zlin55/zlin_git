package cn.zlin.demo.util;

import org.apache.commons.codec.digest.DigestUtils;

import java.util.Random;

public class MD5util {

    public static String  md5(String sac){
        //commons-lang3  version:3.6
        String md5 =  DigestUtils.md5Hex(sac);
        System.out.println(md5);
        return md5;
    }

    /**
     * 第二次加密md5的值+随机盐再进行md5加密
     * @param formPass
     * @param salt
     * @return
     */
    public static String formPassToDBPass(String formPass, String salt){
        String str  = ""+salt.charAt(0)+salt.charAt(2) + formPass +salt.charAt(5) + salt.charAt(4);
        System.out.println(str);
        return md5(str);
    }

    /**
     * 整体加密
     *
     */
    public static String inputPassToDbPass(String pass ,String salt){
        //第二次加密
        return formPassToDBPass(pass,salt);
    }

    /**
     * 生成随机盐
     * @return
     */
    public static String createSalt(){
        String s = "1234567890abcdefghijklmnopqrstuvwxyz";
        String str = "";
        char[] c = s.toCharArray();
        Random random = new Random();
        for( int i = 0; i < 6; i ++) {
            str = str + c[random.nextInt(c.length)];
        }
        return str;
    }
}
