package com.example.popularlibraries.view.main

import android.os.Bundle
import com.example.popularlibraries.R
import com.example.popularlibraries.core.App
import com.example.popularlibraries.databinding.ActivityMainBinding
import com.example.popularlibraries.presenter.MainPresenter
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.androidx.AppNavigator
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter
import javax.inject.Inject

class MainActivity : MvpAppCompatActivity(), MainView {

    private lateinit var binding: ActivityMainBinding
    private val navigator = AppNavigator(this, R.id.containerMain)

    @Inject
    lateinit var navigationHolder: NavigatorHolder

    private val presenter by moxyPresenter {
        MainPresenter().apply {
            App.instance.appComponent.inject(this)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        App.instance.appComponent.inject(this)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Creation().exec()
        //Operators().exec()
        //Sources().exec()
        //BackPressure().exec()
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        navigationHolder.setNavigator(navigator)
    }

    override fun onPause() {
        navigationHolder.removeNavigator()
        super.onPause()
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