package zion830.com.rangetimepickerdialog

import java.text.SimpleDateFormat
import java.util.*

internal object DateTimeUtil {
    private const val DEFAULT_HOUR_RANGE = 1
    private val dateFormat = SimpleDateFormat("yyyy.M.d (EEE)", Locale.KOREA)
    private val timeRangeFormat = SimpleDateFormat("a K:00", Locale.KOREA)

    fun getCurrentTimeRangeString(): String {
        val currentTime = Calendar.getInstance()
        val startTimeString = timeRangeFormat.format(currentTime.time)
        val endTimeString = timeRangeFormat.format(currentTime.apply {
            add(Calendar.HOUR, DEFAULT_HOUR_RANGE)
        }.time)

        return "$startTimeString - $endTimeString"
    }

    fun getTodayString(): String = dateFormat.format(Calendar.getInstance().time).toString()
}