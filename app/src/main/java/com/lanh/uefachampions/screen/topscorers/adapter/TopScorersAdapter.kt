package com.lanh.uefachampions.screen.topscorers.adapter

import android.os.AsyncTask
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.lanh.uefachampions.R
import com.lanh.uefachampions.data.model.TopScorer
import com.lanh.uefachampions.databinding.ItemLayoutTopScorersBinding
import com.lanh.uefachampions.screen.base.BaseListAdapter
import com.lanh.uefachampions.screen.base.BaseViewHolder
import com.lanh.uefachampions.utils.LoadImageUrl
import com.lanh.uefachampions.utils.OnItemRecyclerViewClickListener
import com.lanh.uefachampions.utils.inflateView
import kotlinx.android.synthetic.main.item_layout_top_scorers.view.*

//class TopScorersAdapter : RecyclerView.Adapter<TopScorersAdapter.ViewHolder?>() {
//
//    private var topScorers = mutableListOf<TopScorer>()
//    private var listener: OnItemRecyclerViewClickListener<TopScorer>? = null
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
//        val view = LayoutInflater.from(parent.context)
//            .inflate(R.layout.item_layout_top_scorers, parent, false)
//        return ViewHolder(view).apply {
//            registerItemRecyclerViewClickListener {
//                listener?.onItemClickListener(topScorers[adapterPosition])
//            }
//        }
//    }
//
//    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        holder.bindViewData(topScorers[position])
//    }
//
//    override fun getItemCount() = topScorers.size
//
//    fun updateData(topScorers: MutableList<TopScorer>) {
//        topScorers.let {
//            this.topScorers.clear()
//            this.topScorers.addAll(it)
//            notifyDataSetChanged()
//        }
//    }
//
//    fun registerItemRecyclerViewClickListener(
//        listener: OnItemRecyclerViewClickListener<TopScorer>?
//    ) {
//        this.listener = listener
//    }
//
//    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//
//        fun bindViewData(topScorer: TopScorer) {
//            itemView.apply {
//                textViewNamePlayer.text = topScorer.player?.name
//                textViewGoals.text = topScorer.statistic?.goals.toString()
//            }
//            getRank()
//            getImageAvatar(topScorer)
//            getImageLogo(topScorer)
//        }
//
//        fun registerItemRecyclerViewClickListener(listener: (Int) -> Unit) {
//            itemView.setOnClickListener {
//                listener(adapterPosition)
//            }
//        }
//
//        private fun getRank() {
//            itemView.textViewRank.text = "${adapterPosition + 1}"
//        }
//
//        private fun getImageAvatar(topScorer: TopScorer) {
//            LoadImageUrl {
//                itemView.imageViewAvatar.setImageBitmap(it)
//            }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, topScorer.player?.photo)
//        }
//
//        private fun getImageLogo(topScorer: TopScorer) {
//            LoadImageUrl {
//                itemView.imageViewLogo.setImageBitmap(it)
//            }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, topScorer.statistic?.team?.logo)
//        }
//    }
//}


class TopScorersAdapter(private val layoutPlayerClick: (TopScorer) -> Unit) :
    BaseListAdapter<TopScorer, ItemLayoutTopScorersBinding>() {
    override fun createCustomViewHolder(parent: ViewGroup, viewType: Int): Any {
        return TopScorersViewHolder(layoutPlayerClick, parent)
    }
}

class TopScorersViewHolder(private val layoutPlayerClick: (TopScorer) -> Unit, parent: ViewGroup) :
    BaseViewHolder<TopScorer, ItemLayoutTopScorersBinding>(
        inflateView(
            R.layout.item_layout_top_scorers,
            parent
        )
    ) {

    override fun bind(item: TopScorer) {
        super.bind(item)
        binding.layoutPlayer.setOnClickListener {
            layoutPlayerClick.invoke(item)
        }
    }
}