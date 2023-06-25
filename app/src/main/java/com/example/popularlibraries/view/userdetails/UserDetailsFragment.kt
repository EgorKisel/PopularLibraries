package com.example.popularlibraries.view.userdetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.popularlibraries.core.App
import com.example.popularlibraries.databinding.FragmentUserScreenBinding
import com.example.popularlibraries.presenter.UserPresenter
import com.example.popularlibraries.view.main.BackPressedListener
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class UserDetailsFragment : MvpAppCompatFragment(), UserDetailsView, BackPressedListener {

    private val presenter: UserPresenter by moxyPresenter {
        UserPresenter(App.instance.router, arguments?.getParcelable(KEY_USER))
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

    override fun onBackPressed() = presenter.onBackPressed()

    override fun setUser(login: String?) {
        binding.userName.text = login
    }

    companion object {
        const val KEY_USER = "key_user"
        fun newInstance(bundle: Bundle) = UserDetailsFragment().apply {
            arguments = bundle
        }
    }
}