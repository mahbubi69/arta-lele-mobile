package com.ibnu.artalele.ui.extra.category

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.tabs.TabLayoutMediator
import com.ibnu.artalele.R
import com.ibnu.artalele.databinding.FragmentCategoryBinding
import com.ibnu.artalele.ui.extra.category.adapter.CategoryViewPagerAdapter

class CategoryFragment : Fragment() {

    private var _bindingFragmentCategory: FragmentCategoryBinding? = null
    private val binding get() = _bindingFragmentCategory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _bindingFragmentCategory = FragmentCategoryBinding.inflate(inflater, container, false)
        return _bindingFragmentCategory?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initiateViewPager(requireContext())
    }

    private fun initiateViewPager(context: Context) {
        val titles = intArrayOf(
            R.string.jenis_pemasukan,
            R.string.jenis_pengeluaran
        )

        val viewPagerAdapter = CategoryViewPagerAdapter(requireActivity(), titles)
        binding?.categoryViewpager?.adapter = viewPagerAdapter

        TabLayoutMediator(binding?.categoryTablayout!!, binding?.categoryViewpager!!){ tab, position ->
            tab.text = context.resources.getString(titles[position])
        }.attach()
    }

    override fun onDestroy() {
        super.onDestroy()
        _bindingFragmentCategory = null
    }

}