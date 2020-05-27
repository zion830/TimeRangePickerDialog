package zion830.com.range_picker_dialog

import androidx.annotation.IntRange
import java.util.*

internal object TimePickerUtils {
    const val MINUTE_FORMAT = "%02d"
    const val MINUTES_MIN = 0
    const val MINUTES_MAX = 60

    private const val DEFAULT_HOUR_RANGE = 1
    private const val AM = "AM"
    private const val PM = "PM"
    private const val timeTextFormat = "%s %d:%02d - %s %d:%02d"

    fun getCurrentTimeRange(): TimeRange {
        val startHour = Calendar.getInstance()[Calendar.HOUR_OF_DAY]
        val endHour = if (startHour < 23) startHour + DEFAULT_HOUR_RANGE else startHour
        return TimeRange(startHour, MINUTES_MIN, endHour, MINUTES_MIN)
    }

    fun getReadableTimeString(
        @IntRange(from = 1, to = 23) startHour: Int,
        @IntRange(from = 0, to = 59) startMin: Int,
        @IntRange(from = 1, to = 23) endHour: Int,
        @IntRange(from = 0, to = 59) endMin: Int
    ): String = timeTextFormat.format(
        getAmPm(startHour), getHourForAmPm(startHour), startMin,
        getAmPm(endHour), getHourForAmPm(endHour), endMin
    )

    fun isCorrectSequence(
        @IntRange(from = 1, to = 23) startHour: Int,
        @IntRange(from = 0, to = 59) startMin: Int,
        @IntRange(from = 1, to = 23) endHour: Int,
        @IntRange(from = 0, to = 59) endMin: Int
    ) = when {
        startHour < endHour -> true
        startHour > endHour -> false
        else -> startMin < endMin
    }

    private fun getAmPm(@IntRange(from = 1, to = 23) hour: Int) = if (hour >= 12) PM else AM

    private fun getHourForAmPm(hour: Int) = when {
        hour == 0 -> 12
        hour > 12 -> hour - 12
        else -> hour
    }
}