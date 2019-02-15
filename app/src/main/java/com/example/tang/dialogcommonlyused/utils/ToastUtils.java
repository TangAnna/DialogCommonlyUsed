package com.example.tang.dialogcommonlyused.utils;

import android.widget.Toast;

import com.example.tang.dialogcommonlyused.BuildConfig;
import com.example.tang.dialogcommonlyused.DialogApplication;


/**
 * @author TangAnna
 * @description: Toast工具类
 * @date :${DATA} 19:44
 */
public class ToastUtils {
    private static ToastUtils mToastUtils;
    private static Toast mToast;
    private static boolean debug = BuildConfig.DEBUG;

    public static ToastUtils getInstance() {
        if (mToastUtils == null) {
            mToastUtils = new ToastUtils();
        }
        return mToastUtils;
    }

    /**
     * 普通的提示
     *
     * @param msg
     */
    public static void showToast(String msg) {
        if (mToast == null) {
            mToast = Toast.makeText(DialogApplication.getContext(), msg, Toast.LENGTH_SHORT);
        }
        mToast.setText(msg);
        mToast.show();
    }

    /**
     * 开发过程中用到的toast
     *
     * @param msg
     */
    public static void showDebugToast(Object msg) {
        if (debug) {
            showToast(msg.toString());
        }
    }
}
