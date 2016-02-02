package com.smart.om.util;

/**
 * Created by hxt on 2016/1/22.
 */
public class ReportUtil {
    /**
     * 阿拉伯数字月份转中文
     */
    public static String ArabicNumberToChinese(int i) {
        String month = null;
        switch (i) {
            case 1:
                month = "一";
                break;
            case 2:
                month = "二";
                break;
            case 3:
                month = "三";
                break;
            case 4:
                month = "四";
                break;
            case 5:
                month = "五";
                break;
            case 6:
                month = "六";
                break;
            case 7:
                month = "七";
                break;
            case 8:
                month = "八";
                break;
            case 9:
                month = "九";
                break;
            case 10:
                month = "十";
                break;
            case 11:
                month = "十一";
                break;
            case 12:
                month = "十二";
                break;
            default:
                month = "";
        }
        return month;
    }
}
