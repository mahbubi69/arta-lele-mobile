package com.ibnu.artalele.ui.extra.category

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.ibnu.artalele.databinding.SpendingCategoryFragmentBinding
import com.ibnu.artalele.di.ViewModelFactory
import com.ibnu.artalele.ui.extra.category.adapter.CategoryPagingAdapter
import com.ibnu.artalele.utils.RecyclerviewItemClickHandler
import com.ibnu.artalele.utils.SharedPreferencesManager

class SpendingCategoryFragment : Fragment() {

    private lateinit var adapter: CategoryPagingAdapter

    private val viewModel by lazy {
        val factory = context?.applicationContext?.let { ViewModelFactory.getInstance(it) }
        factory?.let { ViewModelProvider(this, it) }?.get(SpendingCategoryViewModel::class.java)
    }

    private var _bindingSpendingCategoryFragment: SpendingCategoryFragmentBinding? = null
    private val binding get() = _bindingSpendingCategoryFragment

    private val itemClick = object: RecyclerviewItemClickHandler {
        override fun onClickItem(id: Int) {
            SharedPreferencesManager(requireContext()).setTransactionCategory(id)
            view?.findNavController()?.popBackStack()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _bindingSpendingCategoryFragment = SpendingCategoryFragmentBinding.inflate(inflater, container, false)
        return _bindingSpendingCategoryFragment?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initiateRecyclerview()
        initiateData()
    }

    private fun initiateRecyclerview() {
        adapter = CategoryPagingAdapter(itemClick)
        binding?.rvCategorySpending?.adapter = adapter
        binding?.rvCategorySpending?.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
    }

    private fun initiateData() {
        viewModel?.getCategories()?.observe(viewLifecycleOwner, Observer {
            adapter.submitData(lifecycle, it)
        })
    }

}