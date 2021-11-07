package com.ibnu.artalele.ui.home.pemasukan.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.ibnu.artalele.data.entities.IncomeEntity
import com.ibnu.artalele.databinding.SubTransactionItemBinding
import com.ibnu.artalele.utils.ArtaLeleHelper
import com.ibnu.artalele.utils.RecyclerviewItemClickHandler

class IncomeAdapter(private val onClickAction: RecyclerviewItemClickHandler
) :
    PagingDataAdapter<IncomeEntity, IncomeAdapter.IncomeViewHolder>(
        DIFF_CALLBACK
    ) {

    override fun onBindViewHolder(holder: IncomeAdapter.IncomeViewHolder, position: Int) {
        getItem(position)?.let { income ->
            holder.bind(income)
            holder.itemView.setOnClickListener {
                onClickAction.onClickItem(income.id)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IncomeAdapter.IncomeViewHolder {
        val binding =
            SubTransactionItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return IncomeViewHolder(binding)
    }


    inner class IncomeViewHolder(private val binding: SubTransactionItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(income: IncomeEntity) {
            binding.tvValue.text = ArtaLeleHelper.addRupiahToAmount(income.total)
            binding.tvDesc.text = income.description
            binding.tvTitle.text = income.category.categoryName
            binding.imgIcon.setImageResource(income.category.categoryImage)
        }
    }

    companion object {
        private val DIFF_CALLBACK: DiffUtil.ItemCallback<IncomeEntity> =
            object : DiffUtil.ItemCallback<IncomeEntity>() {
                override fun areItemsTheSame(oldItem: IncomeEntity, newItem: IncomeEntity): Boolean {
                    return oldItem.id == newItem.id  && oldItem.id  == newItem.id
                }

                override fun areContentsTheSame(oldItem: IncomeEntity, newItem: IncomeEntity): Boolean {
                    return oldItem == newItem
                }

            }
    }

}