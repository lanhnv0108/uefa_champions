package com.lanh.uefachampions.screen.item

import com.lanh.uefachampions.data.model.translateStaticsVN

data class InfoFixtureDetailItem(
    val numberOfShots: InfoMatchItem?,
    val goal: InfoMatchItem?,
    val totalShots: InfoMatchItem?,
    val yellowCard: InfoMatchItem?,
    val redCard: InfoMatchItem?
)


data class InfoMatchItem(val title: String?, val number: Pair<String, String>?) {
    val titleVN = translateStaticsVN[title]
}
