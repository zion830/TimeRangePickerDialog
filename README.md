# TimeRangePickerDialog

[![label1](https://jitpack.io/v/zion830/RangeTimePickerDialog.svg)](https://jitpack.io/#zion830/RangeTimePickerDialog)
![label2](https://img.shields.io/badge/API-21%2B-blue.svg?style=flat)

Custom dialog for selecting the time range on Android.

## How To Start
1. Add it in your root build.gradle at the end of repositories:
```gradle
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
```
2. Add the dependency. The latest version is `1.3`
```gradle
dependencies {
    implementation "com.github.zion830:RangeTimePickerDialog:$version_code"
}
```
## Usage
- Show TimeRangePickerDialog
```kotlin
TimeRangePickerDialog.Builder()
    .setTimeRange(10, 20, 16, 40)
    .setOnTimeRangeSelectedListener { timeRange -> /* Use selected time range */ }
    .build()
    .show(supportFragmentManager)
```
## Options
#### TimeRangePickerDialog
| name| description|
|---|---|
|`timeRange`|First selected time. Default range is `${current hour}:00 ~ ${current hour + 1}:00`|
| `oneDayMode`| `OK` button is disabled if `end time` is earlier than `start time`. Default value is true.|
| `timeInterval`| Minute time interval. Default value is 10.|

#### TimeRange
| name| description|
|---|---|
|`startHour`|Selected start hour.|
|`startMinute`|Selected start minute.|
|`endHour`|Selected end hour.|
|`endMinute`|Selected end minute.|
| `readableTimeRange`| Return Time string like `AM 10:30 - PM 1:00`.|
| `isCorrectSequence`| Return whether `start time` is earlier than `end time`.|

## License
[MIT](https://choosealicense.com/licenses/mit/)
