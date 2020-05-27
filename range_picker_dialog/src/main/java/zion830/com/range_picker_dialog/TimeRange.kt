package zion830.com.range_picker_dialog

import androidx.annotation.IntRange

data class TimeRange(
    @IntRange(from = 1, to = 23) val startHour: Int,
    @IntRange(from = 0, to = 59) val startMinute: Int,
    @IntRange(from = 1, to = 23) val endHour: Int,
    @IntRange(from = 0, to = 59) val endMinute: Int
) {
    // Time string like 'AM 10:30 - PM 1:00'
    val readableTimeRange =
        TimePickerUtils.getReadableTimeString(startHour, startMinute, endHour, endMinute)

    // Return whether the start time is earlier than the end time
    val isCorrectSequence =
        TimePickerUtils.isCorrectSequence(startHour, startMinute, endHour, endMinute)
}