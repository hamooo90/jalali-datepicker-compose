package com.gmail.hamedvakhide.compose_jalali_datepicker.util

object FormatHelper {
    private val persianNumbers = arrayOf("۰", "۱", "۲", "۳", "۴", "۵", "۶", "۷", "۸", "۹")
    fun toPersianNumber(text: String): String {
        if (text.isEmpty()) {
            return ""
        }
        var out = ""
        val length = text.length
        for (i in 0 until length) {
            val c = text[i]
            when (c) {
                in '0'..'9' -> {
                    val number = c.toString().toInt()
                    out += persianNumbers[number]
                }
                '٫' -> {
                    out += '،'
                }
                else -> {
                    out += c
                }
            }

//            return out;
        }
        return out
    }

}