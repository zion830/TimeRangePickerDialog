package zion830.com.range_picker_dialog

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.IntRange
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import zion830.com.range_picker_dialog.databinding.TimeRangePickerDialogBinding

/**
 * TimeRangePicker based on DialogFragment.
 */
class TimeRangePickerDialog : DialogFragment() {
    // Listener called when time is selected
    var onTimeRangeSelectedListener: OnTimeRangeSelectedListener? = null

    // OK button is disabled if end time is earlier than start time
    var oneDayMode = true

    // Minute time interval. default value is 10
    var interval = TimeRangePicker.defaultInterval

    // default range is {current hour}:00 ~ {current hour + 1}:00
    var timeRange = TimePickerUtils.getCurrentTimeRange()

    private lateinit var binding: TimeRangePickerDialogBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = TimeRangePickerDialogBinding.inflate(inflater)
        setUpTab()
        initView()
        setTimeRange()
        return binding.root
    }

    fun show(fragmentManager: FragmentManager) {
        super.show(fragmentManager, TAG)
    }

    private fun initView() {
        with(binding) {
            tpStart.timeInterval = interval
            tpEnd.timeInterval = interval
            btnOk.setUsable(isOkBtnUsable())
            tpStart.setOnTimeChangedListener { _, _, _ -> btnOk.setUsable(isOkBtnUsable()) }
            tpEnd.setOnTimeChangedListener { _, _, _ -> btnOk.setUsable(isOkBtnUsable()) }
            btnOk.setOnClickListener {
                onTimeRangeSelectedListener?.onTimeSelected(getSelectedTimeRange())
                dismiss()
            }
            btnCancel.setOnClickListener { dismiss() }
        }
    }

    private fun setTimeRange() {
        with(binding) {
            tpStart.setDisplayedHour(timeRange.startHour)
            tpStart.setDisplayedMinute(timeRange.startMinute / interval)
            tpEnd.setDisplayedHour(timeRange.endHour)
            tpEnd.setDisplayedMinute(timeRange.endMinute / interval)
        }
    }

    private fun getSelectedTimeRange() = with(binding) {
        TimeRange(
            tpStart.getDisplayedHour(),
            tpStart.getDisplayedMinute(),
            tpEnd.getDisplayedHour(),
            tpEnd.getDisplayedMinute()
        )
    }

    private fun setUpTab() {
        if (context == null) {
            Log.e(TAG, "context is null")
            return
        }

        val tabHost = binding.tabHost.apply { setup() }
        val startTimeTitle = context!!.getString(R.string.start_time)
        val endTimeTitle = context!!.getString(R.string.end_time)
        val startTimeTab = tabHost.newTabSpec(startTimeTitle).apply {
            setContent(R.id.tab_start_time)
            setIndicator(startTimeTitle)
        }
        val endTimeTab = tabHost.newTabSpec(endTimeTitle).apply {
            setContent(R.id.tab_end_time)
            setIndicator(endTimeTitle)
        }

        tabHost.apply {
            addTab(startTimeTab)
            addTab(endTimeTab)
        }
    }

    private fun isOkBtnUsable(): Boolean {
        if (!oneDayMode) {
            return true
        }

        return TimePickerUtils.isCorrectSequence(getSelectedTimeRange())
    }

    class Builder : Buildable<TimeRangePickerDialog> {
        private var listener: OnTimeRangeSelectedListener? = null
        private var defaultTimeInterval = TimeRangePicker.defaultInterval
        private var defaultTimeRange = TimePickerUtils.getCurrentTimeRange()
        private var defaultOnDayMode = true

        fun setOnDayMode(OnDayMode: Boolean): Builder {
            defaultOnDayMode = OnDayMode
            return this
        }

        fun setTimeInterval(timeInterval: Int): Builder {
            defaultTimeInterval = timeInterval
            return this
        }

        fun setOnTimeRangeSelectedListener(timeRangeSelectedListener: OnTimeRangeSelectedListener): Builder {
            listener = timeRangeSelectedListener
            return this
        }

        fun setOnTimeRangeSelectedListener(onSelected: (timeRange: TimeRange) -> Unit): Builder {
            listener = object : OnTimeRangeSelectedListener {
                override fun onTimeSelected(timeRange: TimeRange) {
                    onSelected(timeRange)
                }
            }
            return this
        }

        fun setTimeRange(
            @IntRange(from = 1, to = 23) startHour: Int,
            @IntRange(from = 0, to = 59) startMinute: Int,
            @IntRange(from = 1, to = 23) endHour: Int,
            @IntRange(from = 0, to = 59) endMinute: Int
        ): Builder {
            defaultTimeRange = TimeRange(startHour, startMinute, endHour, endMinute)
            return this
        }

        fun setTimeRange(timeRange: TimeRange): Builder {
            defaultTimeRange = timeRange
            return this
        }

        override fun build() = TimeRangePickerDialog().apply {
            onTimeRangeSelectedListener = listener
            interval = defaultTimeInterval
            oneDayMode = defaultOnDayMode
            timeRange = defaultTimeRange
        }
    }

    companion object {
        private val TAG = TimeRangePickerDialog::class.java.name
    }
}