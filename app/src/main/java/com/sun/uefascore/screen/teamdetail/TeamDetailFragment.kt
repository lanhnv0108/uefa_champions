package com.sun.uefascore.screen.teamdetail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import com.sun.uefascore.R
import com.sun.uefascore.data.model.PlayerDetail
import com.sun.uefascore.data.model.TeamDetail
import com.sun.uefascore.data.source.local.TeamLocalDataSource
import com.sun.uefascore.data.source.repository.FavoriteRepository
import com.sun.uefascore.data.source.repository.TeamRepository
import com.sun.uefascore.screen.playerdetail.PlayerDetailFragment
import com.sun.uefascore.screen.teamdetail.adapter.PlayerAdapter
import com.sun.uefascore.utils.LoadImageUrl
import com.sun.uefascore.utils.OnItemRecyclerViewClickListener
import kotlinx.android.synthetic.main.fragment_team_detail.*
import java.lang.Exception

class TeamDetailFragment : Fragment(), TeamDetailContract.View,
    OnItemRecyclerViewClickListener<PlayerDetail> {

    private val presenter by lazy {
        TeamDetailPresenter(
            TeamRepository.instance,
            FavoriteRepository.getInstance(
                TeamLocalDataSource.getInstance(requireContext())
            )
        )
    }
    private val adapter: PlayerAdapter by lazy {
        PlayerAdapter()
    }
    private var season: String? = null
    private var idTeam: String? = null
    private var isCheck = false
    private var teamDetail: TeamDetail? = null
    private var playerDetails = mutableListOf<PlayerDetail>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_team_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initData()
        handleEvents()
    }

    override fun onGetTeamByIdSuccess(teamDetail: TeamDetail) {
        textViewCountry?.text = teamDetail.country
        textViewFounded?.text = teamDetail.founded.toString()
        textViewNameTeam?.text = teamDetail.name
        LoadImageUrl {
            imageViewLogo?.setImageBitmap(it)
        }.execute(teamDetail.logo)
        this.teamDetail = teamDetail
    }

    override fun onGetPlayersByIdSuccess(playerDetails: MutableList<PlayerDetail>) {
        adapter.updateData(playerDetails)
        this.playerDetails = playerDetails
    }

    override fun onError(exception: Exception) {
        Toast.makeText(context, exception.message, Toast.LENGTH_SHORT).show()
    }

    override fun onGetTeamLocalSuccess(teamDetail: TeamDetail) {
        checkFavorite(true)
    }

    override fun onGetPlayersLocalSuccess(playerDetails: MutableList<PlayerDetail>) = Unit

    override fun onSaveTeamLocalSuccess() {
        checkFavorite(true)
    }

    override fun onSavePlayersLocalSuccess() = Unit

    override fun onDeleteTeamLocalSuccess() {
        checkFavorite(false)
    }

    override fun onDeletePlayersLocalSuccess() = Unit

    override fun onFailed(idMessage: Int) {
        Toast.makeText(context, resources.getString(id), Toast.LENGTH_SHORT).show()
        checkFavorite(false)
    }

    override fun onItemClickListener(item: PlayerDetail?) {
        item?.player?.let {
            PlayerDetailFragment.newInstance(it)
                .show(this@TeamDetailFragment.childFragmentManager, null)
        }
    }

    private fun initView() {
        recyclerViewPlayers.adapter = adapter
        adapter.registerItemRecyclerViewClickListener(this)
    }

    private fun initData() {
        bindDataBundle()
        season?.let { season ->
            idTeam?.let { idTeam ->
                presenter.apply {
                    onGetTeamById(season, idTeam)
                    onGetPlayersById(season, idTeam)
                    setView(this@TeamDetailFragment)
                    onGetTeamLocal(idTeam)
                }
            }
        }
    }

    private fun bindDataBundle() {
        arguments?.apply {
            getString(BUNDLE_SEASON).let {
                season = it
            }
            getString(BUNDLE_ID_TEAM).let {
                idTeam = it
            }
        }
    }

    private fun handleEvents() {
        imageViewBack.setOnClickListener {
            fragmentManager?.popBackStack()
        }
        imageViewFavorite.setOnClickListener {
            if (isCheck) {
                idTeam?.let { id ->
                    presenter.onDeleteTeamLocal(id)
                    presenter.onDeletePlayersLocal(id)
                }
            } else {
                teamDetail?.let { teamDetail ->
                    presenter.onSaveTeamLocal(teamDetail)
                }
                playerDetails?.let { playerDetails ->
                    presenter.onSavePlayersLocal(playerDetails)
                }
            }
        }
    }

    private fun checkFavorite(isFavorite: Boolean) {
        if (isFavorite) {
            isCheck = isFavorite
            imageViewFavorite.setImageDrawable(
                resources.getDrawable(R.drawable.ic_favorite_checked)
            )
        } else {
            isCheck = isFavorite
            imageViewFavorite.setImageDrawable(
                resources.getDrawable(R.drawable.ic_favorite_check)
            )
        }
    }

    companion object {

        private const val BUNDLE_ID_TEAM = "BUNDLE_ID_TEAM"
        private const val BUNDLE_SEASON = "BUNDLE_SEASON"

        fun newInstance(idTeam: String, season: String) =
            TeamDetailFragment().apply {
                arguments = bundleOf(
                    BUNDLE_ID_TEAM to idTeam,
                    BUNDLE_SEASON to season
                )
            }
    }
}
