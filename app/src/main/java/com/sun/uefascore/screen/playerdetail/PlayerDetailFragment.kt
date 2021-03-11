package com.sun.uefascore.screen.playerdetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment
import com.sun.uefascore.R
import com.sun.uefascore.data.model.Player
import com.sun.uefascore.utils.LoadImageUrl
import kotlinx.android.synthetic.main.fragment_player_detail.*

class PlayerDetailFragment : DialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_player_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        arguments?.getParcelable<Player>(BUNDLE_PLAYER)?.let {
            textViewAge.text = it.age.toString()
            textViewHeight.text = it.height
            textViewNationality.text = it.nationality
            textViewWeight.text = it.weight
            textViewName.text = it.name
            LoadImageUrl { bitmap ->
                imageViewAvatar?.setImageBitmap(bitmap)
            }.execute(it.photo)
        }
    }

    companion object {

        private const val BUNDLE_PLAYER = "BUNDLE_PLAYER"

        fun newInstance(player: Player) = PlayerDetailFragment().apply {
            arguments = bundleOf(BUNDLE_PLAYER to player)
        }
    }
}
