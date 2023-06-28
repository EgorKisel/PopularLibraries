package com.example.popularlibraries.view.userrepository

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.popularlibraries.core.App
import com.example.popularlibraries.databinding.FragmentRepoUserBinding
import com.example.popularlibraries.network.ReposDto
import com.example.popularlibraries.presenter.RepoUserPresenter
import com.example.popularlibraries.view.main.BackPressedListener
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class RepoUserFragment : MvpAppCompatFragment(), RepoUserView, BackPressedListener {

    private val presenter: RepoUserPresenter by moxyPresenter {
        RepoUserPresenter(App.instance.router, arguments?.getParcelable(KEY_REPO))
    }

    private lateinit var binding: FragmentRepoUserBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentRepoUserBinding.inflate(inflater, container, false).also {
            binding = it
        }.root
    }

    override fun onBackPressed() = presenter.onBackPressed()

    override fun showRepo(repo: ReposDto) = with(binding) {
        repoId.text = repo.id.toString()
        nodeId.text = repo.nodeId
        name.text = repo.name
        description.text = repo.description
        createdAt.text = repo.createdAt
        updatedAt.text = repo.updatedAt
        language.text = repo.language
        forksCount.text = repo.forksCount.toString()
    }

    companion object {
        const val KEY_REPO = "KEY_REPO"
        fun getInstance(bundle: Bundle) = RepoUserFragment().apply { arguments = bundle }
    }
}