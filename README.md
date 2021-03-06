# ResManagerSample
使用ResManager 构建的一个可以演示动态皮肤切换的示例工程。
动态换肤原理：https://www.atatech.org/articles/98848

# 更新（2018.1.24）

* Resmanager升级到1.0.3, 新增 CompoundDrawable 属性的换肤支持。
* 代码中不需要再监听ThemeChange事件，虽然你可以这么做。
* 尽量使用Skin API去设置那些需要换肤的控件属性。 
同时不要用控件原生的API如：TextView.setText() 和 SkinApi 混用。这会导致换肤时不可预料的属性值。
* 如果皮肤中不存在某资源，则会使用主工程内置的资源，因此皮肤中尽量仅包含那些需要换肤的资源项。
* 注：一个防止皮肤APK 过大的有效方式是，不要依赖任何 lib,如appcompat-v7. 同时编译时开启混淆，
但注意设置：shrinkResources=false，如果设为true的话，编译生成的apk中将不包含drawable资源（因为当前皮肤工程并无这些资源引用）
* 皮肤APK，最少应添加一个App 类，否则编译时会发现dex 没有任何class 文件，导致编译失败。
* 插件以及ResManager Lib 已提交JCenter 审核通过，因此可以删除gradle 中的maven{ url(xxx)}配置。直接引用插件即可。


 # 更新（2018.1.23）
 * 增加皮肤列表展现
 * 显示当前选中皮肤
 * 切换皮肤即时效果预览

# 使用方法
 > 1：按照正常方式编写主工程代码，使用正常的R.color/R.drawable/R.txt等方式获取资源内容。

 > 2：添加皮肤module, 将主工程中的res目录资源拷贝一份替换 皮肤module的res目录。并修改相应资源的值为当前皮肤主题。

 > 3：在project根目录中的build.gradle中添加插件依赖：
```gradle

buildscript {

    repositories {
        //++
        maven {url "https://dl.bintray.com/andrewlu1/maven/"}
    }
    dependencies {
        //++
        //用于定制皮肤插件的编译。
        classpath "cn.andrewlu.plugins:skinplugin:1.0.1"
    }
}

allprojects {
    repositories {
        //++ 在原有基础上添加以下内容。
        maven {url "https://dl.bintray.com/andrewlu1/maven/"}
    }
}
```
> 4：在主工程以及皮肤工程中的 build.gradle 中添加插件apply:
```gradle
apply plugin: 'cn.andrewlu.plugins.skinplugin'
```
> 5: 在主工程中添加resmanager 工具包的引用：
[注：已废弃。不需要手动添加此依赖，由插件自动添加依赖]
```gradle
dependencies {
    //资源管理工具类。
    implementation 'cn.andrewlu:resmanager:+'
}
```

> 6: build 皮肤工程，生成皮肤资源apk 文件。 并将其放入主工程的assets/skins目录下。

> 7：主工程添加ResManager的初始化：
```java
public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        ResManager.getInstance().init(this);
    }
}
```

> 8: 主工程添加Theme变更监听：
```java
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
```
> 9: 运行主app工程，点击按钮切换皮肤，查看效果。如果正常运行，View 的背景颜色以及文字将顺利切换为皮肤资源中的设定值 。
> 10: 当前ResManager还比较粗糙，仅实现资源查找功能。

# 后续待完善内容有：

 1. 皮肤动态下载，不仅仅可以在assets中内置若干皮肤，也能够动态从网络下载皮肤并使用。
 2. xml布局中的资源ID自动应用皮肤资源，在主工程中隐藏主题变更监听逻辑，由框架版主完成，而不需要开发者再去监听。[done]
 3. 当前工程皮肤列表管理，能够显示当前有几套皮肤，当前应用的是哪套皮肤，当前皮肤版本，皮肤预览效果等。[done]
 
 
 # Contact US
[QQ: 2897319925]
欢迎大家进来闲扯。如有意愿共同维护，请加Q时备注。


