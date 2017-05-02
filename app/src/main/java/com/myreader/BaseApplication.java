package com.myreader;

import android.app.Application;
import android.content.Context;
import android.support.v7.app.AppCompatDelegate;
import android.util.Log;

import com.myreader.component.AppComponent;
import com.myreader.component.DaggerAppComponent;
import com.myreader.module.AppModule;
import com.myreader.module.BookApiModule;
import com.myreader.util.Constant;
import com.myreader.util.SharedPreferencesUtil;

/**
 * Created by admin on 2017/2/16 0016.
 */

public class BaseApplication extends Application {

    private static BaseApplication instance;
    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        appComponent = DaggerAppComponent.builder()
                .bookApiModule(new BookApiModule())
                .appModule(new AppModule(this))
                .build();
        SharedPreferencesUtil.init(getApplicationContext(), getPackageName() + "_preference", Context.MODE_MULTI_PROCESS);
        initNightMode();
    }

    public static BaseApplication getInstance() {
        return instance;
    }

    protected void initNightMode() {
        boolean isNight = SharedPreferencesUtil.getInstance().getBoolean(Constant.ISNIGHT, false);
        Log.i("isNight", "isNight=" + isNight);
        if (isNight) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }
}
