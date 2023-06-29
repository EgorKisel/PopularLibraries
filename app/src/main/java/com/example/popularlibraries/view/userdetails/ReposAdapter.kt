package com.example.popularlibraries.view.userdetails

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.popularlibraries.databinding.ItemReposBinding
import com.example.popularlibraries.network.ReposDto

typealias OnUserClickListener = (repo: ReposDto) -> Unit

class ReposAdapter(private val onUserClickListener: OnUserClickListener) :
    RecyclerView.Adapter<GithubUserReposViewHolder>() {

    var repos: List<ReposDto> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GithubUserReposViewHolder {
        return GithubUserReposViewHolder(
            ItemReposBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ), onUserClickListener
        )
    }

    override fun getItemCount() = repos.size

    override fun onBindViewHolder(holder: GithubUserReposViewHolder, position: Int) {
        holder.bind(repos[position])
    }
}

class GithubUserReposViewHolder(
    private val binding: ItemReposBinding,
    private val onUserClickListener: OnUserClickListener,
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: ReposDto) = with(binding) {
        nameRepo.text = item.name
        dateCreating.text = item.createdAt
        root.setOnClickListener {
            onUserClickListener.invoke(item)
        }
    }
}