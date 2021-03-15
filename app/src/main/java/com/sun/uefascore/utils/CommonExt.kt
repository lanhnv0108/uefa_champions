package com.sun.uefascore.utils

import android.os.AsyncTask
import android.widget.ImageView

fun ImageView.loadImageUrl(url: String) {
    LoadImageUrl {
        this.setImageBitmap(it)
    }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, url)
}
