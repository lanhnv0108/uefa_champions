package com.lanh.uefachampions.screen.base

import com.lanh.uefachampions.R
import com.lanh.uefachampions.databinding.FragmentHomePageBinding
import com.lanh.uefachampions.screen.favorite.FavoriteFragment
import com.lanh.uefachampions.screen.fixtures.FixturesFragment
import com.lanh.uefachampions.screen.news.NewsFragment
import com.lanh.uefachampions.screen.standing.StandingFragment
import com.lanh.uefachampions.screen.topscorers.TopScorersFragment
import com.lanh.uefachampions.utils.*

class HomePageFragment : BaseFragment<FragmentHomePageBinding, EmptyPresenter>(),
    OnGetSeasonListener{
    override val layoutId: Int
        get() = R.layout.fragment_home_page

    override val presenter: EmptyPresenter by lazy { EmptyPresenter() }
    private val newsFragment = NewsFragment.newInstance()
    private val fixturesFragment = FixturesFragment.newInstance()
    private val standingFragment = StandingFragment.newInstance()
    private val topScorersFragment = TopScorersFragment.newInstance()
    private val favoriteFragment = FavoriteFragment.newInstance()

    override fun getSeason(season: String) {
        standingFragment.updateSeason(season)
        topScorersFragment.updateSeason(season)
        favoriteFragment.updateSeason(season)
    }

    override fun initView() {
        super.initView()
        registerListener()
        replaceFragment(fixturesFragment, R.id.viewPagerContainer, false)
        initBottomItem()
    }

    private fun registerListener() {
        fixturesFragment.apply {
            registerGetSeasonListener(this@HomePageFragment)
        }
    }

    private fun initBottomItem() {
        with(binding) {
            bottomNavigation.setOnItemSelectedListener {
                when (it.itemId) {
                    R.id.homeItem -> {
                        replaceFragment(fixturesFragment, R.id.viewPagerContainer, false)
                        true
                    }
                    R.id.newsItem -> {
                        replaceFragment(newsFragment, R.id.viewPagerContainer, false)
                        true
                    }
                    R.id.standingItem -> {
                        replaceFragment(standingFragment, R.id.viewPagerContainer, false)
                        true
                    }
                    R.id.topScorersItem -> {
                        replaceFragment(topScorersFragment, R.id.viewPagerContainer, false)
                        true
                    }
                    R.id.favoriteItem -> {
                        replaceFragment(favoriteFragment, R.id.viewPagerContainer, false)
                        true
                    }
                    else -> false
                }
            }
        }
    }

    companion object {
        fun newInstance() = HomePageFragment()
    }
}
