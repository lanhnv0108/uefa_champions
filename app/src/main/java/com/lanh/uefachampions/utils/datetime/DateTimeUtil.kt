package com.lanh.uefachampions.utils.datetime

import com.lanh.uefachampions.screen.item.ItemDate
import org.joda.time.DateTime

val DAY_OF_WEEK = listOf("MO", "TU", "WE", "THU", "FI", "SA", "SU")

val MONTH_OF_YEAR = listOf(
    "JANUARY",
    "FEBRUARY",
    "MARCH",
    "APRIL",
    "MAY",
    "JUNE",
    "JULY",
    "AUGUST",
    "SEPTEMBER",
    "OCTOBER",
    "NOVEMBER",
    "DECEMBER"
)

fun DateTime.convertDayOfWeekToString() = DAY_OF_WEEK[dayOfWeek - 1]

fun DateTime.getListDateInMonth(): List<ItemDate> {
    val days = mutableListOf<ItemDate>()
    var day = withDayOfMonth(1)
    val dayFirstNextMonth = day.plusMonths(1)
    while (day.isBefore(dayFirstNextMonth)) {
        days.add(ItemDate(day, day == this))
        day = day.plusDays(1)
    }
    return days
}

fun DateTime.covertToMonthAndYear() = "${MONTH_OF_YEAR[monthOfYear - 1]}, $year"
