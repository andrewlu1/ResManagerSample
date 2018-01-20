# ResManagerSample
使用ResManager 构建的一个可以演示动态皮肤切换的示例工程。
动态换肤原理：https://www.atatech.org/articles/98848

#使用方法
1：按照正常方式编写主工程代码，使用正常的R.color/R.drawable/R.txt等方式获取资源内容。
2：添加皮肤module, 将主工程中的res目录资源拷贝一份替换 皮肤module的res目录。并修改相应资源的值为当前皮肤主题。
3：在project根目录中的build.gradle中添加插件依赖：
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
4：在主工程以及皮肤工程中的 build.gradle 中添加插件apply:
```gradle
apply plugin: 'cn.andrewlu.plugins.skinplugin'
```
5: 在主工程中添加resmanager 工具包的引用：
```gradle
dependencies {
    //资源管理工具类。
    implementation 'cn.andrewlu:resmanager:+'
}
```
6: build 皮肤工程，生成皮肤资源apk 文件。 并将其放入主工程的assets/skins目录下。
7：主工程添加ResManager的初始化：
```java
public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        ResManager.getInstance().init(this);
    }
}
```
8: 主工程添加Theme变更监听：
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
```
9: 运行主app工程，点击按钮切换皮肤，查看效果。如果正常运行，View 的背景颜色以及文字将顺利切换为皮肤资源中的设定值 。
10: 当前ResManager还比较粗糙，仅实现资源查找功能。

#后续待完善内容有：
1：皮肤动态下载，不仅仅可以在assets中内置若干皮肤，也能够动态从网络下载皮肤并使用。
2：xml布局中的资源ID自动应用皮肤资源，在主工程中隐藏主题变更监听逻辑，由框架版主完成，而不需要开发者再去监听。
3：当前工程皮肤列表管理，能够显示当前有几套皮肤，当前应用的是哪套皮肤，当前皮肤版本，皮肤预览效果等。




