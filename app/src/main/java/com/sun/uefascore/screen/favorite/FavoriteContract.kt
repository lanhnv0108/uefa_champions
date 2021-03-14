package com.sun.uefascore.screen.favorite

import com.sun.uefascore.utils.BasePresenter

interface FavoriteContract {

    interface View {

        fun onFailed(idMessage: Int)
    }

    interface Presenter : BasePresenter<View>
}
