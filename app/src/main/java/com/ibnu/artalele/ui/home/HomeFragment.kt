package com.ibnu.artalele.ui.home

import android.content.Context
import android.os.Bundle
import android.os.UserManager
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.google.android.material.tabs.TabLayoutMediator
import com.ibnu.artalele.R
import com.ibnu.artalele.databinding.HomeFragmentBinding
import com.ibnu.artalele.utils.SharedPreferencesManager

class HomeFragment : Fragment() {

    private lateinit var viewModel: HomeViewModel

    private var _bindingHomeFragment: HomeFragmentBinding? = null
    private val binding get() = _bindingHomeFragment

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _bindingHomeFragment = HomeFragmentBinding.inflate(inflater, container, false)
        return _bindingHomeFragment?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initiateViewPager(requireContext())
        initiateButton()
    }

    private fun initiateViewPager(context: Context) {

        val titles = intArrayOf(
            R.string.pemasukan,
            R.string.pengeluaran
        )

        val homeViewpager = HomeViewPagerAdapter(requireActivity(), titles)
        binding?.transacationViewpager?.adapter = homeViewpager

        TabLayoutMediator(
            binding?.homeTablayout!!, binding?.transacationViewpager!!
        ) { tab, position ->
            tab.text = context.resources.getString(titles[position])
        }.attach()
    }

    private fun initiateButton() {
        binding?.fabAddTransaction?.setOnClickListener {
            it.findNavController().navigate(R.id.action_homeFragment_to_tambahTransaksiFragment)
            SharedPreferencesManager(requireContext()).resetTransactionSegment()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _bindingHomeFragment = null
    }
}