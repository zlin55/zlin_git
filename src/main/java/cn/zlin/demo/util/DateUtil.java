package cn.zlin.demo.util;

import java.util.Calendar;

/**
 * 项目名称：
 * 类名称：DateUtil
 * 类描述：
 * 创建人：linTH
 * 创建时间：
 * 修改人：
 * 修改时间：2019/10/13、9:40
 * 修改备注：
 * 版本号：1.0
 **/
public class DateUtil {

    public static int getSysYear() {
        Calendar date = Calendar.getInstance();
        int year = date.get(Calendar.YEAR);
        return year;
    }
}