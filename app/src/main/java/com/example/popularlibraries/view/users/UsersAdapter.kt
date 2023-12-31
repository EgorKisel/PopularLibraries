package com.example.popularlibraries.view.users

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.popularlibraries.common.loadGlide
import com.example.popularlibraries.databinding.UserItemBinding
import com.example.popularlibraries.model.GithubUser

typealias OnUserClickListener = (login: String) -> Unit

class UsersAdapter(private val onUserClickListener: OnUserClickListener) :
    RecyclerView.Adapter<UsersAdapter.GithubUserViewHolder>() {

    var users: List<GithubUser> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GithubUserViewHolder {
        return GithubUserViewHolder(
            UserItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            , onUserClickListener
        )
    }

    override fun onBindViewHolder(holder: GithubUserViewHolder, position: Int) {
        holder.bind(users[position])
    }

    override fun getItemCount() = users.size

    class GithubUserViewHolder(
        private val binding: UserItemBinding,
        private val onUserClickListener: OnUserClickListener
    ) : ViewHolder(binding.root) {
        fun bind(item: GithubUser) = with(binding) {
            tvUserLogin.text = item.login
            ivUserAvatar.loadGlide(item.avatarUrl)
            root.setOnClickListener {
                onUserClickListener.invoke(item.login)
            }
        }
    }
}