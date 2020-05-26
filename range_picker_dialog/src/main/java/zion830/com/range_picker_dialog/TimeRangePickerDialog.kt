package zion830.com.range_picker_dialog

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import zion830.com.range_picker_dialog.databinding.TimeRangePickerDialogBinding

class TimeRangePickerDialog : DialogFragment() {
    // If oneDayMode is true, the OK button is disabled if the end time is earlier than the start time.
    var oneDayMode = true
    var onTimeRangeSelectedListener: OnTimeRangeSelectedListener? = null

    private lateinit var binding: TimeRangePickerDialogBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = TimeRangePickerDialogBinding.inflate(inflater)
        setUpTab()
        initView()
        return binding.root
    }

    private fun initView() {
        with(binding) {
            btnOk.setUsable(false)
            tpStart.setOnTimeChangedListener { _, hourOfDay, _ ->
                btnOk.setUsable(
                    isOkBtnUsable(
                        hourOfDay,
                        tpEnd.hour,
                        tpStart.getDisplayedMinutes(),
                        tpEnd.getDisplayedMinutes()
                    )
                )
            }
            tpEnd.setOnTimeChangedListener { _, hourOfDay, _ ->
                btnOk.setUsable(
                    isOkBtnUsable(
                        tpStart.hour,
                        hourOfDay,
                        tpStart.getDisplayedMinutes(),
                        tpEnd.getDisplayedMinutes()
                    )
                )
            }
            btnOk.setOnClickListener {
                onTimeRangeSelectedListener?.onTimeSelected(
                    tpStart.hour,
                    tpStart.getDisplayedMinutes(),
                    tpEnd.hour,
                    tpEnd.getDisplayedMinutes()
                )
                dismiss()
            }
            btnCancel.setOnClickListener { dismiss() }
        }
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

    fun show(fragmentManager: FragmentManager) {
        super.show(fragmentManager, TAG)
    }

    private fun isOkBtnUsable(startHour: Int, endHour: Int, startMin: Int, endMin: Int): Boolean {
        if (!oneDayMode) {
            return false
        }

        return DateTimeUtil.isCorrectSequence(startHour, endHour, startMin, endMin)
    }

    companion object {
        private val TAG = TimeRangePickerDialog::class.java.name

        fun newInstance(
            timeRangeSelectedListener: OnTimeRangeSelectedListener? = null
        ): TimeRangePickerDialog = TimeRangePickerDialog().apply {
            onTimeRangeSelectedListener = timeRangeSelectedListener
        }
    }
}