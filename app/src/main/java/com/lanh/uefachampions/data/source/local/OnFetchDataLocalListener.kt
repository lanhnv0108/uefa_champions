package com.lanh.uefachampions.data.source.local

interface OnFetchDataLocalListener<T> {

    fun onSuccess(data: T)
    fun onError(idMessage: Int)
}
