package com.example.root.launchmode;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

/**
 问题列表：

 1.Intent.FLAG_ACTIVITY_CLEAR_TOP标志的使用

 条件： MainActivity默认启动方式，从Other2Activity页面启动MainActivity（启动方式具体看代码）
 测试结果：

 启动顺序list:
 com.example.root.launchmode.MainActivity@41970d1
 com.example.root.launchmode.MainActivity@73fb76c   注意，返回到这里，最上面的MainActivity
 com.example.root.launchmode.Other1Activity@c5d3ea3
 com.example.root.launchmode.Other2Activity@c4e8ca6
 com.example.root.launchmode.Other2Activity@2df2b65
 com.example.root.launchmode.Other2Activity@e3c9390
 com.example.root.launchmode.Other2Activity@174e984

 销毁顺序
 onDestroy: com.example.root.launchmode.Other1Activity@c5d3ea3
 onDestroy: com.example.root.launchmode.Other2Activity@c4e8ca6
 onDestroy: com.example.root.launchmode.Other2Activity@2df2b65
 onDestroy: com.example.root.launchmode.Other2Activity@e3c9390
 onDestroy: com.example.root.launchmode.MainActivity@73fb76c
 onDestroy: com.example.root.launchmode.Other2Activity@174e984

 最终list
 com.example.root.launchmode.MainActivity@41970d1
 com.example.root.launchmode.MainActivity@40b79e4   完全重新创建的MainActivity

 结论：
 1）默认启动模式下，MainActivity重新创建，而不是走onNewIntent
 2）销毁的顺序并不是从顶部到MainActivity,如果需要通过页面跳转实现流程控制，需要特别注意。
 3）如果栈底有两个MainActivity，FLAG_ACTIVITY_CLEAR_TOP返回的上面的那个

 */

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActivityStack.sStack.add(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        ActivityStack.list();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.i("xiong", "onNewIntent: ");
    }

    public void clickmain(View view) {
        startActivity(new Intent(this, MainActivity.class));
    }

    public void clickother(View view) {
        startActivity(new Intent(this, Other1Activity.class));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityStack.sStack.remove(this);
        Log.i("xiong", "onDestroy: " + toString());
        ActivityStack.list();
    }
}
