package com.gmail.hamedvakhide.jalalidatepicker

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gmail.hamedvakhide.compose_jalali_datepicker.JalaliDatePickerDialog
import ir.huri.jcal.JalaliCalendar

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val openDialog = remember { mutableStateOf(false) }

            var selectedDate by remember {
                mutableStateOf<JalaliCalendar?>(null)
            }

            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Button(onClick = { openDialog.value = true }) {
                    Text(text = "open dialog")
                }
                Text(
                    modifier = Modifier.padding(vertical = 8.dp),
                    text = "Selected Date: ${selectedDate?.year}/${selectedDate?.month}/${selectedDate?.day}",
                    fontSize = 22.sp,
                )
            }



            JalaliDatePickerDialog(
                openDialog = openDialog,
                fontFamily = FontFamily(
                    Font(R.font.bziba_0)
                ),
                disableBeforeDate = JalaliCalendar(1403,10,15),
                disableAfterDate = JalaliCalendar(1404, 9, 30),
                initialDate = JalaliCalendar(1403, 11, 2),
                onSelectDay = {
                    Log.d("Date", "onSelect: ${it.day} ${it.monthString} ${it.year}")
                },
                onConfirm = {
                    Log.d("Date", "onConfirm: ${it.day} ${it.monthString} ${it.year}")
                    selectedDate = it
                },
//                backgroundColor = Color.Yellow,
//                textColor = Color.Blue
//                fontSize = 19.sp,
            )
        }
    }
}

