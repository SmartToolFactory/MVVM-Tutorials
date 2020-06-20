package com.example.tutorial1_2lifecycleowner;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.OnLifecycleEvent;
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
