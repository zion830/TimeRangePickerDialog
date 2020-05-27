package zion830.com.range_picker_dialog

import androidx.annotation.IntRange

data class TimeRange(
    @IntRange(from = 1, to = 24) val startHour: Int,
    @IntRange(from = 1, to = 24) val endHour: Int,
    @IntRange(from = 0, to = 59) val startMin: Int,
    @IntRange(from = 0, to = 59) val endMin: Int
) {

    fun getReadableTimeRange() =
        DateTimeUtil.getReadableTimeString(startHour, endHour, startMin, endMin)
}