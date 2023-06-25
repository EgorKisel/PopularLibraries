package com.example.popularlibraries.view.users

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.transition.TransitionManager
import com.example.popularlibraries.common.hide
import com.example.popularlibraries.common.show
import com.example.popularlibraries.core.App
import com.example.popularlibraries.databinding.FragmentUserListBinding
import com.example.popularlibraries.model.GithubUser
import com.example.popularlibraries.model.repository.GithubRepositoryImpl
import com.example.popularlibraries.presenter.UsersPresenter
import com.example.popularlibraries.view.main.BackPressedListener
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class UsersFragment : MvpAppCompatFragment(), UserView, BackPressedListener {

    private val presenter: UsersPresenter by moxyPresenter {
        UsersPresenter(GithubRepositoryImpl(), App.instance.router)
    }

    private val listener = object : ItemClickListener {
        override fun onUserClick(user: GithubUser) {
            presenter.openUserScreen(user)
        }
    }

    private val userAdapter = UsersAdapter()
    private lateinit var binding: FragmentUserListBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentUserListBinding.inflate(inflater, container, false).also {
            binding = it
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        userAdapter.setOnUserClickListener(listener)
        binding.rvGithubUser.adapter = userAdapter
        binding.rvGithubUser.layoutManager = LinearLayoutManager(requireContext())
    }

    override fun onBackPressed() = presenter.onBackPressed()

    override fun initList(list: List<GithubUser>) {
        TransitionManager.beginDelayedTransition(binding.root)
        userAdapter.users = list
    }

    override fun showLoading() {
        binding.progressBarList.show()
    }

    override fun hideLoading() {
        binding.progressBarList.hide()
    }

    override fun errorGetUser(message: String?) {
        Log.d("ERROR_LIST_USERS", "errorGetUser() called with: message = $message")
    }

    companion object {
        fun getInstance(): UsersFragment {
            return UsersFragment()
        }
    }
}