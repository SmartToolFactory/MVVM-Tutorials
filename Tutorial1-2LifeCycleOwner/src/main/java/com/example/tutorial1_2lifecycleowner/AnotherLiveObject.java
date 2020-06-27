package com.example.tutorial1_2lifecycleowner;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;
import android.content.Context;
import android.widget.Toast;

public class AnotherLiveObject implements LifecycleObserver {

    private Lifecycle mLifecycle;

    private Context mContext;

    public AnotherLiveObject(Context context) {
        mContext = context;
        Toast.makeText(context, "AnotherLiveObject Constructor", Toast.LENGTH_SHORT).show();
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public void onResume() {
        Toast.makeText(mContext, "AnotherLiveObject onResume()", Toast.LENGTH_SHORT).show();

    }


    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    public void onPause() {
        Toast.makeText(mContext, "AnotherLiveObject onPause()", Toast.LENGTH_SHORT).show();
    }

}