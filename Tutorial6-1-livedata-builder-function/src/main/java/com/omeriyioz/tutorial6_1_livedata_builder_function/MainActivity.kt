package com.omeriyioz.tutorial6_1_livedata_builder_function

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.omeriyioz.tutorial6_1_livedata_builder_function.databinding.ActivityMainBinding

/*
* https://www.youtube.com/watch?v=GUvi1LS_8Kw video by Jose Alcérreca
* */
class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        // 🔥 ‍LifeCycleOwner should be set for LiveData changes to be propagated to UI for this binding
        binding.lifecycleOwner = this

        binding.viewModel = viewModel

        binding.buttonOpenSecondActivity.setOnClickListener {
            val intent = Intent(this, SecondActivity::class.java)
            startActivity(intent)
        }
    }
}