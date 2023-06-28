package com.example.popularlibraries.view.userdetails

import android.os.Bundle
import android.transition.TransitionManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.popularlibraries.common.hide
import com.example.popularlibraries.common.loadGlide
import com.example.popularlibraries.common.show
import com.example.popularlibraries.core.App
import com.example.popularlibraries.databinding.FragmentUserScreenBinding
import com.example.popularlibraries.model.GithubUserRepos
import com.example.popularlibraries.model.repository.GithubRepositoryImpl
import com.example.popularlibraries.network.NetworkProvider
import com.example.popularlibraries.presenter.UserDetailsPresenter
import com.example.popularlibraries.view.main.BackPressedListener
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class UserDetailsFragment : MvpAppCompatFragment(), UserDetailsView, BackPressedListener {

    private val presenter: UserDetailsPresenter by moxyPresenter {
        UserDetailsPresenter(App.instance.router, GithubRepositoryImpl(NetworkProvider.usersApi))
    }

    private val reposAdapter = ReposAdapter {
        presenter.openRepoScreen(it)
    }

    private lateinit var binding: FragmentUserScreenBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentUserScreenBinding.inflate(inflater, container, false).also {
            binding = it
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.getString(KEY_USER)?.let {
            presenter.loadUser(it)
        }
        binding.rvGithubUserRepos.adapter = reposAdapter
        binding.rvGithubUserRepos.layoutManager = LinearLayoutManager(requireContext())
    }

    override fun onBackPressed() = presenter.onBackPressed()

    companion object {
        const val KEY_USER = "key_user"
        fun newInstance(userLogin: String) = UserDetailsFragment().apply {
            arguments = Bundle().apply {
                putString(KEY_USER, userLogin)
            }
        }
    }

    override fun showUser(user: GithubUserRepos) {
        TransitionManager.beginDelayedTransition(binding.root)
        binding.userName.text = user.user.login
        binding.ivUserAvatar.loadGlide(user.user.avatarUrl)
        reposAdapter.repos = user.reposList
    }

    override fun showLoading() {
        binding.apply {
            progressBar.show()
            userName.hide()
            ivUserAvatar.hide()
        }
    }

    override fun hideLoading() {
        binding.apply {
            progressBar.hide()
            userName.show()
            ivUserAvatar.show()
        }
    }
}