package zion830.com.range_picker_dialog

import androidx.annotation.IntRange
import java.util.Calendar.AM
import java.util.Calendar.PM

internal object DateTimeUtil {
    const val MINUTE_FORMAT = "%02d"
    const val MINUTES_MIN = 0L
    const val MINUTES_MAX = 60L

    private const val timeTextFormat = "%s %d:%02d - %s %d:%02d"

    fun getReadableTimeString(
        @IntRange(from = 1, to = 24) startHour: Int,
        @IntRange(from = 1, to = 24) endHour: Int,
        @IntRange(from = 0, to = 59) startMin: Int,
        @IntRange(from = 0, to = 59) endMin: Int
    ): String = timeTextFormat.format(
        getAmPm(startHour), getHourForAmPm(startHour), startMin,
        getAmPm(endHour), getHourForAmPm(endHour), endMin
    )

    fun isCorrectSequence(
        @IntRange(from = 1, to = 24) startHour: Int,
        @IntRange(from = 1, to = 24) endHour: Int,
        @IntRange(from = 0, to = 59) startMin: Int,
        @IntRange(from = 0, to = 59) endMin: Int
    ) = when {
        startHour < endHour -> true
        startHour > endHour -> false
        else -> startMin < endMin
    }

    private fun getAmPm(@IntRange(from = 1, to = 24) hour: Int) =
        if (hour >= 12) PM else AM

    private fun getHourForAmPm(hour: Int) = when {
        hour == 0 -> 12
        hour > 12 -> hour - 12
        else -> hour
    }
}