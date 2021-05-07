package com.omeriyioz.tutorial6_1_livedata_builder_function

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.switchMap
import kotlinx.coroutines.delay

/*
🔥 In this example we used livedata-ktx extensions for Transformations.
All of the Transformations class functions
Transformations.map(), Transformations.switchMap(), Transformations.distinctUntilChanged()
are also available as extension functions on LiveData :
liveData.map(), liveData.switchMap() , liveData.distinctUntilChanged()

🔥 You can use switchMap and liveData builder function together, as you see in this example.
You observe 'weather' liveData.
When you set a new city value on 'city' LiveData, switchMap invokes the liveData which is returned by liveData builder function.

switchMap observes two LiveData,
- 1st is 'city' LiveData.
- 2nd is the LiveData returned by liveData builder function.
When the value of 'city' LiveData changed, livedata builder block is invoked and 2 string value is emitted to the observer of 'weather' LiveData.
*/

class SecondViewModel : ViewModel() {

    val city = MutableLiveData<String>()

    val weather: LiveData<String> = city.switchMap {
        liveData {
            emit("Loading")
            emit(WeatherApi2.fetchWeatherForecast(it))
        }

    }

    fun setCity(newCity: String) {
        city.value = newCity
    }

}

/*
* Simulates an asynchronous API such as network call.
* */
object WeatherApi2 {

    suspend fun fetchWeatherForecast(city: String): String {
        delay(2000)

        return when (city) {
            "London" -> "Rainy"
            else -> "Sunny"
        }

    }
}