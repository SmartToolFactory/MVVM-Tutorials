package com.example.tutorial3livedataanddatabinding.viewmodel

import android.os.Handler
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class CounterViewModel : ViewModel() {

    val counterLiveData1 = MutableLiveData<Int>()
    val counterLiveData2 = MutableLiveData<Int>()

    private var myCounter1 = 0
    private var myCounter2 = 0

    init {

        // 🔥 counter - way 1
        viewModelScope.launch {
            while (true) {
                delay(1000)
                myCounter1++
                counterLiveData1.value = myCounter1 // Value is set even app is paused but onChanged() is not called until LiveData is active()
            }
        }


        // 🔥 counter - way 2
        val handler = Handler()
        handler.postDelayed(object : Runnable {
            override fun run() {
                myCounter2++
                counterLiveData2.value = myCounter2 // Value is set even app is paused but onChanged() is not called until LiveData is active()
                handler.postDelayed(this, 1000)
            }
        }, 1000)
    }
}