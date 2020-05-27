package zion830.com.range_picker_dialog

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class TimeRangePickerTest {
    private lateinit var timeRangePicker: TimeRangePicker

    @Before
    fun setUp() {
        val app: Context = ApplicationProvider.getApplicationContext()
        timeRangePicker = TimeRangePicker(app)
    }

    @Test
    fun testSetTimeInterval() {
        timeRangePicker.timeInterval = 100
        Assert.assertEquals(timeRangePicker.timeInterval, TimeRangePicker.maxInterval)
    }

    @Test
    fun testGetDisplayedMinutes() {
        val method =
            timeRangePicker::class.java.getDeclaredMethod("getDisplayedValue", Int::class.java)
        method.isAccessible = true
        val result: Array<String> = method.invoke(timeRangePicker, 10) as Array<String>
        Assert.assertArrayEquals(result, arrayOf("00", "10", "20", "30", "40", "50"))
    }
}