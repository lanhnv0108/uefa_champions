package com.sun.uefascore.screen.favorite.adapter

import com.sun.uefascore.data.model.TeamDetail

interface OnFavoriteRecyclerViewClickListener{

    fun onClickItemListener(item: TeamDetail)
    fun onClickFavoriteListener(item: TeamDetail)
}
