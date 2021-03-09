package com.sun.uefascore.data.model

data class StandingGroup(val standings: MutableList<Standing>?)

object StandingGroupEntry {
    const val STANDING_GROUP = "standings"
}
