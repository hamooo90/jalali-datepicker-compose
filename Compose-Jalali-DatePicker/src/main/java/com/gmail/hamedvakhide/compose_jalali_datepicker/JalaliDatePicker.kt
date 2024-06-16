package com.gmail.hamedvakhide.compose_jalali_datepicker

import android.content.res.Configuration
import android.util.Log
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowDropDown
import androidx.compose.material.icons.outlined.KeyboardArrowLeft
import androidx.compose.material.icons.outlined.KeyboardArrowRight
import androidx.compose.material3.AlertDialogDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.gmail.hamedvakhide.compose_jalali_datepicker.ui.theme.backgroundColor
import com.gmail.hamedvakhide.compose_jalali_datepicker.ui.theme.selectedIconColor
import com.gmail.hamedvakhide.compose_jalali_datepicker.ui.theme.textColor
import com.gmail.hamedvakhide.compose_jalali_datepicker.ui.theme.textColorHighlight
import com.gmail.hamedvakhide.compose_jalali_datepicker.util.FormatHelper
import com.gmail.hamedvakhide.compose_jalali_datepicker.util.PickerType

import ir.huri.jcal.JalaliCalendar

/**
 * Opens a Jalali DatePicker dialog with the given content.
 *
 * Example usage:
 *
 * @param openDialog Dialog will be visible as long as openDialog value is true.
 * @param initialDate Specify a date to be shown when dialog opens.
 * @param onSelectDay Called when a day is selected.
 * @param onConfirm Called when confirm button is clicked.
 * @param backgroundColor Background color of the dialog.
 * @param textColor Color of the text.
 * @param selectedIconColor Color of selected day (month, year) circular icon.
 * @param textColorHighlight Color of current day (month, year) text.
 * @param dropDownColor Color of the year and month drop-down text.
 * @param dayOfWeekLabelColor Color for the day of the week label text.
 * @param confirmBtnColor Color of confirm button.
 * @param cancelBtnColor Color of cancel button.
 * @param todayBtnColor Color of today button.
 * @param nextPreviousBtnColor Color of next and previous month button.
 * @param fontFamily changing font for all the text.
 *
 */

@Composable
fun JalaliDatePickerDialog(
    openDialog: MutableState<Boolean>,
    initialDate: JalaliCalendar? = null,
    onSelectDay: (JalaliCalendar) -> Unit,
    onConfirm: (JalaliCalendar) -> Unit,
    backgroundColor: Color = MaterialTheme.colorScheme.backgroundColor,
    textColor: Color = MaterialTheme.colorScheme.textColor,
    selectedIconColor: Color = MaterialTheme.colorScheme.selectedIconColor,
    textColorHighlight: Color = MaterialTheme.colorScheme.textColorHighlight,
    dropDownColor: Color = MaterialTheme.colorScheme.textColor,
    dayOfWeekLabelColor: Color = MaterialTheme.colorScheme.textColor,
    confirmBtnColor: Color = MaterialTheme.colorScheme.textColorHighlight,
    cancelBtnColor: Color = MaterialTheme.colorScheme.textColorHighlight,
    todayBtnColor: Color = MaterialTheme.colorScheme.textColorHighlight,
    nextPreviousBtnColor: Color = MaterialTheme.colorScheme.textColor,
    fontFamily: FontFamily = FontFamily.Default
) {
    if (openDialog.value) {
        Dialog(
            onDismissRequest = { openDialog.value = false },
//            properties = DialogProperties(usePlatformDefaultWidth = false)
        ) {

            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxSize()
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null,
                    ) {
                        // same action as in onDismissRequest
                        openDialog.value = false
                    }
            ) {
                // content
                Surface(
                    modifier = Modifier
                        .wrapContentWidth()
                        .wrapContentHeight(),
                    shape = MaterialTheme.shapes.large,
                    tonalElevation = AlertDialogDefaults.TonalElevation,

                    ) {
                    JalaliCalendarView(
                        openDialog = openDialog,
                        initialDate = initialDate,
                        onSelectDay = {
                            onSelectDay(it)
//                            Log.d("DAGDAG", "onSelect: ${it.day} ${it.monthString} ${it.year}")
                        },
                        onConfirm = {
                            onConfirm(it)
//                            Log.d("DAGDAG", "onConfirm: ${it.day} ${it.monthString} ${it.year}")
                        },
                        backgroundColor = backgroundColor,
                        dayOfWeekLabelColor = dayOfWeekLabelColor,
                        dropDownColor = dropDownColor,
                        selectedIconColor = selectedIconColor,
                        textColorHighlight = textColorHighlight,
                        textColor = textColor,
                        cancelBtnColor = cancelBtnColor,
                        confirmBtnColor = confirmBtnColor,
                        todayBtnColor = todayBtnColor,
                        nextPreviousBtnColor = nextPreviousBtnColor,
                        fontFamily = fontFamily

                    )
                }
            }

        }

    }
}


