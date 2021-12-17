package com.lanh.uefachampions.screen.favorite.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lanh.uefachampions.R
import com.lanh.uefachampions.data.model.TeamDetail
import com.lanh.uefachampions.utils.loadUrl
import kotlinx.android.synthetic.main.item_layout_favorite.view.*

class FavoriteAdapter : RecyclerView.Adapter<FavoriteAdapter.ViewHolder>() {

    private var favorites = mutableListOf<TeamDetail>()
    private var listener: OnFavoriteRecyclerViewClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_layout_favorite, parent, false)
        return ViewHolder(view).apply {
            registerRecyclerViewClickListener { adapterPosition, view ->
                listener?.apply {
                    when (view) {
                        view.imageViewFavorite -> {
                            onClickFavoriteListener(favorites[adapterPosition])
                        }
                        view.constraintLayout -> {
                            onClickItemListener(favorites[adapterPosition])
                        }
                    }
                }
            }
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindViewData(favorites[position])
    }

    override fun getItemCount() = favorites.size

    fun updateData(favorites: MutableList<TeamDetail>) {
        favorites.let {
            this.favorites.clear()
            this.favorites.addAll(favorites)
            notifyDataSetChanged()
        }
    }

    fun registerRecyclerViewClickListener(
        listener: OnFavoriteRecyclerViewClickListener
    ) {
        this.listener = listener
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun registerRecyclerViewClickListener(listener: (Int, View) -> Unit) {
            itemView.imageViewFavorite.setOnClickListener {
                listener(adapterPosition, it)
            }
            itemView.constraintLayout.setOnClickListener {
                listener(adapterPosition, it)
            }
        }

        fun bindViewData(favorite: TeamDetail) {
            itemView.textViewNameTeam?.text = favorite.name
            getImageLogo(favorite)
        }

        private fun getImageLogo(favorite: TeamDetail) {
            favorite.logo?.let { itemView.imageViewLogo.loadUrl(it) }
        }
    }
}
