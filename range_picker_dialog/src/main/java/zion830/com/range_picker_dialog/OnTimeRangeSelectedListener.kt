package zion830.com.range_picker_dialog

interface OnTimeRangeSelectedListener {

    fun onTimeSelected(startHour: Int, startMin: Int, endHour: Int, endMin: Int)
}