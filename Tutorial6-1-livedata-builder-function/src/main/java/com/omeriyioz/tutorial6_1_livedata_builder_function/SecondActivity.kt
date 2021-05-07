package com.omeriyioz.tutorial6_1_livedata_builder_function

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.omeriyioz.tutorial6_1_livedata_builder_function.databinding.ActivitySecondBinding

/*
* https://www.youtube.com/watch?v=KUn0-D6DpuY video by Jose Alcérreca
* */

class SecondActivity : AppCompatActivity() {

    private val viewModel: SecondViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivitySecondBinding = DataBindingUtil.setContentView(this, R.layout.activity_second)

        // 🔥 ‍LifeCycleOwner should be set for LiveData changes to be propagated to UI for this binding
        binding.lifecycleOwner = this

        binding.viewModel = viewModel
    }

}