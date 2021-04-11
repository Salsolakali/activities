package com.example.activities.core.extensions

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

fun String.toDate(string: String): Date {
    val pattern = "EEE MMM dd HH:mm:ss 'UTC' yyyy"
    val dateFormat = SimpleDateFormat(pattern, Locale.ENGLISH)
    try {
        return dateFormat.parse(string)
    } catch (e: ParseException) {
        e.printStackTrace()
    }
    return Date()
}

fun fiveDaysBefore(): Date {
    var calendar = Calendar.getInstance()
    calendar.add(Calendar.DATE, -5)
    return calendar.time
}

fun toDateView(date: Date): String {
    var calendar = Calendar.getInstance()
    calendar.time = date
    return "${calendar.get(Calendar.DAY_OF_MONTH)} - ${calendar.get(Calendar.MONTH)}"
}