package com.gmail.hamedvakhide.compose_jalali_datepicker.util

sealed class PickerType{
    object Year: PickerType()
    object Month: PickerType()
    object Day: PickerType()
}
