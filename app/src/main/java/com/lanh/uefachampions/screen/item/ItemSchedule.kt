package com.lanh.uefachampions.screen.item

import android.os.Parcelable
import com.lanh.uefachampions.data.model.GoalsData
import com.lanh.uefachampions.data.model.TeamDetailsData
import com.lanh.uefachampions.screen.base.ItemRecyclerview
import kotlinx.android.parcel.Parcelize
import org.joda.time.DateTime

data class ItemTextTitleSchedule(val time: DateTime?) : ItemRecyclerview() {
    fun getTimeString(): String {
        return when {
            time == null -> "các ngày trước đó"
            time.withTimeAtStartOfDay().isEqual(DateTime.now().withTimeAtStartOfDay()) -> "hôm nay"
            else -> time.toString("dd/MM/yyyy")
        }
    }
}

@Parcelize
data class ItemTeamMatchSchedule(
    val id: Int,
    val home: TeamDetailsData?,
    val away: TeamDetailsData?,
    val time: String?,
    val goal: GoalsData?,
) : ItemRecyclerview(), Parcelable