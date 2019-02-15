package com.example.tang.dialogcommonlyused.utils;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;

import com.example.tang.dialogcommonlyused.DialogApplication;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author TangAnna
 * @description:
 * @date :${DATA} 15:33
 */
public class CodeUtils {

    /**
     * 格式化时间  上传到服务器  string转成long  以秒为单位
     *
     * @param strDate
     * @return
     */
    public static long formatDate(String strDate) {
        SimpleDateFormat sdf = new SimpleDateFormat("yy年MM月dd日");
        try {
            Date parse = sdf.parse(strDate);
            return parse.getTime() / 1000;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 获取Resources对象
     */
    public static Resources getResources() {
        return DialogApplication.getContext().getResources();
    }

    /**
     * 获取资源文件中的String
     *
     * @param id
     * @return
     */
    public static String getIdString(int id) {
        return getResources().getString(id);
    }

    /**
     * 获取资源文件中的Drawable文件
     *
     * @param id
     * @return
     */
    public static Drawable getIdDrawable(int id) {
        return getResources().getDrawable(id);
    }

    /**
     * 获取资源文件中的颜色值
     *
     * @param id
     * @return
     */
    public static int getIdColor(int id) {
        return getResources().getColor(id);
    }

}
