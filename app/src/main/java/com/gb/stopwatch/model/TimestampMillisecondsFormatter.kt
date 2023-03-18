package com.gb.stopwatch.model

import com.gb.stopwatch.utils.ZERO

class TimestampMillisecondsFormatter {

    fun format(timestamp: Long): String {
        val millisecondsFormatted = (timestamp % THOUSAND).pad(MILLISECONDS_LENGTH)
        val seconds = timestamp / THOUSAND
        val secondsFormatted = (seconds % SIXTY).pad(SECONDS_LENGTH)
        val minutes = seconds / SIXTY
        val minutesFormatted = (minutes % SIXTY).pad(MINUTES_LENGTH)
        val hours = minutes / SIXTY
        return if (hours > Int.ZERO) {
            val hoursFormatted = (minutes / SIXTY).pad(HOURS_LENGTH)
            "$hoursFormatted:$minutesFormatted:$secondsFormatted"
        } else {
            "$minutesFormatted:$secondsFormatted:$millisecondsFormatted"
        }
    }

    private fun Long.pad(desiredLength: Int) = this.toString().padStart(desiredLength, PAD_CHAR)

    companion object {
        private const val PAD_CHAR = '0'
        private const val THOUSAND = 1000
        private const val SIXTY = 60
        private const val MILLISECONDS_LENGTH = 3
        private const val SECONDS_LENGTH = 2
        private const val MINUTES_LENGTH = 2
        private const val HOURS_LENGTH = 2
    }
}
