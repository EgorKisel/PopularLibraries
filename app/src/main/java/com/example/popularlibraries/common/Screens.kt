package com.example.popularlibraries.common

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.example.popularlibraries.model.GithubUser
import com.example.popularlibraries.view.userdetails.UserDetailsFragment
import com.example.popularlibraries.view.userdetails.UserDetailsFragment.Companion.KEY_USER
import com.example.popularlibraries.view.users.UsersFragment
import com.github.terrakok.cicerone.androidx.FragmentScreen

object UsersScreen : FragmentScreen {
    override fun createFragment(factory: FragmentFactory): Fragment {
        return UsersFragment.getInstance()
    }
}

data class UserScreen(private val user: GithubUser) : FragmentScreen {
    override fun createFragment(factory: FragmentFactory): Fragment {
        return UserDetailsFragment.newInstance(Bundle().apply {
            putParcelable(KEY_USER, user)
        })
    }
}