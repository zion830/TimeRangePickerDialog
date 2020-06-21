package zion830.com.rangetimepickerdialog

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import zion830.com.range_picker_dialog.TimeRangePickerBottomSheet
import zion830.com.range_picker_dialog.TimeRangePickerDialog

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_show_dialog.setOnClickListener {
            showDialog()
        }
        btn_show_bottom_sheet_dialog.setOnClickListener {
            showBottomSheetDialog()
        }
    }

    private fun showDialog() {
        TimeRangePickerDialog.Builder()
            .setTimeRange(10, 20, 16, 40)
            .setTimeInterval(20)
            .setOnDayMode(false)
            .setOnTimeRangeSelectedListener { tv_selected_range.text = it.readableTimeRange }
            .build()
            .show(supportFragmentManager)
    }

    private fun showBottomSheetDialog() {
        TimeRangePickerBottomSheet.getInstance().show(this)
    }
}
