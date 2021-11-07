package com.ibnu.artalele.ui.home.pengeluaran

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
import com.ibnu.artalele.databinding.SpendingFragmentBinding
import com.ibnu.artalele.di.ViewModelFactory
import com.ibnu.artalele.ui.home.HomeFragmentDirections
import com.ibnu.artalele.utils.ArtaLeleHelper
import com.ibnu.artalele.utils.ConstValue
import com.ibnu.artalele.utils.RecyclerviewItemClickHandler

class SpendingFragment : Fragment() {

    private lateinit var adapter: SpendingAdapter

    private val viewModel by lazy {
        val factory = context?.applicationContext?.let { ViewModelFactory.getInstance(it) }
        factory?.let { ViewModelProvider(this, it) }?.get(SpendingViewModel::class.java)
    }

    private var _bindingSpendingFragment: SpendingFragmentBinding? = null
    private val binding get() = _bindingSpendingFragment

    private val onClick = object : RecyclerviewItemClickHandler {
        override fun onClickItem(id: Int) {
            val action = HomeFragmentDirections.actionHomeFragmentToDetailSpendingFragment(id)
            view?.findNavController()?.navigate(action)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _bindingSpendingFragment = SpendingFragmentBinding.inflate(inflater, container, false)
        return _bindingSpendingFragment?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initiateRecyclerview()
        initiateData()
    }

    private fun initiateRecyclerview() {
        adapter = SpendingAdapter(onClick)
        adapter.addLoadStateListener { loadState ->
            if (loadState.append.endOfPaginationReached) {
                if (adapter.itemCount < 1) {
                    showEmptyItemView(true)
                } else {
                    showEmptyItemView(false)
                }
            }
        }
        binding?.rvParentSpending?.adapter = adapter
        binding?.rvParentSpending?.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
    }

    private fun initiateData() {
        val day = ArtaLeleHelper.getCurrentDay()
        val month = ArtaLeleHelper.getCurrentMonth()

        viewModel?.getNewestSpending(day, month)?.observe(viewLifecycleOwner, Observer {
            adapter.submitData(lifecycle, it)
        })

        viewModel?.getThisMonthTotalSpending(month = month)
            ?.observe(viewLifecycleOwner, Observer { total ->
                binding?.tvTotalSpending?.text = ArtaLeleHelper.addRupiahToAmount(total)
            })
    }

    private fun showEmptyItemView(isShow: Boolean) {
        if (isShow) {
            binding?.rvParentSpending?.visibility = View.GONE
            binding?.btnAllSpending?.visibility = View.GONE
            binding?.viewEmpty?.root?.visibility = View.VISIBLE
            binding?.viewEmpty?.tvEmpty?.text = getString(
                R.string.empty_transaksi_text,
                ConstValue.SPENDING,
                ConstValue.SPENDING,
                ConstValue.SPENDING
            )
        } else {
            binding?.rvParentSpending?.visibility = View.VISIBLE
            binding?.btnAllSpending?.visibility = View.VISIBLE
            binding?.viewEmpty?.root?.visibility = View.GONE
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _bindingSpendingFragment = null
    }

}