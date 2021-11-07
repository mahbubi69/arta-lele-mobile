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
import com.ibnu.artalele.R
import com.ibnu.artalele.databinding.IncomeCategoryFragmentBinding
import com.ibnu.artalele.di.ViewModelFactory
import com.ibnu.artalele.ui.extra.category.adapter.CategoryPagingAdapter
import com.ibnu.artalele.utils.RecyclerviewItemClickHandler
import com.ibnu.artalele.utils.SharedPreferencesManager

class IncomeCategoryFragment : Fragment() {

    private lateinit var adapter: CategoryPagingAdapter

    private val viewModel by lazy {
        val factory = context?.applicationContext?.let { ViewModelFactory.getInstance(it) }
        factory?.let { ViewModelProvider(this, it) }?.get(IncomeCategoryViewModel::class.java)
    }

    private var _bindingIncomeCategoryFragment: IncomeCategoryFragmentBinding? = null
    private val binding get() = _bindingIncomeCategoryFragment

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
        _bindingIncomeCategoryFragment =
            IncomeCategoryFragmentBinding.inflate(inflater, container, false)
        return _bindingIncomeCategoryFragment?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initiateRecyclerview()
        initiateData()
        initiateButton()
    }

    private fun initiateRecyclerview() {
        adapter = CategoryPagingAdapter(itemClick)
        binding?.rvCategoryIncome?.adapter = adapter
        binding?.rvCategoryIncome?.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
    }

    private fun initiateData() {
        viewModel?.getCategories()?.observe(viewLifecycleOwner, Observer {
            adapter.submitData(lifecycle, it)
        })
    }

    private fun initiateButton() {
        binding?.addCategory?.setOnClickListener {
            view?.findNavController()?.navigate(R.id.action_categoryFragment_to_addCategoryFragment)
        }
    }

}