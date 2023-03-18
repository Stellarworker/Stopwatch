package com.gb.stopwatch.view

import androidx.lifecycle.ViewModel
import com.gb.stopwatch.model.*
import com.gb.stopwatch.model.viewModel.MainActivityViewModelContract
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainActivityViewModel : ViewModel(), MainActivityViewModelContract {

    private val _messagesLiveData = SingleLiveEvent<MainActivityMessage>()
    val messagesLiveData: SingleLiveEvent<MainActivityMessage> by this::_messagesLiveData

    private val timestampProvider = object : TimestampProvider {
        override fun getMilliseconds() = System.currentTimeMillis()
    }

    private val scope = CoroutineScope(Dispatchers.IO + SupervisorJob())
    private val orchestratorScope = CoroutineScope(Dispatchers.IO + SupervisorJob())
    private val elapsedTimeCalculator = ElapsedTimeCalculator(timestampProvider)
    private val stopwatchStateCalculator =
        StopwatchStateCalculator(timestampProvider, elapsedTimeCalculator)
    private val stopwatchStateHolder = StopwatchStateHolder(
        stopwatchStateCalculator,
        elapsedTimeCalculator,
        TimestampMillisecondsFormatter()
    )

    private val stopwatchListOrchestrator =
        StopwatchListOrchestrator(stopwatchStateHolder, orchestratorScope)

    override fun initCollector() {
        scope.launch {
            stopwatchListOrchestrator.ticker.collect { time ->
                _messagesLiveData.postValue(MainActivityMessage(time))
            }
        }
    }

    override fun onStartButtonPressed() {
        stopwatchListOrchestrator.start()
    }

    override fun onPauseButtonPressed() {
        stopwatchListOrchestrator.pause()
    }

    override fun onStopButtonPressed() {
        stopwatchListOrchestrator.stop()
    }

}