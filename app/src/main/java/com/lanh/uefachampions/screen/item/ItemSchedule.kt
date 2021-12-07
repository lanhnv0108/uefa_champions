package com.lanh.uefachampions.screen.item

import com.lanh.uefachampions.data.model.Team
import org.joda.time.DateTime

sealed class ItemSchedule

data class ItemTextTitleSchedule(val time: DateTime?) : ItemSchedule() {
    fun getTimeString(): String {
        return when {
            time == null -> "các ngày trước đó"
            time.withTimeAtStartOfDay().isEqual(DateTime.now().withTimeAtStartOfDay()) -> "hôm nay"
            else -> time.toString("dd/MM/yyyy")
        }
    }
}

data class ItemTeamMatchSchedule(
    val id: Int,
    val teamOne: Team?,
    val teamTwo: Team?,
    val time: String?,
    val goalTeamOne: String?,
    val goalTeamTwo: String?
) : ItemSchedule()