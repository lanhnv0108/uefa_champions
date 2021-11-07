package com.lanh.uefachampions.screen.teamdetail

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.lanh.uefachampions.R
import com.lanh.uefachampions.data.model.PlayerDetail
import com.lanh.uefachampions.data.model.TeamDetail
import com.lanh.uefachampions.data.source.local.TeamLocalDataSource
import com.lanh.uefachampions.data.source.repository.FavoriteRepository
import com.lanh.uefachampions.data.source.repository.TeamRepository
import com.lanh.uefachampions.screen.playerdetail.PlayerDetailFragment
import com.lanh.uefachampions.screen.teamdetail.adapter.PlayerAdapter
import com.lanh.uefachampions.utils.LoadImageUrl
import com.lanh.uefachampions.utils.OnFavoriteListener
import com.lanh.uefachampions.utils.OnItemRecyclerViewClickListener
import com.lanh.uefachampions.utils.loadUrl
import kotlinx.android.synthetic.main.fragment_team_detail.*

@Suppress("DEPRECATION")
class TeamDetailFragment : Fragment(), TeamDetailContract.View,
    OnItemRecyclerViewClickListener<PlayerDetail> {

    private var onFavoriteListener: OnFavoriteListener? = null
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
        imageViewLogo.loadUrl(teamDetail.logo.toString())
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

    override fun onGetPlayersLocalSuccess(playerDetails: MutableList<PlayerDetail>) {
        checkFavorite(true)
    }

    override fun onSaveTeamLocalSuccess() {
        checkFavorite(true)
    }

    override fun onSavePlayersLocalSuccess() {
        checkFavorite(true)
    }

    override fun onDeleteTeamLocalSuccess() {
        checkFavorite(false)
    }

    override fun onDeletePlayersLocalSuccess() {
        checkFavorite(false)
    }

    override fun onFailed(idMessage: Int) {
        checkFavorite(false)
    }

    override fun onItemClickListener(item: PlayerDetail?) {
        item?.player?.let {
            PlayerDetailFragment.newInstance(it)
                .show(this@TeamDetailFragment.childFragmentManager, null)
        }
    }

    fun registerFavoriteListener(onFavoriteListener: OnFavoriteListener) {
        this.onFavoriteListener = onFavoriteListener
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
                    presenter.onDeletePlayersLocal(id)
                    presenter.onDeleteTeamLocal(id)
                }
                onFavoriteListener?.onClickFavoriteListener()
            } else {
                teamDetail?.let { teamDetail ->
                    presenter.onSaveTeamLocal(teamDetail)
                }
                playerDetails.let { playerDetails ->
                    presenter.onSavePlayersLocal(playerDetails)
                }
                onFavoriteListener?.onClickFavoriteListener()
            }
        }
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private fun checkFavorite(isFavorite: Boolean) {
        if (isFavorite) {
            isCheck = isFavorite
            imageViewFavorite.setImageDrawable(
                context?.let { ContextCompat.getDrawable(it, R.drawable.ic_favorite_checked) }
            )
        } else {
            isCheck = isFavorite
            imageViewFavorite.setImageDrawable(
                context?.let { ContextCompat.getDrawable(it, R.drawable.ic_favorite_check) }
            )
        }
    }

    companion object {

        private const val BUNDLE_ID_TEAM = "BUNDLE_ID_TEAM"
        private const val BUNDLE_SEASON = "BUNDLE_SEASON"

        fun newInstance(idTeam: String, season: String?) =
            TeamDetailFragment().apply {
                arguments = bundleOf(
                    BUNDLE_ID_TEAM to idTeam,
                    BUNDLE_SEASON to season
                )
            }
    }
}
