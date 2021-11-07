package com.ibnu.artalele.ui.extra.category.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.ibnu.artalele.data.entities.CategoryEntity
import com.ibnu.artalele.databinding.CategoryItemBinding
import com.ibnu.artalele.utils.RecyclerviewItemClickHandler

class CategoryPagingAdapter(private val onClickAction: RecyclerviewItemClickHandler) :
    PagingDataAdapter<CategoryEntity, CategoryPagingAdapter.CategoryViewHolder>(DIFF_CALLBACK) {

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        getItem(position)?.let { category ->
            holder.bind(category)
            holder.itemView.setOnClickListener {
                onClickAction.onClickItem(
                    category.categoryId
                )
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val binding =
            CategoryItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CategoryViewHolder(binding)
    }

    inner class CategoryViewHolder(private val binding: CategoryItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(category: CategoryEntity) {
            binding.imgCategoryItem.setImageResource(category.categoryImage)
            binding.tvCategoryItem.text = category.categoryName
        }
    }

    companion object {
        private val DIFF_CALLBACK: DiffUtil.ItemCallback<CategoryEntity> =
            object : DiffUtil.ItemCallback<CategoryEntity>() {
                override fun areItemsTheSame(
                    oldItem: CategoryEntity,
                    newItem: CategoryEntity
                ): Boolean {
                    return oldItem.categoryId == newItem.categoryId && oldItem.categoryId == newItem.categoryId
                }

                override fun areContentsTheSame(
                    oldItem: CategoryEntity,
                    newItem: CategoryEntity
                ): Boolean {
                    return oldItem == newItem
                }

            }
    }


}