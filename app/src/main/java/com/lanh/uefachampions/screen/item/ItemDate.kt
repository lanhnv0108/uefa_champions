package com.lanh.uefachampions.screen.item

import com.lanh.uefachampions.utils.datetime.convertDayOfWeekToString
import org.joda.time.DateTime
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.*

data class ItemDate(var date: DateTime, var isCheck: Boolean = false) {
    fun getDayOfWeek(): String = date.convertDayOfWeekToString()
    fun getDayOfMonth(): String = DecimalFormat(
        "00",
        DecimalFormatSymbols.getInstance(Locale.getDefault())
    ).format(date.dayOfMonth)

    fun convertDateTimeToString(): String = date.toString("yyyy/MM/dd")
}
