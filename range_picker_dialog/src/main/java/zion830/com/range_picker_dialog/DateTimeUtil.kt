package zion830.com.range_picker_dialog

import androidx.annotation.IntRange
import java.text.SimpleDateFormat
import java.util.*

/*
 * Created by yunji on 14/04/2020
 */
object DateTimeUtil {
    const val MINUTE_FORMAT = "%02d"

    private const val AM = "오전"
    private const val PM = "오후"
    const val MINUTES_MIN = 0L
    const val MINUTES_MAX = 60L
    private const val HOUR_MIN = 1L
    private const val HOUR_MAX = 24L
    private const val UNIX_TRANS_NUM = 1000
    private const val DEFAULT_HOUR_RANGE = 1
    private const val timeTextFormat = "%s %d:%02d - %s %d:%02d"

    private val dateFormat = SimpleDateFormat("yyyy.M.d (EEE)", Locale.KOREA)
    private val timeRangeFormat = SimpleDateFormat("a K:00", Locale.KOREA)
    private val timeFormat = SimpleDateFormat("K:mm", Locale.KOREA)
    private val ampmFormat = SimpleDateFormat("a ", Locale.KOREA)

    fun getAmPm(@IntRange(from = HOUR_MIN, to = HOUR_MAX) hour: Int) = if (hour >= 12) PM else AM

    fun getHourForAmPm(hour: Int) = when {
        hour == 0 -> 12
        hour > 12 -> hour - 12
        else -> hour
    }

    fun getReadableTimeString(
        @IntRange(from = 1, to = 24) startHour: Int,
        @IntRange(from = 1, to = 24) endHour: Int,
        @IntRange(from = 1, to = 59) startMin: Int,
        @IntRange(from = 1, to = 59) endMin: Int
    ): String = timeTextFormat.format(
        getAmPm(startHour), getHourForAmPm(startHour), startMin,
        getAmPm(endHour), getHourForAmPm(endHour), endMin
    )

    fun getReadableTimeString(timestamp: Long): String =
        timeFormat.format(Date(timestamp * UNIX_TRANS_NUM))

    fun getReadableDateString(timestamp: Long): String =
        dateFormat.format(Date(timestamp * UNIX_TRANS_NUM))

    fun getReadableAmPmString(timestamp: Long): String =
        ampmFormat.format(Date(timestamp * UNIX_TRANS_NUM))

    fun getReadableTimeRange(startAt: Long, endAt: Long): String {
        val startTimeString = getReadableAmPmString(startAt) + getReadableTimeString(startAt)
        val endTimeRange = getReadableAmPmString(endAt)
        val endTimeString = if (startTimeString.startsWith(endTimeRange)) {
            getReadableTimeString(endAt)
        } else {
            endTimeRange + getReadableTimeString(endAt)
        }

        return "$startTimeString - $endTimeString"
    }

    fun getCurrentTimeRangeString(): String {
        val currentTime = Calendar.getInstance()
        val startTimeString = timeRangeFormat.format(currentTime.time)
        val endTimeString = timeRangeFormat.format(currentTime.apply {
            add(Calendar.HOUR, DEFAULT_HOUR_RANGE)
        }.time)

        return "$startTimeString - $endTimeString"
    }

    fun getTodayString(): String = dateFormat.format(Calendar.getInstance().time).toString()

    fun getDateString(date: Date): String = dateFormat.format(date).toString()

    fun getDateString(calendar: Calendar): String = dateFormat.format(calendar.time).toString()

    fun isCorrectSequence(
        @IntRange(from = 1, to = 24) startHour: Int,
        @IntRange(from = 1, to = 24) endHour: Int,
        @IntRange(from = 1, to = 59) startMin: Int,
        @IntRange(from = 1, to = 59) endMin: Int
    ): Boolean {
        if (startHour < endHour) {
            return true
        } else if (startHour > endHour) {
            return false
        }
        return startMin < endMin
    }
}