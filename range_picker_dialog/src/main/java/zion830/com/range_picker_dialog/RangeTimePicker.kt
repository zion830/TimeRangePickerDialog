package zion830.com.range_picker_dialog

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.widget.NumberPicker
import android.widget.TimePicker
import androidx.annotation.IntRange
import androidx.core.math.MathUtils

class RangeTimePicker @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : TimePicker(context, attrs) {
    val minInterval = 1
    val maxInterval = 30
    val defaultInterval = 5
    var timeInterval = defaultInterval
        set(value) {
            if (field !in minInterval..maxInterval) {
                Log.w("RangeTimePicker", "timeInterval must be between $minInterval..$maxInterval")
            }

            field = MathUtils.clamp(minInterval, maxInterval, value)
            setInterval(field)
            invalidate()
        }

    init {
        setInterval()
        minute = 0
    }

    @SuppressLint("PrivateApi")
    fun setInterval(
        @IntRange(from = 1, to = 59)
        timeInterval: Int = defaultInterval
    ) {
        try {
            val classForId = Class.forName("com.android.internal.R\$id")
            val fieldId = classForId.getField("minute").getInt(null)
            (this.findViewById(fieldId) as NumberPicker).run {
                minValue = DateTimeUtil.MINUTES_MIN.toInt()
                maxValue = DateTimeUtil.MINUTES_MAX.toInt() / timeInterval - 1
                displayedValues = getDisplayedValue()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun getDisplayedMinutes(): Int = minute * timeInterval

    private fun getDisplayedValue(
        interval: Int = timeInterval
    ): Array<String> {
        val minutesArray = ArrayList<String>()
        for (i in 0 until DateTimeUtil.MINUTES_MAX.toInt() step interval) {
            minutesArray.add(String.format(DateTimeUtil.MINUTE_FORMAT, i))
        }

        return minutesArray.toArray(arrayOf(""))
    }
}