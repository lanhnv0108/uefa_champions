package com.lanh.uefachampions.screen.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.lanh.uefachampions.utils.BasePresenter

abstract class BaseFragment<B : ViewDataBinding, P : BasePresenter<*>> : Fragment() {
    @get:LayoutRes
    abstract val layoutId: Int
    abstract val presenter: P

    lateinit var binding: B

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initPresenter()
        handlerEvent()
    }

    override fun onDestroy() {
        presenter.setView(null)
        super.onDestroy()
    }

    open fun initView() {}
    open fun initPresenter() {}
    open fun handlerEvent() {}
}
