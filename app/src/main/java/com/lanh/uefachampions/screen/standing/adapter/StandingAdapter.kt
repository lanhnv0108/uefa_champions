package com.lanh.uefachampions.screen.standing.adapter

import android.os.AsyncTask
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewParent
import androidx.recyclerview.widget.RecyclerView
import com.lanh.uefachampions.R
import com.lanh.uefachampions.data.model.Standing
import com.lanh.uefachampions.data.model.Team
import com.lanh.uefachampions.databinding.ItemLayoutStandingBinding
import com.lanh.uefachampions.screen.base.BaseListAdapter
import com.lanh.uefachampions.screen.base.BaseViewHolder
import com.lanh.uefachampions.utils.LoadImageUrl
import com.lanh.uefachampions.utils.OnItemRecyclerViewClickListener
import com.lanh.uefachampions.utils.inflateView
import kotlinx.android.synthetic.main.item_layout_standing.view.*

//
//class StandingAdapter : RecyclerView.Adapter<StandingAdapter.ViewHolder?>() {
//
//    private var standings = mutableListOf<Standing>()
//    private var listener: OnItemRecyclerViewClickListener<Team>? = null
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
//        val view = LayoutInflater.from(parent.context)
//            .inflate(R.layout.item_layout_standing, parent, false)
//        return ViewHolder(view).apply {
//            registerItemViewHolderListener {
//                listener?.onItemClickListener(standings[it].team)
//            }
//        }
//    }
//
//    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        holder.bindViewData(standings[position])
//    }
//
//    override fun getItemCount() = standings.size
//
//    fun updateData(standings: MutableList<Standing>) {
//        standings.let {
//            this.standings.clear()
//            this.standings.addAll(it)
//            notifyDataSetChanged()
//        }
//    }
//
//    fun registerItemViewHolderListener(listener: OnItemRecyclerViewClickListener<Team>?) {
//        this.listener = listener
//    }
//
//    class ViewHolder(
//        itemView: View
//    ) : RecyclerView.ViewHolder(itemView) {
//
//        fun registerItemViewHolderListener(listener: (Int) -> Unit) {
//            itemView.setOnClickListener {
//                listener(adapterPosition)
//            }
//        }
//
//        fun bindViewData(standing: Standing) {
//            itemView.apply {
//                textViewRank.text = standing.rank.toString()
//                textViewNameTeam.text = standing.team?.name
//                textViewPlayed.text = standing.all?.played.toString()
//                textViewWin.text = standing.all?.win.toString()
//                textViewDraw.text = standing.all?.draw.toString()
//                textViewLose.text = standing.all?.lose.toString()
//                textViewGoals.text = standing.goalsDiff.toString()
//                textViewPoints.text = standing.points.toString()
//            }
//            getImage(standing)
//        }
//
//        private fun getImage(standing: Standing) {
//            LoadImageUrl {
//                itemView.imageViewLogo.setImageBitmap(it)
//            }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, standing.team?.logo)
//        }
//    }
//}

class StandingAdapter(private val onItemClick: (Team) -> Unit) :
    BaseListAdapter<Standing, ItemLayoutStandingBinding>() {
    override fun createCustomViewHolder(parent: ViewGroup, viewType: Int): Any {
        return StandingViewHolder(parent, onItemClick)
    }
}

class StandingViewHolder(parent: ViewGroup, private val onItemClick: (Team) -> Unit) :
    BaseViewHolder<Standing, ItemLayoutStandingBinding>(
        inflateView(R.layout.item_layout_standing, parent)
    ) {
    override fun bind(item: Standing) {
        super.bind(item)
        binding.layoutTeam.setOnClickListener {
            item.team?.let { team -> onItemClick.invoke(team) }
        }
    }
}