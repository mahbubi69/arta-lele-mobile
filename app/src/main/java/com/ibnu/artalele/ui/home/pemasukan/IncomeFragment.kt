package com.ibnu.artalele.ui.home.pemasukan

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.ibnu.artalele.R
import com.ibnu.artalele.databinding.HomeFragmentBinding
import com.ibnu.artalele.databinding.IncomeFragmentBinding
import com.ibnu.artalele.di.ViewModelFactory
import com.ibnu.artalele.ui.extra.category.IncomeCategoryFragmentDirections
import com.ibnu.artalele.ui.home.HomeFragmentDirections
import com.ibnu.artalele.ui.home.pemasukan.adapter.IncomeAdapter
import com.ibnu.artalele.utils.ArtaLeleHelper
import com.ibnu.artalele.utils.ConstValue.INCOME
import com.ibnu.artalele.utils.RecyclerviewItemClickHandler
import java.time.Month

class IncomeFragment : Fragment() {

    private lateinit var adapter: IncomeAdapter

    private val viewModel by lazy {
        val factory = context?.applicationContext?.let { ViewModelFactory.getInstance(it) }
        factory?.let { ViewModelProvider(this, it) }?.get(IncomeViewModel::class.java)
    }

    private var _bindingIncomeFragment: IncomeFragmentBinding? = null
    private val binding get() = _bindingIncomeFragment

    private val onClick = object : RecyclerviewItemClickHandler {
        override fun onClickItem(id: Int) {
            val action = HomeFragmentDirections.actionHomeFragmentToDetailIncomeFragment(id)
            view?.findNavController()?.navigate(action)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _bindingIncomeFragment = IncomeFragmentBinding.inflate(inflater, container, false)
        return _bindingIncomeFragment?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initiateRecyclerview()
        initiateData()
    }

    private fun initiateRecyclerview() {
        adapter = IncomeAdapter(onClick)
        adapter.addLoadStateListener { loadState ->
            if(loadState.append.endOfPaginationReached) {
                if (adapter.itemCount < 1) {
                    showEmptyItemView(true)
                } else {
                    showEmptyItemView(false)
                }
            }
        }
        binding?.rvParentIncome?.adapter = adapter
        binding?.rvParentIncome?.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
    }

    private fun initiateData() {
        val day = ArtaLeleHelper.getCurrentDay()
        val month = ArtaLeleHelper.getCurrentMonth()

        viewModel?.getNewestIncome(day, month)?.observe(viewLifecycleOwner, Observer {
            adapter.submitData(lifecycle, it)
        })
        getTotalIncomeThisMonth(month)
    }

    private fun getTotalIncomeThisMonth(month: String) {
        viewModel?.getIncomeTotal(month = month)?.observe(viewLifecycleOwner, Observer { total ->
            binding?.tvTotalIncome?.text = ArtaLeleHelper.addRupiahToAmount(total)
        })
    }

    private fun showEmptyItemView(isShow: Boolean) {
        if (isShow) {
            binding?.rvParentIncome?.visibility = View.GONE
            binding?.btnAllIncome?.visibility = View.GONE
            binding?.viewEmpty?.root?.visibility = View.VISIBLE
            binding?.viewEmpty?.tvEmpty?.text = getString(R.string.empty_transaksi_text, INCOME, INCOME, INCOME)
        } else {
            binding?.rvParentIncome?.visibility = View.VISIBLE
            binding?.btnAllIncome?.visibility = View.VISIBLE
            binding?.viewEmpty?.root?.visibility = View.GONE
        }
     }
}