package com.lanh.uefachampions.data.model

data class StandingGroup(val standings: MutableList<Standing>?) {
    val groupName = standings?.firstOrNull()?.group?.run {
        substring(lastIndex - 6, length)
    }
}

object StandingGroupEntry {
    const val STANDING_GROUP = "standings"
}
