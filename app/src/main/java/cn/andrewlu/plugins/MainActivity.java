package cn.andrewlu.plugins;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import cn.andrewlu.resmanager.IThemeChangeListener;
import cn.andrewlu.resmanager.ResManager;

/**
 * Created by andrewlu on 2018/1/18.
 */

public class MainActivity extends Activity implements IThemeChangeListener {
    private TextView themeViewText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        themeViewText = findViewById(R.id.themeView);
        ResManager.getInstance().addThemeObserver(this);
    }

    @Override
    public void onThemeChanged(Resources currentTheme) {
        themeViewText.setText(currentTheme.getText(R.string.theme_view_text));
        themeViewText.setBackgroundColor(currentTheme.getColor(R.color.colorAccent));
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.clickBtn: {
                ResManager.getInstance().setTheme("bluetheme-release");
                break;
            }
        }
    }
}
