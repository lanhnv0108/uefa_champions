package com.lanh.uefachampions.data.source.remote.fetchjson

import android.os.AsyncTask
import com.lanh.uefachampions.data.source.remote.OnFetchDataJsonListener
import com.lanh.uefachampions.utils.TypeModel

class GetJsonFromUrlWithGson<T> constructor(
    private val listener: OnFetchDataJsonListener<T>,
    private val typeMode: TypeModel
) : AsyncTask<String?, Unit?, String>() {
    private val parseStringToObject by lazy { ParseStringToObject() }
    private var exception: Exception? = null

    override fun doInBackground(vararg params: String?): String {
        var data = ""
        try {
            params[0]?.let { data = ParseDataWithJson().getJsonFromUrl(it) }
        } catch (e: Exception) {
            exception = e
        }
        return data
    }

    override fun onPostExecute(result: String) {
        super.onPostExecute(result)
        if (result.isNotBlank()) {
            @Suppress("UNCHECKED_CAST")
            val data = parseStringToObject.passGsonToObject(result, typeMode) as? T
            (data?.let { listener.onSuccess(it) })
        } else {
            listener.onError(exception)
        }
    }
}
