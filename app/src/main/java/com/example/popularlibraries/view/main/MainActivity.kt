package com.example.popularlibraries.view.main

import android.os.Bundle
import com.example.popularlibraries.R
import com.example.popularlibraries.core.App
import com.example.popularlibraries.databinding.ActivityMainBinding
import com.example.popularlibraries.presenter.MainPresenter
import com.example.popularlibraries.test.BackPressure
import com.example.popularlibraries.test.Operators
import com.example.popularlibraries.test.Sources
import com.github.terrakok.cicerone.androidx.AppNavigator
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter

class MainActivity : MvpAppCompatActivity(), MainView {

    private lateinit var binding: ActivityMainBinding
    private val navigator = AppNavigator(this, R.id.container)
    private val presenter by moxyPresenter { MainPresenter(App.instance.router) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Creation().exec()
        //Operators().exec()
        //Sources().exec()
        BackPressure().exec()
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        App.instance.navigationHolder.setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()
        App.instance.navigationHolder.removeNavigator()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        supportFragmentManager.fragments.forEach { fragment ->
            if (fragment is BackPressedListener && fragment.onBackPressed()) {
                return
            }
            presenter.onBackPressed()
        }
    }
}