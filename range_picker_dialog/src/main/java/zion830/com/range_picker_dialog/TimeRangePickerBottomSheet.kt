package zion830.com.range_picker_dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import zion830.com.range_picker_dialog.databinding.TimeRangePickerBottomSheetDialogBinding

/**
 * TimeRangePicker based on BottomSheetDialogFragment.
 * If you want to use TimeRangePickerBottomSheet, you need to add Material Components for Android.
 * Note the following link : https://material.io/develop/android/docs/getting-started/
 */
class TimeRangePickerBottomSheet : BottomSheetDialogFragment() {
    private lateinit var binding: TimeRangePickerBottomSheetDialogBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = TimeRangePickerBottomSheetDialogBinding.inflate(inflater)
        initView()
        return binding.root
    }

    fun show(fragmentActivity: FragmentActivity) {
        show(fragmentActivity.supportFragmentManager, TAG)
    }

    private fun initView() {
        with(binding) {
            tpStart.setIs24HourView(true)
            tpEnd.setIs24HourView(true)
        }
    }

    companion object {
        private val TAG = TimeRangePickerBottomSheet::class.java.name

        fun getInstance(
        ): TimeRangePickerBottomSheet {
            val pickBottomSheetFragment = TimeRangePickerBottomSheet()
            return pickBottomSheetFragment
        }
    }
}