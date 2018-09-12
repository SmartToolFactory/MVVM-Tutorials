package com.example.tutorial1_2lifecycleowner;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.OnLifecycleEvent;
import android.content.Context;
import android.widget.Toast;

public class MyLiveObject implements LifecycleObserver {

    private Lifecycle mLifecycle;

    private Context mContext;

    public MyLiveObject(Context context) {
        mContext = context;
        Toast.makeText(context, "MyLiveObject Constructor", Toast.LENGTH_SHORT).show();
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public void onResume() {
        Toast.makeText(mContext, "MyLiveObject onResume()", Toast.LENGTH_SHORT).show();

    }


    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    public void onPause() {
        Toast.makeText(mContext, "MyLiveObject onPause()", Toast.LENGTH_SHORT).show();
    }

}
