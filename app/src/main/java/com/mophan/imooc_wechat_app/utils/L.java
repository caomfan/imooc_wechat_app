package com.mophan.imooc_wechat_app.utils;

import android.util.Log;

import com.mophan.imooc_wechat_app.BuildConfig;

/**
 * Create by CMF on 2019/9/1.
 */
public class L {
    private static final String TAG="hyman";
    private static boolean sDebug= BuildConfig.DEBUG;

    public static void d(String msg, Object... args){
        if(!sDebug){
            return;
        }
        Log.d(TAG,String.format(msg,args));
    }
}
