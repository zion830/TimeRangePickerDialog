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
2. Add the dependency. The latest version is `1.1`
```
dependencies {
    implementation "com.github.zion830:RangeTimePickerDialog:$version_code"
}
```
## Usage
- Show TimeRangePickerDialog
```
TimeRangePickerDialog.Builder()
    .setTimeRange(10, 20, 16, 40)
    .setTimeInterval(20)
    .setOnDayMode(false)
    .setOnTimeRangeSelectedListener { /* Use selected time range */ }
    .build()
    .show(supportFragmentManager)
```
## Options
#### TimeRangePickerDialog
| name| description|
|---|---|
| `oneDayMode`| `OK` button is disabled if `end time` is earlier than `start time`. Default value is true.|
| `timeInterval`| Minute time interval. Default value is 10.|

#### TimeRange
| name| description|
|---|---|
| `readableTimeRange`| Time string like `AM 10:30 - PM 1:00`|
| `isCorrectSequence`| Return whether `start time` is earlier than `end time`.|

## License
[MIT](https://choosealicense.com/licenses/mit/)
