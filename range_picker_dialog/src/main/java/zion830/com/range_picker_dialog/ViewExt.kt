package zion830.com.range_picker_dialog

import android.graphics.Color
import android.widget.Button

fun Button.setUsable(
    usable: Boolean,
    usableColorResId: Int = Color.BLACK,
    disableColorResId: Int = context.getColor(R.color.color_4D000000)
) {
    isEnabled = usable
    setTextColor(if (usable) usableColorResId else disableColorResId)
}