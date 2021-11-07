package com.lanh.uefachampions.screen.base

import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import com.lanh.uefachampions.utils.loadUrl

object BindingAdapter {
@JvmStatic
@BindingAdapter("loadImageUrl")
fun AppCompatImageView.loadImageUrl(url : String?) {
    if (url != null) {
        this.loadUrl(url)
    }
}
}