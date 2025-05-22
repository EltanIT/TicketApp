package com.example.ticketsapp.presentation.utils

import android.os.Build
import androidx.annotation.RequiresApi
import java.text.SimpleDateFormat
import java.time.ZoneId
import java.util.Date
import java.util.Locale

@RequiresApi(Build.VERSION_CODES.O)
fun isSameDay(date1: java.util.Date, date2: java.util.Date): Boolean {
    val localDate1 = date1.toInstant().atZone(ZoneId.systemDefault()).toLocalDate()
    val localDate2 = date2.toInstant().atZone(ZoneId.systemDefault()).toLocalDate()
    return localDate1 == localDate2
}


fun dateToDateTimeString(date: Date): String{
    val formatter = SimpleDateFormat("HH:mm, d MMMM yyyy", Locale("ru"))
    return formatter.format(date)
}

fun dateToDateString(date: Date): String{
    val formatter = SimpleDateFormat("d MMMM", Locale("ru"))
    return formatter.format(date)
}