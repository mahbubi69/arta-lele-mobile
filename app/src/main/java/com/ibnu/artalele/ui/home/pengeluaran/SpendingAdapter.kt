package com.ibnu.artalele.ui.home.pengeluaran

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.ibnu.artalele.data.entities.SpendingEntity
import com.ibnu.artalele.databinding.SubTransactionItemBinding
import com.ibnu.artalele.utils.ArtaLeleHelper
import com.ibnu.artalele.utils.RecyclerviewItemClickHandler

class SpendingAdapter(private val onClickAction: RecyclerviewItemClickHandler
) :
    PagingDataAdapter<SpendingEntity, SpendingAdapter.SpendingViewHolder>(
        DIFF_CALLBACK
    ) {

    override fun onBindViewHolder(holder: SpendingAdapter.SpendingViewHolder, position: Int) {
        getItem(position)?.let { income ->
            holder.bind(income)
            holder.itemView.setOnClickListener {
                onClickAction.onClickItem(income.id)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SpendingAdapter.SpendingViewHolder {
        val binding =
            SubTransactionItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SpendingViewHolder(binding)
    }


    inner class SpendingViewHolder(private val binding: SubTransactionItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(income: SpendingEntity) {
            binding.tvValue.text = ArtaLeleHelper.addRupiahToAmount(income.total)
            binding.tvDesc.text = income.description
            binding.tvTitle.text = income.category.categoryName
            binding.imgIcon.setImageResource(income.category.categoryImage)
        }
    }

    companion object {
        private val DIFF_CALLBACK: DiffUtil.ItemCallback<SpendingEntity> =
            object : DiffUtil.ItemCallback<SpendingEntity>() {
                override fun areItemsTheSame(oldItem: SpendingEntity, newItem: SpendingEntity): Boolean {
                    return oldItem.id == newItem.id  && oldItem.id  == newItem.id
                }

                override fun areContentsTheSame(oldItem: SpendingEntity, newItem: SpendingEntity): Boolean {
                    return oldItem == newItem
                }

            }
    }

}