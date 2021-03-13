package com.sun.uefascore.data.source.local

interface OnFetchDataLocalListener<T> {

    fun onSuccess(data: T)
    fun onError(idMessage: Int)
}
