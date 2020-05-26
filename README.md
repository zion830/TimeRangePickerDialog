# RangeTimePickerDialog

[![](https://jitpack.io/v/zion830/RangeTimePickerDialog.svg)](https://jitpack.io/#zion830/RangeTimePickerDialog)


Custom dialog for selecting the time range on Android.

## How To Start
1. Add it in your root build.gradle at the end of repositories:
```
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
```
2. Add the dependency. The latest version is `1.0`
```
dependencies {
    implementation "com.github.zion830:RangeTimePickerDialog:$version_code"
}
```
## Usage
- Show TimeRangePickerDialog
```
TimeRangePickerDialog.newInstance(object : OnTimeRangeSelectedListener {
    override fun onTimeSelected(startHour: Int, startMin: Int, endHour: Int, endMin: Int) {
        // Use selected time range
    }
}).show(supportFragmentManager
```
- Convert onTimeSelected values to readable string (Ex. AM 10:30 - PM 1:00)
```
val dateString = DateTimeUtil.getReadableTimeString(startHour, endHour, startMin, endMin)
```
## Options
- `oneDayMode` : If oneDayMode is true, the `OK` button is disabled if the end time is earlier than the start time. The default value is true.

## License
[MIT](https://choosealicense.com/licenses/mit/)
