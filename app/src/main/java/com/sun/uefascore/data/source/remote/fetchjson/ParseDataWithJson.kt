package com.sun.uefascore.data.source.remote.fetchjson

import com.sun.uefascore.BuildConfig
import com.sun.uefascore.data.model.FixtureEntry
import com.sun.uefascore.utils.TypeModel
import org.json.JSONArray
import org.json.JSONObject
import java.io.BufferedInputStream
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class ParseDataWithJson {

    @Throws(Exception::class)
    fun getJsonFromUrl(urlString: String): String {
        val url = URL(urlString)
        val httpURLConnect = (url.openConnection() as HttpURLConnection).apply {
            requestMethod = METHOD_GET
            connectTimeout = TIME_OUT
            readTimeout = TIME_OUT
            setRequestProperty(HEADER_API_KEY, BuildConfig.API_KEY)
            connect()
        }
        val stringBuilder = StringBuilder()
        if (httpURLConnect.responseCode == 200) {
            val bufferedInputStream = BufferedInputStream(httpURLConnect.inputStream) as InputStream
            val bufferedReader = BufferedReader(InputStreamReader(bufferedInputStream.buffered()))
            var line: String?
            while (bufferedReader.readLine().also { line = it } != null) {
                stringBuilder.append(line)
            }
            bufferedInputStream.close()
            bufferedReader.close()
            httpURLConnect.disconnect()
        }
        return stringBuilder.toString()
    }

    fun parseJson(jsonString: String, typeModel: TypeModel): Any? =
        try {
            when (typeModel) {
                TypeModel.FIXTURE -> {
                    parseJsonToList(
                        JSONObject(jsonString).getJSONArray(FixtureEntry.RESPONSE),
                        typeModel
                    )
                }
                else -> null
            }
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }

    @Throws(Exception::class)
    private fun parseJsonToObject(jsonObject: JSONObject?, typeModel: TypeModel): Any? {
        val parseJsonToModel = ParseJsonToModel()
        return when (typeModel) {
            TypeModel.FIXTURE -> {
                parseJsonToModel.parseJsonToFixture(jsonObject)
            }
            else -> null
        }
    }

    @Throws(Exception::class)
    private fun parseJsonToList(jsonArray: JSONArray?, typeModel: TypeModel): Any {
        val data = mutableListOf<Any?>()
        for (i in 0 until (jsonArray?.length() ?: 0)) {
            val jsonObject = jsonArray?.getJSONObject(i)
            data.add(parseJsonToObject(jsonObject, typeModel))
        }
        return data.filterNotNull()
    }

    companion object {
        private const val HEADER_API_KEY = "x-rapidapi-key"
        private const val TIME_OUT = 20000
        private const val METHOD_GET = "GET"
    }
}
