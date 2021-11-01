package com.lanh.uefachampions.data.source.remote.fetchjson

import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.lanh.uefachampions.BuildConfig
import com.lanh.uefachampions.data.model.*
import com.lanh.uefachampions.utils.TypeModel
import java.lang.reflect.Type

class ParseStringToObject {
    private val gson by lazy {
        Gson()
    }

    fun passGsonToObject(result: String, typeModel: TypeModel): Any? {
        return try {
            when (typeModel) {
                TypeModel.FIXTURE -> {
                    val data =
                        convertJsonToObject<BaseDataApiFootBall<List<FixtureSeason>>>(
                            result,
                            object : TypeToken<BaseDataApiFootBall<List<FixtureSeason>>>() {}.type
                        )?.response
                    Log.e("data", "$data")
                    null
                }
                TypeModel.NEWS ->
                    convertJsonToObject<BaseDataApiNews<List<News>>>(
                        result,
                        object : TypeToken<BaseDataApiNews<List<News>>>() {}.type
                    )?.data
                else -> null
            }
        } catch (e: Throwable) {
            if (BuildConfig.DEBUG) Log.e("expection", e.message)
            null
        }
    }

    private fun <T> convertJsonToObject(result: String, type: Type): T? {
        return gson.fromJson<T>(result, type)
    }
}
