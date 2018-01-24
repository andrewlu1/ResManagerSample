package cn.andrewlu.plugins;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import cn.andrewlu.resmanager.ResManager;
import cn.andrewlu.resmanager.SkinActivity;
import cn.andrewlu.resmanager.dao.ThemeInfo;

/**
 * Created by andrewlu on 2018/1/18.
 */

public class MainActivity extends SkinActivity {
    private TextView themeViewText;
    private ListView themeListView;
    private ThemeInfoAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        themeViewText = findViewById(R.id.previewText);
        themeListView = findViewById(R.id.skinListView);

        adapter = new ThemeInfoAdapter(this, ResManager.getInstance().getAllThemes());
        themeListView.setAdapter(adapter);
        themeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ThemeInfo info = (ThemeInfo) parent.getAdapter().getItem(position);
                if (info != null) {
                    ResManager.getInstance().setTheme(info.name);
                }
            }
        });
    }
}
