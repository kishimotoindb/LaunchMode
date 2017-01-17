package com.example.root.launchmode;

import android.app.Activity;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by haichen.cui on 17-1-17.
 */

public class ActivityStack {
    public static List<Activity> sStack = new ArrayList<>();

    public static void list() {
        StringBuilder sb = new StringBuilder("list: \n");
        for (Activity activity : sStack) {
            sb.append(activity.toString() + "\n");
        }
        Log.i("xiong", "list: " + sb.toString());
    }
}
