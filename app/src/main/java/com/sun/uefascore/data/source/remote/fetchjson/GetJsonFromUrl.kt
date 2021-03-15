package com.sun.uefascore.data.source.remote.fetchjson

import android.os.AsyncTask
import com.sun.uefascore.data.source.remote.OnFetchDataJsonListener
import com.sun.uefascore.utils.TypeModel

class GetJsonFromUrl<T> constructor(
    private val listener: OnFetchDataJsonListener<T>,
    private val typeMode: TypeModel
) : AsyncTask<String?, Unit?, String>() {

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
            val data = ParseDataWithJson().parseJson(result, typeMode)
            data?.let {
                @Suppress("UNCHECKED_CAST")
                listener.onSuccess(it as T)
            }
        } else {
            listener.onError(exception)
        }
    }
}
