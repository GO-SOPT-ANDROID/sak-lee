package org.android.go.sopt.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import org.android.go.sopt.databinding.ItemGithubInfoBinding
import org.android.go.sopt.model.FakeGithubInfoItem

class HomeAdapter():
    ListAdapter<FakeGithubInfoItem, HomeAdapter.HomeViewHolder>(
        BannerDiffCallback()
    ) {
    private lateinit var binding: ItemGithubInfoBinding


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        binding =
            ItemGithubInfoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HomeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class HomeViewHolder(private val binding: ItemGithubInfoBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(fakeGithubInfoItem: FakeGithubInfoItem) {
            binding.fakeinfo = fakeGithubInfoItem
            binding.executePendingBindings()
        }
    }
}

class BannerDiffCallback : DiffUtil.ItemCallback<FakeGithubInfoItem>() {
    override fun areItemsTheSame(oldItem: FakeGithubInfoItem, newItem: FakeGithubInfoItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: FakeGithubInfoItem, newItem: FakeGithubInfoItem): Boolean {
        return oldItem == newItem
    }
}