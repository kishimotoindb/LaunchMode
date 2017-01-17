package com.example.root.launchmode;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class Other2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other);
        ActivityStack.sStack.add(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        ActivityStack.list();
    }

    public void clickmain(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    public void clickother(View view) {
        startActivity(new Intent(this, Other2Activity.class));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityStack.sStack.remove(this);
        Log.i("xiong", "onDestroy: " + toString());
        ActivityStack.list();
    }
}
