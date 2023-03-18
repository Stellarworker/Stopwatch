package com.gb.stopwatch.model.viewModel

interface MainActivityViewModelContract {
    fun initCollector()
    fun onStartButtonPressed()
    fun onPauseButtonPressed()
    fun onStopButtonPressed()
}