package com.gb.stopwatch.model

import com.gb.stopwatch.utils.ZERO

class ElapsedTimeCalculator(
    private val timestampProvider: TimestampProvider,
) {

    fun calculate(state: StopwatchState.Running): Long {
        val currentTimestamp = timestampProvider.getMilliseconds()
        val timePassedSinceStart = if (currentTimestamp > state.startTime) {
            currentTimestamp - state.startTime
        } else {
            Long.ZERO
        }
        return timePassedSinceStart + state.elapsedTime
    }
}