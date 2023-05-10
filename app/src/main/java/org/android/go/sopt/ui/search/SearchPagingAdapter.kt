package org.android.go.sopt.ui.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import org.android.go.sopt.data.model.kakao.KaKaoImage
import org.android.go.sopt.databinding.ItemKakaoImageBinding

class SearchPagingAdapter(
) : PagingDataAdapter<KaKaoImage, SearchPagingAdapter.PagingViewHolder>(
    KaKaoDiffCallback()
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagingViewHolder {
        val binding =
            ItemKakaoImageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PagingViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PagingViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    inner class PagingViewHolder(private val binding: ItemKakaoImageBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(data: KaKaoImage) {
            binding.kakaoimage = data
            binding.executePendingBindings()
        }
    }

}

class KaKaoDiffCallback : DiffUtil.ItemCallback<KaKaoImage>() {
    override fun areItemsTheSame(oldItem: KaKaoImage, newItem: KaKaoImage): Boolean {
        return oldItem.collection == newItem.collection
    }

    override fun areContentsTheSame(oldItem: KaKaoImage, newItem: KaKaoImage): Boolean {
        return oldItem == newItem
    }

}