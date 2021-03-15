package com.sun.uefascore.screen.teamdetail

import com.sun.uefascore.data.model.PlayerDetail
import com.sun.uefascore.data.model.TeamDetail
import com.sun.uefascore.data.source.local.OnFetchDataLocalListener
import com.sun.uefascore.data.source.remote.OnFetchDataJsonListener
import com.sun.uefascore.data.source.repository.FavoriteRepository
import com.sun.uefascore.data.source.repository.TeamRepository

class TeamDetailPresenter(
    private val teamRepository: TeamRepository,
    private val favoriteRepository: FavoriteRepository
) : TeamDetailContract.Presenter {

    private var view: TeamDetailContract.View? = null

    override fun onStart() = Unit

    override fun onStop() = Unit

    override fun setView(view: TeamDetailContract.View?) {
        this.view = view
    }

    override fun onGetTeamById(season: String, idTeam: String) {
        teamRepository.getTeamById(
            season,
            idTeam,
            object : OnFetchDataJsonListener<TeamDetail> {

                override fun onSuccess(data: TeamDetail) {
                    view?.onGetTeamByIdSuccess(data)
                }

                override fun onError(exception: Exception?) {
                    exception?.let { view?.onError(it) }
                }
            })
    }

    override fun onGetPlayersById(season: String, idTeam: String) {
        teamRepository.getPlayersById(
            season,
            idTeam,
            object : OnFetchDataJsonListener<MutableList<PlayerDetail>> {

                override fun onSuccess(data: MutableList<PlayerDetail>) {
                    view?.onGetPlayersByIdSuccess(data)
                }

                override fun onError(exception: Exception?) {
                    exception?.let { view?.onError(it) }
                }
            })
    }

    override fun onGetTeamLocal(idTeam: String) {
        favoriteRepository.getTeamDetail(
            idTeam,
            object : OnFetchDataLocalListener<TeamDetail> {

                override fun onSuccess(data: TeamDetail) {
                    view?.onGetTeamLocalSuccess(data)
                }

                override fun onError(idMessage: Int) = Unit
            })
    }

    override fun onGetPlayersLocal(idTeam: String) {
    }

    override fun onSaveTeamLocal(teamDetail: TeamDetail) {
        favoriteRepository.saveTeamDetail(
            teamDetail,
            object : OnFetchDataLocalListener<Long> {

                override fun onSuccess(data: Long) {
                    view?.onSaveTeamLocalSuccess()
                }

                override fun onError(idMessage: Int) {
                    view?.onFailed(idMessage)
                }
            })
    }

    override fun onSavePlayersLocal(playerDetails: MutableList<PlayerDetail>) {
        favoriteRepository.savePlayerDetails(
            playerDetails,
            object : OnFetchDataLocalListener<Long> {

                override fun onSuccess(data: Long) {
                    view?.onSavePlayersLocalSuccess()
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
        favoriteRepository.deletePlayerDetailsByIdTeam(
            idTeam,
            object : OnFetchDataLocalListener<Int> {

                override fun onSuccess(data: Int) {
                    view?.onSavePlayersLocalSuccess()
                }

                override fun onError(idMessage: Int) {
                    view?.onFailed(idMessage)
                }
            })
    }
}
