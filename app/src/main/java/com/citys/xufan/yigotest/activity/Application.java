package com.citys.xufan.yigotest.activity;

import com.citys.xufan.yigotest.http.OkHttpMgr;
import com.citys.xufan.yigotest.utils.SpUtil;

public class Application extends android.app.Application {
    @Override
    public void onCreate() {
        super.onCreate();
        SpUtil.getInstance().init(this.getApplicationContext());
        OkHttpMgr.getInstance().init("http://49.234.152.112:8080/");
    }
}
