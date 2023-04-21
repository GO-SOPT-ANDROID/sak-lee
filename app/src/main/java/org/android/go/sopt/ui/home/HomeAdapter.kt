package org.android.go.sopt.ui.home

import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.ViewGroup
import androidx.recyclerview.selection.ItemDetailsLookup
import androidx.recyclerview.selection.SelectionTracker
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import org.android.go.sopt.databinding.ItemGithubInfoBinding
import org.android.go.sopt.databinding.ItemTextBinding
import org.android.go.sopt.model.FakeGithubInfoItem

class HomeAdapter() :
    ListAdapter<FakeGithubInfoItem, RecyclerView.ViewHolder>(
        FakeGithubDiffCallback()
    ) {

    private lateinit var selectionTracker: SelectionTracker<Long>

    init {
        setHasStableIds(true)
    }

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getItemViewType(position: Int): Int =
        when {
            getItem(position)?.title != null -> VIEW_TYPE_TITLE
            else -> VIEW_TYPE_ITEM
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        when (viewType) {
            VIEW_TYPE_TITLE -> TitleViewHolder(
                ItemTextBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            )
            VIEW_TYPE_ITEM -> HomeViewHolder(
                ItemGithubInfoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            )
            else -> throw IllegalArgumentException("Invalid view type")
        }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val content = currentList[position]
        when (holder) {
            is HomeViewHolder -> holder.bind(content, position)
            is TitleViewHolder -> holder.bind(content)
        }
    }


    fun setSelectionTracker(selectionTracker: SelectionTracker<Long>) {
        this.selectionTracker = selectionTracker
    }

    /**
     * ItemDetailsLookup
     * 사용자가 선택한 아이템의 위치,정보를 제공
     * getItemDetails메서드 제공 인터페이스
     * MotionEvent를 기반하여 선택된 내용을 ViewHolder에 매핑
     * */
    class HomeLookUp(private val recyclerView: RecyclerView) : ItemDetailsLookup<Long>() {
        override fun getItemDetails(event: MotionEvent): ItemDetails<Long>? {
            val view = recyclerView.findChildViewUnder(event.x, event.y)
            return view?.let {
                val viewHolder = recyclerView.getChildViewHolder(it) as? HomeViewHolder
                viewHolder?.getItemDetails()
            }
        }
    }

    inner class HomeViewHolder(private val binding: ItemGithubInfoBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(fakeGithubInfoItem: FakeGithubInfoItem, itemPosition: Int) = with(binding) {
            fakeinfo = fakeGithubInfoItem
            root.setOnClickListener {
                selectionTracker?.select(itemPosition.toLong())
            }

            loRvitem.isActivated = selectionTracker?.isSelected(itemPosition.toLong()) ?: false
            executePendingBindings()
        }

        fun getItemDetails(): ItemDetailsLookup.ItemDetails<Long> =
            object : ItemDetailsLookup.ItemDetails<Long>() {
                override fun getPosition(): Int = adapterPosition
                override fun getSelectionKey(): Long? = itemId
            }
    }

    class TitleViewHolder(private val binding: ItemTextBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(fakeGithubInfoItem: FakeGithubInfoItem) {
            binding.tvTitle.text = fakeGithubInfoItem.title
            binding.executePendingBindings()
        }
    }

    companion object {
        const val VIEW_TYPE_TITLE = 1
        const val VIEW_TYPE_ITEM = 2
    }
}

class FakeGithubDiffCallback : DiffUtil.ItemCallback<FakeGithubInfoItem>() {
    override fun areItemsTheSame(
        oldItem: FakeGithubInfoItem,
        newItem: FakeGithubInfoItem
    ): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(
        oldItem: FakeGithubInfoItem,
        newItem: FakeGithubInfoItem
    ): Boolean =
        oldItem == newItem
}