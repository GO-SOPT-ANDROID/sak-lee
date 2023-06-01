package org.android.go.sopt.ui.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import org.android.go.sopt.data.model.home.ResponseUserInfo
import org.android.go.sopt.data.model.kakao.KaKaoImage
import org.android.go.sopt.databinding.ItemKakaoImageBinding
import org.android.go.sopt.util.DiffCallback

class SearchPagingAdapter(
) : PagingDataAdapter<KaKaoImage, SearchPagingAdapter.PagingViewHolder>(
    KaKaoDiffCallback
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

    companion object {
        private val KaKaoDiffCallback =
            DiffCallback<KaKaoImage>(id = { old, new -> old.collection == new.collection })
    }
}

