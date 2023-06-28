package com.example.popularlibraries.common

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.example.popularlibraries.network.ReposDto
import com.example.popularlibraries.view.userdetails.UserDetailsFragment
import com.example.popularlibraries.view.userrepository.RepoUserFragment
import com.example.popularlibraries.view.userrepository.RepoUserFragment.Companion.KEY_REPO
import com.example.popularlibraries.view.users.UsersFragment
import com.github.terrakok.cicerone.androidx.FragmentScreen

object UsersScreen : FragmentScreen {
    override fun createFragment(factory: FragmentFactory): Fragment {
        return UsersFragment.getInstance()
    }
}

data class UserScreen(private val userLogin: String) : FragmentScreen {
    override fun createFragment(factory: FragmentFactory): Fragment {
        return UserDetailsFragment.newInstance(userLogin)
    }
}

data class RepoScreen(private val repo: ReposDto) : FragmentScreen {
    override fun createFragment(factory: FragmentFactory): Fragment {
        return RepoUserFragment.getInstance(Bundle().apply {
            putParcelable(KEY_REPO, repo)
        })
    }

}