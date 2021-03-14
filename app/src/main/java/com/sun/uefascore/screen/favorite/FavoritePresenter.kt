package com.sun.uefascore.screen.favorite

class FavoritePresenter : FavoriteContract.Presenter {

    private var view: FavoriteContract.View? = null

    override fun onStart() = Unit

    override fun onStop() = Unit

    override fun setView(view: FavoriteContract.View?) {
        this.view = view
    }
}
