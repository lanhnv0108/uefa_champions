package com.sun.uefascore.screen.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.sun.uefascore.R
import com.sun.uefascore.data.model.TeamDetail
import com.sun.uefascore.screen.favorite.adapter.FavoriteAdapter
import com.sun.uefascore.screen.favorite.adapter.OnFavoriteRecyclerViewClickListener
import kotlinx.android.synthetic.main.fragment_favorite.*

class FavoriteFragment : Fragment(), FavoriteContract.View,
    OnFavoriteRecyclerViewClickListener {

    private val adapter: FavoriteAdapter by lazy {
        FavoriteAdapter()
    }
    private val presenter by lazy {
        FavoritePresenter()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_favorite, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initData()
    }

    private fun initView() {
        recyclerViewFavorite.adapter = adapter
        adapter.registerRecyclerViewClickListener(this)
    }

    private fun initData() {
        presenter.setView(this)
    }

    override fun onFailed(idMessage: Int) {
        Toast.makeText(context, getString(idMessage), Toast.LENGTH_LONG).show()
    }

    override fun onClickItemListener(item: TeamDetail) {
    }

    override fun onClickFavoriteListener(item: TeamDetail) {
    }

    companion object {

        fun newInstance() = FavoriteFragment()
    }
}
