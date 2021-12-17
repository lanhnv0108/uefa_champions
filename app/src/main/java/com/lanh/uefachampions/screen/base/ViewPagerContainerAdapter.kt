package com.lanh.uefachampions.screen.base

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

class ViewPagerContainerAdapter(
    fragment: FragmentManager,
    lifecycle: Lifecycle,
    private var fragments: MutableList<Fragment>
) : FragmentStateAdapter(fragment, lifecycle) {

    override fun getItemCount(): Int = fragments.size

    override fun createFragment(position: Int) = fragments[position]
}
