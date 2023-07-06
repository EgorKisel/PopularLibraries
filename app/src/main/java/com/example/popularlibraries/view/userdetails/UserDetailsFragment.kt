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
import com.example.popularlibraries.model.GithubUser
import com.example.popularlibraries.presenter.UserDetailsPresenter
import com.example.popularlibraries.view.main.BackPressedListener
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class UserDetailsFragment : MvpAppCompatFragment(), UserDetailsView, BackPressedListener {

    private val reposAdapter = ReposAdapter {
        presenter.openRepoScreen(it)
    }

    private val presenter: UserDetailsPresenter by moxyPresenter {
        UserDetailsPresenter(arguments?.getString(KEY_USER))
    }

    private var binding: FragmentUserScreenBinding? = null

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
        binding?.rvGithubUserRepos?.adapter = reposAdapter
        binding?.rvGithubUserRepos?.layoutManager = LinearLayoutManager(requireContext())
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

    override fun showUser(user: GithubUser) {
        TransitionManager.beginDelayedTransition(binding?.root)
        binding?.userName?.text = user.login
        binding?.ivUserAvatar?.loadGlide(user.avatarUrl)
        binding?.userRepos?.text = "Repo:" + user.repos?.size.toString()
        user.repos?.let {list ->
            reposAdapter.repos = list.sortedByDescending { it.createdAt }
        }
    }

    override fun showLoading() {
        binding?.apply {
            progressBar.show()
            userName.hide()
            ivUserAvatar.hide()
            rvGithubUserRepos.hide()
            userRepos.hide()
        }
    }

    override fun hideLoading() {
        binding?.apply {
            progressBar.hide()
            userName.show()
            ivUserAvatar.show()
            rvGithubUserRepos.show()
            userRepos.show()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}