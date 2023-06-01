package org.android.go.sopt.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import org.android.go.sopt.data.model.home.ResponseUserInfo
import org.android.go.sopt.databinding.ItemGithubInfoBinding
import org.android.go.sopt.util.DiffCallback

class HomePagingAdapter(
) : PagingDataAdapter<ResponseUserInfo, HomePagingAdapter.PagingViewHolder>(
    HomeDiffCallback
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagingViewHolder {
        val binding =
            ItemGithubInfoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PagingViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PagingViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    inner class PagingViewHolder(private val binding: ItemGithubInfoBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(data: ResponseUserInfo) {
            binding.responseUserInfo = data
            binding.executePendingBindings()
        }
    }

    companion object {
        private val HomeDiffCallback =
            DiffCallback<ResponseUserInfo>(id = { old, new -> old.id == new.id })
    }
}

