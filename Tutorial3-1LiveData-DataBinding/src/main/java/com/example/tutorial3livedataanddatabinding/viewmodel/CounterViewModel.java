package com.example.tutorial3livedataanddatabinding.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import android.os.Handler;

public class CounterViewModel extends ViewModel {
    private MutableLiveData<Integer> counter = new MutableLiveData<>();

    public MutableLiveData<Integer> getCounter() {
        return counter;
    }

    private int myCounter;

    public CounterViewModel() {

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                myCounter++;
                // Value is set even app is paused but onChanged() is not called until LiveData is active()
                counter.setValue(myCounter);
                handler.postDelayed(this, 1000);
            }
        }, 1000);
    }
}
