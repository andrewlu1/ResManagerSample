package cn.andrewlu.plugins;

import android.app.Application;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

import cn.andrewlu.resmanager.ResManager;
import cn.andrewlu.resmanager.Skin;
import cn.andrewlu.resmanager.view.SkinnableAction;
import cn.andrewlu.resmanager.view.TextColorAction;

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
