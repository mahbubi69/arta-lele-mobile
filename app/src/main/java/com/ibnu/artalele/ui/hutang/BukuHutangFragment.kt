package com.ibnu.artalele.ui.hutang

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.ibnu.artalele.R
import com.ibnu.artalele.databinding.BukuHutangFragmentBinding
import com.ibnu.artalele.di.ViewModelFactory
import com.ibnu.artalele.ui.hutang.adapter.BukuHutangAdapter
import com.ibnu.artalele.utils.ArtaLeleHelper
import com.ibnu.artalele.utils.RecyclerviewItemClickHandler
import com.ibnu.artalele.utils.type.TypeUtils.NEW_DEBT
import com.ibnu.artalele.utils.type.TypeUtils.OLD_DEBT

class BukuHutangFragment : Fragment() {

    private lateinit var adapter: BukuHutangAdapter
    private var sortType = NEW_DEBT

    private val viewModel by lazy {
        val factory = context?.applicationContext?.let { ViewModelFactory.getInstance(it) }
        factory?.let { ViewModelProvider(this, it) }?.get(BukuHutangViewModel::class.java)
    }

    private val onItemClick = object : RecyclerviewItemClickHandler {
        override fun onClickItem(id: Int) {
            val action =
                BukuHutangFragmentDirections.actionBukuHutangFragmentToDetailHutangFragment(id)
            view?.findNavController()?.navigate(action)
        }

    }

    private var _bindingBukuHutangFragment: BukuHutangFragmentBinding? = null
    private val binding get() = _bindingBukuHutangFragment

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _bindingBukuHutangFragment = BukuHutangFragmentBinding.inflate(inflater, container, false)
        return _bindingBukuHutangFragment?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.fabAddDebt?.setOnClickListener {
            it.findNavController().navigate(R.id.action_bukuHutangFragment_to_tambahHutangFragment)
        }

        initiateRecyclerView()
        initiateTotalAmount()
        loadData(NEW_DEBT)
        initiateSortByButton()
        initiateSearchAction()
    }

    private fun initiateRecyclerView() {
        adapter = BukuHutangAdapter(onItemClick)
        binding?.rvHutang?.adapter = adapter
        binding?.rvHutang?.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding?.rvHutang?.addItemDecoration(
            DividerItemDecoration(
                binding?.rvHutang?.context,
                DividerItemDecoration.VERTICAL
            )
        )
    }

    private fun initiateTotalAmount() {
        viewModel?.getTotalAmountDebt()?.observe(viewLifecycleOwner, Observer {
            val formattedTotal = ArtaLeleHelper.addRupiahToAmount(it)
            binding?.tvTotalHutang?.text = formattedTotal
        })
    }

    private fun loadData(type: String) {
        viewModel?.getDebts(type)?.observe(viewLifecycleOwner, Observer {
            adapter.submitData(lifecycle, it)
        })
    }

    private fun loadSearchData(keyword: String) {
        viewModel?.getSearchDebt(keyword)?.observe(viewLifecycleOwner, Observer {
            adapter.submitData(lifecycle, it)
        })
    }

    private fun initiateSortByButton() {
        binding?.btnSort?.setOnClickListener {
            when (sortType) {
                NEW_DEBT -> {
                    sortType = OLD_DEBT
                    loadData(OLD_DEBT)
                    binding?.btnSort?.setImageResource(R.drawable.ic_swap_blue)
                }
                OLD_DEBT -> {
                    sortType = NEW_DEBT
                    loadData(NEW_DEBT)
                    binding?.btnSort?.setImageResource(R.drawable.ic_swap)
                }
            }
        }
    }

    private fun initiateSearchAction() {
        binding?.svHutang?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                loadSearchData("\"$query\"")
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText.isNullOrEmpty() || newText.isBlank()) {
                    loadData(sortType)
                }
                return false
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _bindingBukuHutangFragment = null
    }

}