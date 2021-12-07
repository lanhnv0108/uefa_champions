package com.lanh.uefachampions.screen.base

import android.annotation.SuppressLint
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.lanh.uefachampions.R
import com.lanh.uefachampions.databinding.FragmentHomePageBinding
import com.lanh.uefachampions.screen.favorite.FavoriteFragment
import com.lanh.uefachampions.screen.fixtures.FixturesFragment
import com.lanh.uefachampions.screen.news.NewsFragment
import com.lanh.uefachampions.screen.standing.StandingFragment
import com.lanh.uefachampions.screen.topscorers.TopScorersFragment
import com.lanh.uefachampions.utils.Constant
import com.lanh.uefachampions.utils.MenuItem
import com.lanh.uefachampions.utils.OnFavoriteListener
import com.lanh.uefachampions.utils.OnGetSeasonListener
import com.lanh.uefachampions.utils.*

class HomePageFragment : BaseFragment<FragmentHomePageBinding, EmptyPresenter>(),
    OnGetSeasonListener,
    OnFavoriteListener {
    override val layoutId: Int
        get() = R.layout.fragment_home_page

    override val presenter: EmptyPresenter by lazy { EmptyPresenter() }
    private val fragments = mutableListOf<Fragment>()
    private val newsFragment = NewsFragment.newInstance()
    private val fixturesFragment = FixturesFragment.newInstance()
    private val standingFragment = StandingFragment.newInstance()
    private val topScorersFragment = TopScorersFragment.newInstance()
    private val favoriteFragment = FavoriteFragment.newInstance()

    override fun onClickFavoriteListener() {
        favoriteFragment.onUpdateFavorite()
    }

    override fun getSeason(season: String) {
        standingFragment.updateSeason(season)
        topScorersFragment.updateSeason(season)
        favoriteFragment.updateSeason(season)
    }

    override fun initView() {
        super.initView()
        registerListener()
        initFragment()
        initViewPager()
        initBottomItem()
    }

    private fun registerListener() {
        fixturesFragment.apply {
            registerGetSeasonListener(this@HomePageFragment)
            registerFavoriteListener(this@HomePageFragment)
        }
        standingFragment.registerFavoriteListener(this)
        favoriteFragment.registerFavoriteListener(this)
    }


    @SuppressLint("WrongConstant")
    private fun initViewPager() {
        with(binding.viewPagerContainer) {
            adapter =
                ViewPagerContainerAdapter(parentFragmentManager, lifecycle, fragments)
            offscreenPageLimit = Constant.LIMIT_OFFSET
        }
    }

    private fun initFragment() {
        fragments.apply {
            add(MenuItem.FIXTURES.ordinal, fixturesFragment)
            add(MenuItem.NEWS.ordinal, newsFragment)
            add(MenuItem.STANDING.ordinal, standingFragment)
            add(MenuItem.SCORERS.ordinal, topScorersFragment)
            add(MenuItem.FAVORITE.ordinal, favoriteFragment)
        }
    }

    private fun initBottomItem() {
        with(binding) {
            bottomNavigation.setOnItemSelectedListener {
                when (it.itemId) {
                    R.id.homeItem -> {
                        viewPagerContainer.currentItem = MenuItem.FIXTURES.ordinal
                        true
                    }
                    R.id.newsItem -> {
                        viewPagerContainer.currentItem = MenuItem.NEWS.ordinal
                        true
                    }
                    R.id.standingItem -> {
                        viewPagerContainer.currentItem = MenuItem.STANDING.ordinal
                        true
                    }
                    R.id.topScorersItem -> {
                        viewPagerContainer.currentItem = MenuItem.SCORERS.ordinal
                        true
                    }
                    R.id.favoriteItem -> {
                        viewPagerContainer.currentItem = MenuItem.FAVORITE.ordinal
                        true
                    }
                    else -> false
                }
            }
            viewPagerContainer.registerOnPageChangeCallback(object :
                ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    hideKeyboard()
                    bottomNavigation.menu.getItem(position).isChecked = true
                }
            })
        }
    }

    companion object {
        fun newInstance() = HomePageFragment()
    }
}
