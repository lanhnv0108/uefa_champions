package com.lanh.uefachampions.utils

import android.app.Activity
import android.content.Context
import android.os.AsyncTask
import android.os.SystemClock
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide

fun ImageView.loadImageUrl(url: String) {
    loadUrl(url)
}

fun ImageView.loadUrl(url: String) {
    Glide.with(context)
        .load(url)
        .centerCrop()
        .into(this)
}

fun Fragment.hideKeyboard() {
    view?.let { activity?.hideKeyboard(it) }
}

fun Context.hideKeyboard(view: View) {
    val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
}

inline fun <reified B : ViewDataBinding> inflateView(
    @LayoutRes layoutRes: Int,
    parent: ViewGroup
): B {
    return DataBindingUtil.inflate(
        LayoutInflater.from(parent.context), layoutRes, parent, false
    )
}

fun View.safeClick(blockInMillis: Long = 1000, onClick: (View) -> Unit) {
    var lastClickTime: Long = 0
    this.setOnClickListener {
        if (SystemClock.elapsedRealtime() - lastClickTime < blockInMillis) return@setOnClickListener
        lastClickTime = SystemClock.elapsedRealtime()
        onClick(this)
    }
}
