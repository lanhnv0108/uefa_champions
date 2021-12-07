package com.lanh.uefachampions.screen.base

import android.view.View
import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import com.lanh.uefachampions.utils.loadUrl

object BindingAdapter {
    @JvmStatic
    @BindingAdapter("loadImageUrl")
    fun AppCompatImageView.loadImageUrl(url: String?) {
        if (url != null) {
            this.loadUrl(url)
        }
    }

    @JvmStatic
    @BindingAdapter("isVisible")
    fun View.isVisible(isVisible: Boolean) {
        visibility = if (isVisible) View.VISIBLE else View.GONE
    }
}