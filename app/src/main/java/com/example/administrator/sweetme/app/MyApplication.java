package com.example.administrator.sweetme.app;

import android.app.Application;

import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMOptions;
import com.socks.library.KLog;

import cn.bmob.v3.Bmob;

/**
 * Created by Administrator on 2017/5/25.
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        KLog.init(true, "--------->");
        Bmob.initialize(this, "8f7236e809606cfcd80bd26f0881c372");

        EMOptions options = new EMOptions();
        // 默认添加好友时，是不需要验证的，改成需要验证
        options.setAcceptInvitationAlways(false);

        //初始化
        EMClient.getInstance().init(getApplicationContext(), options);
        //在做打包混淆时，关闭debug模式，避免消耗不必要的资源
        EMClient.getInstance().setDebugMode(false);


    }
}
