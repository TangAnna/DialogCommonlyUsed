package com.example.tang.dialogcommonlyused;

import android.app.Application;
import android.content.Context;

/**
 * @author TangAnna
 * @description:
 * @date :${DATA} 14:51
 */
public class DialogApplication extends Application {

    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
    }

    public static Context getContext() {
        return mContext;
    }
}
