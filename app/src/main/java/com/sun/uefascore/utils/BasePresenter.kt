package com.sun.uefascore.utils

interface BasePresenter<T> {

    fun onStart()
    fun onStop()
    fun setView(view: T?)
}
