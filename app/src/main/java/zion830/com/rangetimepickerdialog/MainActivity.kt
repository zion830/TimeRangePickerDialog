package zion830.com.rangetimepickerdialog

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import zion830.com.range_picker_dialog.DateTimeUtil
import zion830.com.range_picker_dialog.OnTimeRangeSelectedListener
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
        TimeRangePickerDialog.newInstance(object : OnTimeRangeSelectedListener {
            override fun onTimeSelected(startHour: Int, startMin: Int, endHour: Int, endMin: Int) {
                tv_selected_range.text =
                    DateTimeUtil.getReadableTimeString(startHour, endHour, startMin, endMin)
            }
        }).show(supportFragmentManager)
    }
}
