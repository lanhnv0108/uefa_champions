package com.sun.uefascore.screen.favorite

import com.sun.uefascore.data.model.TeamDetail
import com.sun.uefascore.data.source.local.OnFetchDataLocalListener
import com.sun.uefascore.data.source.repository.FavoriteRepository

class FavoritePresenter(
    private val favoriteRepository: FavoriteRepository,
) : FavoriteContract.Presenter {

    private var view: FavoriteContract.View? = null

    override fun onGetFavorites() {
        favoriteRepository.getAllTeamDetail(object :
            OnFetchDataLocalListener<MutableList<TeamDetail>> {

            override fun onSuccess(data: MutableList<TeamDetail>) {
                view?.onGetFavoritesSuccess(data)
            }

            override fun onError(idMessage: Int) {
                view?.onFailed(idMessage)
            }
        })
    }

    override fun onDeleteTeamLocal(idTeam: String) {
        favoriteRepository.deleteTeamDetail(
            idTeam,
            object : OnFetchDataLocalListener<Int> {
                override fun onSuccess(data: Int) {
                    view?.onDeleteTeamLocalSuccess()
                }

                override fun onError(idMessage: Int) {
                    view?.onFailed(idMessage)
                }
            })
    }

    override fun onDeletePlayersLocal(idTeam: String) {
        favoriteRepository.deletePlayerDetailsByIdTeam(idTeam,
            object : OnFetchDataLocalListener<Int> {

                override fun onSuccess(data: Int) {
                    view?.onDeletePlayersLocalSuccess()
                }

                override fun onError(idMessage: Int) {
                    view?.onFailed(idMessage)
                }
            })
    }

    override fun onStart() = Unit

    override fun onStop() = Unit

    override fun setView(view: FavoriteContract.View?) {
        this.view = view
    }
}
