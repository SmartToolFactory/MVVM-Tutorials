package com.omeriyioz.tutorial6_1_livedata_builder_function

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/*
* The following two different approaches does the same thing.
*
*
* 🔥 livedata builder function :
* The code block starts executing when LiveData becomes active and is automatically canceled after a configurable timeout when the LiveData becomes inactive.
* If it is canceled before completion, it is restarted if the LiveData becomes active again. If it completed successfully in a previous run, it doesn't restart.
* Note that it is restarted only if canceled automatically. If the block is canceled for any other reason (e.g. throwing a CancelationException), it is not restarted.
* https://developer.android.com/topic/libraries/architecture/coroutines#livedata
*
* 👍 liveData builder is invoked when the observer is active.
* 👎 If you want to invoke liveData when a button is clicked, then livedata builder function is not suitable for this case.
* */
class MainViewModel : ViewModel() {

    // 🔥 WAY 1 - By using init{ } inside viewModel
    private val _weather1 = MutableLiveData<String>()
    val weather1: LiveData<String> = _weather1

    init {
        viewModelScope.launch {
            _weather1.value = "Loading.."
            _weather1.value = WeatherApi.fetchWeatherForecast()
        }
    }


    // 🔥 WAY 2 - By using liveData builder.
    val weather2: LiveData<String> = liveData {
        emit("Loading..")
        emit(WeatherApi.fetchWeatherForecast())
    }

}

/*
* Simulates an asynchronous API such as network call.
* */
object WeatherApi {

    suspend fun fetchWeatherForecast(): String {
        delay(2000)
        return listOf("Sunny", "Rainy", "Cloudy").random()
    }
}