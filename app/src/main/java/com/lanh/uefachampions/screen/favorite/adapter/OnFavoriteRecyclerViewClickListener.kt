package com.lanh.uefachampions.screen.favorite.adapter

import com.lanh.uefachampions.data.model.TeamDetail

interface OnFavoriteRecyclerViewClickListener{

    fun onClickItemListener(item: TeamDetail)
    fun onClickFavoriteListener(item: TeamDetail)
}
