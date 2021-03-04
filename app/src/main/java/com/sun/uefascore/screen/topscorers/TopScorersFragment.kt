package com.sun.uefascore.screen.topscorers

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.sun.uefascore.R

class TopScorersFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_top_scorers, container, false)
    }

    companion object {
        fun newInstance() = TopScorersFragment()
    }
}
