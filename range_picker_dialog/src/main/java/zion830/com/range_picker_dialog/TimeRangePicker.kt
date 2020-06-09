package zion830.com.range_picker_dialog

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.util.AttributeSet
import android.util.Log
import android.widget.NumberPicker
import android.widget.TimePicker
import androidx.annotation.IntRange
import androidx.core.math.MathUtils

internal class TimeRangePicker @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : TimePicker(context, attrs) {
    var timeInterval = defaultInterval
        set(@IntRange(from = 0, to = 60) value) {
            if (field !in minInterval..maxInterval) {
                Log.w("RangeTimePicker", "timeInterval must be between $minInterval..$maxInterval")
            }

            field = MathUtils.clamp(value, minInterval, maxInterval)
            setInterval(field)
            invalidate()
        }

    companion object {
        const val defaultInterval = 10
        const val minInterval = 1
        const val maxInterval = 60
    }

    init {
        setInterval(timeInterval)
        setDisplayedMinute(0)
    }

    @SuppressLint("PrivateApi")
    fun setInterval(@IntRange(from = 1, to = 60) interval: Int) {
        try {
            val classForId = Class.forName("com.android.internal.R\$id")
            val fieldId = classForId.getField("minute").getInt(null)
            (this.findViewById(fieldId) as NumberPicker).run {
                minValue = TimePickerUtils.MINUTES_MIN
                maxValue = TimePickerUtils.MINUTES_MAX / interval - 1
                displayedValues = getDisplayedValue()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun setDisplayedHour(@IntRange(from = 0, to = 23) hour: Int) =
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            this.hour = MathUtils.clamp(hour, 0, 23)
        } else {
            this.currentHour = MathUtils.clamp(hour, 0, 23)
        }

    fun setDisplayedMinute(@IntRange(from = 0, to = 59) minute: Int) =
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            this.minute = minute
        } else {
            this.currentMinute = minute
        }

    fun getDisplayedHour() = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        hour
    } else {
        currentHour
    }

    fun getDisplayedMinute(): Int = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        minute * timeInterval
    } else {
        currentMinute * timeInterval
    }

    private fun getDisplayedValue(interval: Int = timeInterval): Array<String> {
        val minutesArray = ArrayList<String>()
        for (i in 0 until TimePickerUtils.MINUTES_MAX step interval) {
            minutesArray.add(String.format(TimePickerUtils.MINUTE_FORMAT, i))
        }

        return minutesArray.toArray(arrayOf(""))
    }
}