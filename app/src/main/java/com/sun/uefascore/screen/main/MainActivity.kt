package com.sun.uefascore.screen.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sun.uefascore.R
import com.sun.uefascore.screen.base.HomePageFragment
import com.sun.uefascore.utils.addFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        addFragment(HomePageFragment.newInstance(), containerLayout.id)
    }
}