@Composable
fun JalaliCalendarView(
//        modifier: Modifier = Modifier,
    initialDate: JalaliCalendar?,
    openDialog: MutableState<Boolean>,
    onSelectDay: (JalaliCalendar) -> Unit,
    onConfirm: (JalaliCalendar) -> Unit,
    backgroundColor: Color,
    textColor: Color,
    selectedIconColor: Color,
    textColorHighlight: Color,
    dropDownColor: Color,
    dayOfWeekLabelColor: Color,
    confirmBtnColor: Color,
    cancelBtnColor: Color,
    todayBtnColor: Color,
    nextPreviousBtnColor: Color,
    fontFamily: FontFamily
) {
    var iconSize: Dp by remember {
        mutableStateOf(43.dp)
    }

    var weekDaysLabelPadding: Dp by remember {
        mutableStateOf(18.dp)
    }
    var yearSelectorHeight: Dp by remember {
        mutableStateOf(280.dp)
    }

    var jalali by remember {
        mutableStateOf(initialDate ?: JalaliCalendar())
//        mutableStateOf(JalaliCalendar())
    }
    val today = JalaliCalendar()
    var selectedDate: JalaliCalendar? by remember {
        mutableStateOf(initialDate)
    }

    var pickerType: PickerType by remember {
        mutableStateOf(PickerType.Day)
    }

//        val coroutineScope = rememberCoroutineScope()
    val configuration = LocalConfiguration.current
    when (configuration.orientation) {
        Configuration.ORIENTATION_LANDSCAPE -> {
            Log.d("DAGDAG", "CalendarView: Landscape")
            iconSize = 32.dp
            weekDaysLabelPadding = 9.dp
            yearSelectorHeight = 230.dp
        }

        else -> {
            Log.d("DAGDAG", "CalendarView: Portrait")
        }
    }


    Column(
        modifier = Modifier
            .background(color = backgroundColor)
            .animateContentSize()
    ) {
        var firstJomeh = 0
        firstJomeh = 7 - JalaliCalendar(jalali.year, jalali.month, 1).dayOfWeek
        if (JalaliCalendar(jalali.year, jalali.month, 1).dayOfWeek == 7)
            firstJomeh = 7

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 4.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (pickerType == PickerType.Day) {
                IconButton(
                    onClick = {
                        jalali = if (jalali.month != 12) {
                            JalaliCalendar(jalali.year, jalali.month + 1, 1)
                        } else {
                            JalaliCalendar(jalali.year + 1, 1, 1)
                        }
                    },
                    modifier = Modifier.size(iconSize),
//                    colors = IconButtonDefaults.filledIconButtonColors(containerColor = MaterialTheme.colorScheme.dialogNavigationButton)
                ) {
                    Icon(
                        imageVector = Icons.Outlined.KeyboardArrowLeft,
                        contentDescription = "",
                        tint = nextPreviousBtnColor
                    )
                }
            } else {
                FilledIconButton(
                    onClick = { },
                    Modifier
                        .size(iconSize)
                        .alpha(0f),
                    colors = IconButtonDefaults.filledIconButtonColors(containerColor = Color.Transparent)
                ) {
                    Text(text = "X")
                }
            }

            TextButton(
                onClick = {
                    pickerType = if (pickerType != PickerType.Year)
                        PickerType.Year
                    else
                        PickerType.Day
                },
                modifier = Modifier.padding(0.dp)
            ) {
                Text(
                    text = FormatHelper.toPersianNumber(jalali.year.toString()),
                    color = dropDownColor,
                    fontFamily = fontFamily
                )
                Icon(
                    imageVector = Icons.Outlined.ArrowDropDown,
                    contentDescription = "",
                    tint = dropDownColor
                )
            }

            TextButton(onClick = {
                pickerType = if (pickerType != PickerType.Month)
                    PickerType.Month
                else
                    PickerType.Day
            }) {
                Text(text = jalali.monthString, color = dropDownColor, fontFamily = fontFamily)
                Icon(
                    imageVector = Icons.Outlined.ArrowDropDown,
                    contentDescription = "",
                    tint = dropDownColor
                )
            }

            if (pickerType == PickerType.Day) {
                IconButton(
                    onClick = {
                        jalali = if (jalali.month != 1) {
                            JalaliCalendar(jalali.year, jalali.month - 1, 1)
                        } else {
                            JalaliCalendar(jalali.year - 1, 12, 1)
                        }
                    },
                    modifier = Modifier.size(iconSize)
//                    colors = IconButtonDefaults.filledIconButtonColors(containerColor = MaterialTheme.colorScheme.dialogNavigationButton)
                ) {
                    Icon(
                        imageVector = Icons.Outlined.KeyboardArrowRight,
                        contentDescription = "",
                        tint = nextPreviousBtnColor
                    )
                }
            } else {
                FilledIconButton(
                    onClick = { },
                    Modifier
                        .size(iconSize)
                        .alpha(0f),
                    colors = IconButtonDefaults.filledIconButtonColors(containerColor = Color.Transparent)
                ) {
                    Text(text = "X")
                }
            }

        }

        when (pickerType) {
            PickerType.Day -> {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = weekDaysLabelPadding),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    Text(text = "ج", color = dayOfWeekLabelColor, fontFamily = fontFamily)
                    Text(text = "پ", color = dayOfWeekLabelColor, fontFamily = fontFamily)
                    Text(text = "چ", color = dayOfWeekLabelColor, fontFamily = fontFamily)
                    Text(text = "س", color = dayOfWeekLabelColor, fontFamily = fontFamily)
                    Text(text = "د", color = dayOfWeekLabelColor, fontFamily = fontFamily)
                    Text(text = "ی", color = dayOfWeekLabelColor, fontFamily = fontFamily)
                    Text(text = "ش", color = dayOfWeekLabelColor, fontFamily = fontFamily)
                }

                var jomeh = firstJomeh
                var weeksRowInMonth = 0
                while (true) {
                    weeksRowInMonth++
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 4.dp),
                        horizontalArrangement = Arrangement.SpaceAround
                    ) {
                        var day = jomeh
                        for (i in 7 downTo 1) {
                            if (day > 0 && day <= jalali.monthLength) {
                                val selectDay = day //

                                FilledIconButton(
                                    onClick = {
                                        selectedDate =
                                            JalaliCalendar(jalali.year, jalali.month, selectDay)
                                        onSelectDay(selectedDate!!)
                                    },
                                    Modifier.size(iconSize),
                                    colors = IconButtonDefaults.filledIconButtonColors(
                                        containerColor = if (selectedDate != null && day == selectedDate!!.day && jalali.year == selectedDate!!.year && jalali.month == selectedDate!!.month)
                                            selectedIconColor
                                        else
                                            Color.Transparent
                                    )
                                ) {

                                    Text(
                                        text = FormatHelper.toPersianNumber(day.toString()),
                                        color = if (day == today.day && jalali.year == today.year && jalali.month == today.month) {
                                            textColorHighlight
                                        } else {
                                            textColor

                                        },
                                        fontFamily = fontFamily
                                    )
                                }

                            } else {
                                FilledIconButton(
                                    onClick = {},
                                    Modifier
                                        .size(iconSize)
                                        .alpha(0f),
                                    colors = IconButtonDefaults.filledIconButtonColors(
                                        containerColor = Color.Transparent
                                    )
                                ) {
                                    Text(
                                        text = day.toString(), fontFamily = fontFamily
                                    )
                                }
                            }
                            day--
                        }
                    }
                    if (jomeh >= jalali.monthLength)
                        break
                    jomeh += 7
                }

            }

            PickerType.Month -> {

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 4.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        color = MaterialTheme.colorScheme.textColor,
                        text = "انتخاب ماه",
                        fontFamily = fontFamily
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 4.dp),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    TextButton(onClick = {
                        jalali = JalaliCalendar(jalali.year, 4, 1)
                        pickerType = PickerType.Day
                    }) {
                        Text(
                            text = "تیر",
                            color = monthTextColorFun(4, jalali, textColorHighlight, textColor),
                            fontFamily = fontFamily
                        )
                    }
                    TextButton(onClick = {
                        jalali = JalaliCalendar(jalali.year, 3, 1)
                        pickerType = PickerType.Day
                    }) {
                        Text(
                            text = "خرداد",
                            color = monthTextColorFun(3, jalali, textColorHighlight, textColor),
                            fontFamily = fontFamily
                        )
                    }
                    TextButton(onClick = {
                        jalali = JalaliCalendar(jalali.year, 2, 1)
                        pickerType = PickerType.Day
                    }) {
                        Text(
                            text = "اردیبهشت",
                            color = monthTextColorFun(2, jalali, textColorHighlight, textColor),
                            fontFamily = fontFamily
                        )
                    }
                    TextButton(onClick = {
                        jalali = JalaliCalendar(jalali.year, 1, 1)
                        pickerType = PickerType.Day
                    }) {
                        Text(
                            text = "فروردین",
                            color = monthTextColorFun(1, jalali, textColorHighlight, textColor),
                            fontFamily = fontFamily
                        )
                    }
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 4.dp),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    TextButton(onClick = {
                        jalali = JalaliCalendar(jalali.year, 8, 1)
                        pickerType = PickerType.Day
                    }) {
                        Text(
                            text = "آبان",
                            color = monthTextColorFun(8, jalali, textColorHighlight, textColor),
                            fontFamily = fontFamily
                        )
                    }
                    TextButton(onClick = {
                        jalali = JalaliCalendar(jalali.year, 7, 1)
                        pickerType = PickerType.Day
                    }) {
                        Text(
                            text = "مهر",
                            color = monthTextColorFun(7, jalali, textColorHighlight, textColor),
                            fontFamily = fontFamily
                        )
                    }
                    TextButton(onClick = {
                        jalali = JalaliCalendar(jalali.year, 6, 1)
                        pickerType = PickerType.Day
                    }) {
                        Text(
                            text = "شهریور",
                            color = monthTextColorFun(6, jalali, textColorHighlight, textColor),
                            fontFamily = fontFamily
                        )
                    }
                    TextButton(onClick = {
                        jalali = JalaliCalendar(jalali.year, 5, 1)
                        pickerType = PickerType.Day
                    }) {
                        Text(
                            text = "مرداد",
                            color = monthTextColorFun(5, jalali, textColorHighlight, textColor),
                            fontFamily = fontFamily
                        )
                    }
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 4.dp),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    TextButton(onClick = {
                        jalali = JalaliCalendar(jalali.year, 12, 1)
                        pickerType = PickerType.Day
                    }) {
                        Text(
                            text = "اسفند",
                            color = monthTextColorFun(12, jalali, textColorHighlight, textColor)
                        )
                    }
                    TextButton(onClick = {
                        jalali = JalaliCalendar(jalali.year, 11, 1)
                        pickerType = PickerType.Day
                    }) {
                        Text(
                            text = "بهمن",
                            color = monthTextColorFun(11, jalali, textColorHighlight, textColor),
                            fontFamily = fontFamily
                        )
                    }
                    TextButton(onClick = {
                        jalali = JalaliCalendar(jalali.year, 10, 1)
                        pickerType = PickerType.Day
                    }) {
                        Text(
                            text = "دی",
                            color = monthTextColorFun(10, jalali, textColorHighlight, textColor),
                            fontFamily = fontFamily
                        )
                    }
                    TextButton(onClick = {
                        jalali = JalaliCalendar(jalali.year, 9, 1)
                        pickerType = PickerType.Day
                    }) {
                        Text(
                            text = "آذر",
                            color = monthTextColorFun(9, jalali, textColorHighlight, textColor),
                            fontFamily = fontFamily
                        )
                    }
                }
            }

            PickerType.Year -> {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 4.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        modifier = Modifier.padding(vertical = 8.dp),
                        color = MaterialTheme.colorScheme.textColor,
                        text = "انتخاب سال",
                        fontFamily = fontFamily
                    )
                }
                val scrollState =
                    rememberLazyListState(initialFirstVisibleItemIndex = jalali.year - 2)
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(yearSelectorHeight)
                        .padding(horizontal = 4.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    state = scrollState
                ) {
                    items(3000) { index ->
                        Divider()
                        Box(modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                jalali = JalaliCalendar(index, jalali.month, 1)
                                pickerType = PickerType.Day
                            }
                            .padding(vertical = 4.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(

                                text = FormatHelper.toPersianNumber(index.toString()),
                                fontSize = 30.sp,
                                color = yearTextColorFun(
                                    jalali.year,
                                    index,
                                    textColorHighlight,
                                    textColor
                                ),
                                fontFamily = fontFamily
                            )
                        }
                    }
                }
            }
        }


        if (pickerType == PickerType.Day) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row() {
                    TextButton(
                        modifier = Modifier.padding(horizontal = 8.dp),
                        enabled = selectedDate != null,
                        onClick = {
                            onConfirm(selectedDate!!)
                            openDialog.value = false
                        }) {
                        if (selectedDate == null)
                            Text(text = "تایید", fontFamily = fontFamily)
                        else
                            Text(text = "تایید", color = confirmBtnColor, fontFamily = fontFamily)
                    }
                    TextButton(
                        modifier = Modifier.padding(horizontal = 8.dp),
                        onClick = { openDialog.value = false }) {
                        Text(text = "انصراف", color = cancelBtnColor, fontFamily = fontFamily)
                    }
                }

                TextButton(
                    modifier = Modifier
                        .padding(horizontal = 8.dp)
                        .alpha(
//                            if (pickerType != PickerType.Day)
//                                0f
//                            else
                            if (selectedDate != today || jalali.year != today.year || jalali.month != today.month)
                                1f
                            else 0f
                        ),
                    onClick = {
                        val tempToday = JalaliCalendar()
                        jalali = JalaliCalendar(tempToday.year, tempToday.month, 1)
                        selectedDate = JalaliCalendar()
                        onSelectDay(selectedDate!!)
                    }
                ) {
                    Text(text = "امروز", color = todayBtnColor, fontFamily = fontFamily)
                }
            }
        }
    }
}


@Composable
fun monthTextColorFun(
    currentMonth: Int,
    jalali: JalaliCalendar,
    textColorHighlight: Color,
    textColor: Color
): Color {
    return if (jalali.month == currentMonth) {
        textColorHighlight
    } else {
        textColor
    }
}

@Composable
fun yearTextColorFun(
    currentYear: Int,
    yearIndex: Int,
    textColorHighlight: Color,
    textColor: Color
): Color {
    return if (currentYear == yearIndex) {
        textColorHighlight
    } else {
        textColor
    }
}