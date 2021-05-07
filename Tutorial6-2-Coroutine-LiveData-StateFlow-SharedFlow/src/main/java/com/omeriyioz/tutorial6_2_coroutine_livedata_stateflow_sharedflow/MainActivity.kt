package com.omeriyioz.tutorial6_2_coroutine_livedata_stateflow_sharedflow

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import com.omeriyioz.tutorial6_2_coroutine_livedata_stateflow_sharedflow.databinding.ActivityMainBinding
import kotlinx.coroutines.flow.collect

class MainActivity : AppCompatActivity() {
    private val counterViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        // 🔥 ‍LifeCycleOwner should be set for LiveData changes to be propagated to UI for this binding
        binding.lifecycleOwner = this

        binding.viewModel = counterViewModel


        // 😎 Setting StateFlow value in IO thread is safe.
        binding.buttonForStateFlow.setOnClickListener {
            counterViewModel.actionForStateFlow()
        }

        // Observe StateFlow
        lifecycleScope.launchWhenResumed {
            counterViewModel.weatherStateFlow.collect {
                binding.textViewStateFlow.text = it
            }
        }


        // 😎 Setting SharedFlow value in IO thread is safe.
        binding.buttonForSharedFlow.setOnClickListener {
            counterViewModel.actionForSharedFlow()
        }

        // Observe SharedFlow
        lifecycleScope.launchWhenResumed {
            counterViewModel.weatherSharedFlow.collect {
                binding.textViewSharedFlow.text = it
            }
        }

    }
}