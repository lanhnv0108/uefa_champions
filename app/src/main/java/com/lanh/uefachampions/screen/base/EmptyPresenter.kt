package com.lanh.uefachampions.screen.base

import com.lanh.uefachampions.utils.BasePresenter

class EmptyPresenter : BasePresenter<Unit> {
    override fun onStart() {}

    override fun onStop() {}

    override fun setView(view: Unit?) {}
}
