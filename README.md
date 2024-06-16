# jalali-datepicker-compose
An Android library - built with jetpack compose - that offers Jalali/Persian calendar date picker dialog.
It's easy to use, quick to implement and can be customized according to your application color and theme.

[![Download](https://img.shields.io/jitpack/version/com.github.hamooo90/jalali-datepicker-compose)](https://jitpack.io/#hamooo90/jalali-datepicker-compose)

<img src="screenshot.png" alt="Library Image">

# Setup
Add the JitPack repository to settings.gradle file
```gradle
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        ...      
        maven { url 'https://jitpack.io' }
    }
}
```

Add the dependencies to app-level build.gradle file
```gradle
dependencies {
    ...
    implementation 'com.github.hamooo90:jalali-datepicker-compose:1.1.1'
    implementation 'ir.huri:JalaliCalendar:1.3.3'
}
```
This library uses another library called [JalaliCalendar](https://github.com/razeghi71/JalaliCalendar/tree/master "by razeghi71") to get, set and manipulate the dates. for more information on how to use it visit it's github page.

# How To Use

### Minimum Configuration
```kotlin
val openDialog = remember { mutableStateOf(false) }
Button(onClick = { openDialog.value = true }) {
    Text(text = "Open JalaliDatePicker")
}
JalaliDatePickerDialog(
    openDialog = openDialog,
    onSelectDay = { //it:JalaliCalendar
        Log.d("Date", "onSelect: ${it.day} ${it.monthString} ${it.year}")
    },
    onConfirm = {
        Log.d("Date", "onConfirm: ${it.day} ${it.monthString} ${it.year}")
    }
)
```

### With Customization
```kotlin
val openDialog = remember { mutableStateOf(false) }
Button(onClick = { openDialog.value = true }) {
    Text(text = "Open JalaliDatePicker")
}
JalaliDatePickerDialog(
    openDialog = openDialog,
    fontFamily = FontFamily(
                    Font(R.font.bziba_0)
    ),
    initialDate = JalaliCalendar(1402, 1, 2),
    onSelectDay = { //it:JalaliCalendar
        Log.d("Date", "onSelect: ${it.day} ${it.monthString} ${it.year}")
    },
    onConfirm = {
        Log.d("Date", "onConfirm: ${it.day} ${it.monthString} ${it.year}")
    },
    backgroundColor = Color.Yellow,
    textColor = Color.Blue
)
```

jalali datepicker colors can be changed with the use of these parameters
<img src="customization.png" alt="colors">

# Demo
<img src="gif.gif" width="300" />

# Credit 
Special Thanks to [JalaliCalendar](https://github.com/razeghi71/JalaliCalendar/tree/master "by razeghi71")

# Licence

    Copyright 2023 Hamed Vakhideh
    
    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at
    
        http://www.apache.org/licenses/LICENSE-2.0
    
    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
