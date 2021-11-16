package com.lanh.uefachampions.utils

fun Int?.toPoint(): String = when {
    this == null -> ""
    this < 0 -> this.toString()
    this >= 0 -> " $this"
    else -> ""
}