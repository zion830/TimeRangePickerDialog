package zion830.com.range_picker_dialog

interface TimeRangeSelectable<T> {

    fun setOnDayMode(OnDayMode: Boolean): T

    fun setTimeInterval(timeInterval: Int): T

    fun setOnTimeRangeSelectedListener(timeRangeSelectedListener: OnTimeRangeSelectedListener): T

    fun setTimeRange(timeRange: TimeRange): T
}
