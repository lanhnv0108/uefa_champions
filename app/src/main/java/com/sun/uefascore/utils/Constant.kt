package com.sun.uefascore.utils

import com.sun.uefascore.BuildConfig

object Constant {
    const val BASE_URL = "https://api-football-v1.p.rapidapi.com/v3/"
    const val BASE_API_KEY = "?api_key=" + BuildConfig.API_KEY
    const val BASE_DATE = "?date="
    const val BASE_LEAGUE = "&league=2"
    const val BASE_SEASON = "&season="
    const val DAY_FORMAT = "yyyy-MM-dd"
}
