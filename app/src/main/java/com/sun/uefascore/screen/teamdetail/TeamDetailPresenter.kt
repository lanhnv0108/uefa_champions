package com.sun.uefascore.screen.teamdetail

import com.sun.uefascore.data.model.PlayerDetail
import com.sun.uefascore.data.model.TeamDetail
import com.sun.uefascore.data.source.remote.OnFetchDataJsonListener
import com.sun.uefascore.data.source.repository.TeamRepository

class TeamDetailPresenter(
    private val teamRepository: TeamRepository
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
}
