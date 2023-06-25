package com.example.popularlibraries.view.users

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.popularlibraries.databinding.UserItemBinding
import com.example.popularlibraries.model.GithubUser

class UsersAdapter() : RecyclerView.Adapter<UsersAdapter.GithubUserViewHolder>() {

    private lateinit var userClick: ItemClickListener

    fun setOnUserClickListener(listener: ItemClickListener) {
        userClick = listener
    }

    var users: List<GithubUser> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GithubUserViewHolder {
        return GithubUserViewHolder(
            UserItemBinding.inflate(LayoutInflater.from(parent.context)), userClick
        )
    }

    override fun onBindViewHolder(holder: GithubUserViewHolder, position: Int) {
        holder.bind(users[position])
    }

    override fun getItemCount() = users.size

    class GithubUserViewHolder(
        private val binding: UserItemBinding,
        private val userClick: ItemClickListener
    ) : ViewHolder(binding.root) {
        fun bind(item: GithubUser) = with(binding) {
            tvUserLogin.text = item.login
            itemView.setOnClickListener {
                userClick.onUserClick(item)
            }
        }
    }
}