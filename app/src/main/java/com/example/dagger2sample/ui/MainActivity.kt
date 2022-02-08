package com.example.dagger2sample.ui

import android.os.Bundle
import dagger.android.support.DaggerAppCompatActivity
import com.example.dagger2sample.R
import com.example.dagger2sample.ui.fragments.MovieListFragment

class MainActivity : DaggerAppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        replaceFragment()
    }

    fun replaceFragment()
    {
        supportFragmentManager.beginTransaction().replace(R.id.container,MovieListFragment()).commit()
    }
}