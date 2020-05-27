package zion830.com.rangetimepickerdialog

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import zion830.com.range_picker_dialog.OnTimeRangeSelectedListener
import zion830.com.range_picker_dialog.TimeRange
import zion830.com.range_picker_dialog.TimeRangePickerDialog

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tv_selected_range.text = DateTimeUtil.getCurrentTimeRangeString()
        btn_show_dialog.text = DateTimeUtil.getTodayString()
        btn_show_dialog.setOnClickListener {
            showDialog()
        }
    }

    private fun showDialog() {
        TimeRangePickerDialog.create(object : OnTimeRangeSelectedListener {
            override fun onTimeSelected(timeRange: TimeRange) {
                tv_selected_range.text = timeRange.getReadableTimeRange()
            }
        }).show(supportFragmentManager)
    }
}
