package com.ibnu.artalele.ui.hutang.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.ibnu.artalele.data.entities.DebtEntity
import com.ibnu.artalele.databinding.HutangItemBinding
import com.ibnu.artalele.utils.ArtaLeleHelper
import com.ibnu.artalele.utils.RecyclerviewItemClickHandler

class BukuHutangAdapter(private val onClickAction: RecyclerviewItemClickHandler) :
    PagingDataAdapter<DebtEntity, BukuHutangAdapter.BukuHutangViewHolder>(
        DIFF_CALLBACK
    ) {

    override fun onBindViewHolder(holder: BukuHutangViewHolder, position: Int) {
        getItem(position)?.let { debt ->
            holder.bind(debt)
            holder.itemView.setOnClickListener {
                onClickAction.onClickItem(debt.id)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BukuHutangViewHolder {
        val binding =
            HutangItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BukuHutangViewHolder(binding)
    }


    inner class BukuHutangViewHolder(private val binding: HutangItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(debt: DebtEntity) {
            binding.tvName.text = debt.name
            val formattedAmount = ArtaLeleHelper.addRupiahToAmount(debt.amount)
            binding.tvValue.text = formattedAmount
            binding.tvDate.text = debt.startDate
        }
    }

    companion object {
        private val DIFF_CALLBACK: DiffUtil.ItemCallback<DebtEntity> =
            object : DiffUtil.ItemCallback<DebtEntity>() {
                override fun areItemsTheSame(oldItem: DebtEntity, newItem: DebtEntity): Boolean {
                    return oldItem.id == newItem.id && oldItem.id == newItem.id
                }

                override fun areContentsTheSame(oldItem: DebtEntity, newItem: DebtEntity): Boolean {
                    return oldItem == newItem
                }

            }
    }

}