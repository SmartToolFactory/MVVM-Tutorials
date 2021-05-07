package com.omeriyioz.tutorial6_2_coroutine_livedata_stateflow_sharedflow

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/*

* 🔥 You cannot set LiveData value in a background thread. Otherwise crash occurs.
* 🔥 You can set StateFlow and SharedFlow in a background thread.

* 🔥 StateFlow has initial value.
* 🔥 SharedFlow does not have initial value.
*
* 🔥 What is the argument given to MutableSharedFlow ? It is for replay cache.
* What is replay cache ?
* A shared flow keeps a specific number of the most recent values in its replay cache.
* Every new subscriber first gets the values from the replay cache and then gets new emitted values.
* The maximum size of the replay cache is specified when the shared flow is created by the replay parameter.
* https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.flow/-shared-flow/
* */

class MainViewModel : ViewModel() {

    val weatherLiveData = MutableLiveData<String>()


    // 😎 Setting LiveData value in main thread is safe
    fun actionForLiveData1() {
        viewModelScope.launch {
            weatherLiveData.value = "Loading.."
        }
    }


    // ☠️  Setting LiveData value in IO thread causes crash.
    fun actionForLiveData2() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                weatherLiveData.value = "Loading.."
            }
        }
    }


    // 😎 Setting StateFlow value in IO thread is safe.
    val weatherStateFlow = MutableStateFlow("Loading..")
    fun actionForStateFlow() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                weatherStateFlow.value = "Done StateFlow."
            }
        }
    }


    // 😎 Setting SharedFlow value in IO thread is safe.
    val weatherSharedFlow = MutableSharedFlow<String>(1)
    fun actionForSharedFlow() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                weatherSharedFlow.tryEmit("Done SharedFlow.")
            }
        }
    }
}
