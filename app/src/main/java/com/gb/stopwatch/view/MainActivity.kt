package com.gb.stopwatch.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.gb.stopwatch.databinding.ActivityMainBinding
import com.gb.stopwatch.model.MainActivityMessage
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val mainViewModel: MainActivityViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViewModel()
        initButtons()
        mainViewModel.initCollector()
    }

    private fun initViewModel() {
        mainViewModel.messagesLiveData.observe(this) { mainActivityMessage ->
            processMessages(mainActivityMessage)
        }
    }

    private fun initButtons() {
        with(binding) {
            buttonStart.setOnClickListener { mainViewModel.onStartButtonPressed() }
            buttonPause.setOnClickListener { mainViewModel.onPauseButtonPressed() }
            buttonStop.setOnClickListener { mainViewModel.onStopButtonPressed() }
        }
    }

    private fun processMessages(mainActivityMessage: MainActivityMessage) {
        binding.textTime.text = mainActivityMessage.message
    }

}
