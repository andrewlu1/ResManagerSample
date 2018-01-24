package cn.andrewlu.plugins;

import android.app.Application;

import cn.andrewlu.resmanager.ResManager;

/**
 * Created by andrewlu on 2018/1/18.
 */

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        ResManager.getInstance().init(this);
    }
}
