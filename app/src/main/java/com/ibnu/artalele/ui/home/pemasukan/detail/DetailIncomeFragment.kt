package com.ibnu.artalele.ui.home.pemasukan.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.ibnu.artalele.databinding.DetailIncomeFragmentBinding
import com.ibnu.artalele.di.ViewModelFactory
import com.ibnu.artalele.utils.ArtaLeleHelper

class DetailIncomeFragment : Fragment() {

    private val viewModel by lazy {
        val factory = context?.applicationContext?.let { ViewModelFactory.getInstance(it) }
        factory?.let { ViewModelProvider(this, it) }?.get(DetailIncomeViewModel::class.java)
    }

    private var _bindingIncomeDetailFragment: DetailIncomeFragmentBinding? = null
    private val binding get() = _bindingIncomeDetailFragment

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _bindingIncomeDetailFragment =
            DetailIncomeFragmentBinding.inflate(inflater, container, false)
        return _bindingIncomeDetailFragment?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val safeArgs = arguments?.let { DetailIncomeFragmentArgs.fromBundle(it) }
        val id = safeArgs?.passId ?: 0

        initiateViews(id)
        initiateToolbarButton(id)
    }

    private fun initiateViews(id: Int) {
        viewModel?.getIncomeDetail(id)?.observe(viewLifecycleOwner, Observer {
            binding?.tvDate?.text = "${it.day} ${it.month} ${it.year}"
            binding?.tvGrandTotal?.text = ArtaLeleHelper.addRupiahToAmount(it.total)
            binding?.tvNote?.text = it.description
            binding?.tvWeight?.text = it.weight
            binding?.tvCategoryName?.text = it.category.categoryName
            binding?.imgCategory?.setImageResource(it.category.categoryImage)
        })
    }

    private fun initiateToolbarButton(id: Int) {
        val toolbar = binding?.toolbar

        toolbar?.btnClose?.setOnClickListener {
            view?.findNavController()?.popBackStack()
        }

        toolbar?.btnEdit?.setOnClickListener {

        }

        toolbar?.btnDeleteToolbar?.setOnClickListener {
            deleteIncome(id, it)
        }
    }

    private fun deleteIncome(id: Int, view: View) {
        AlertDialog.Builder(requireContext()).apply {
            setTitle("Hapus Transaksi")
            setMessage("Apakah Anda yakin untuk transaksi ini?")
            setNegativeButton("Tidak") { p0, _ ->
                p0.dismiss()
            }
            setPositiveButton("IYA") { _, _ ->
                try {
                    viewModel?.deleteIncome(id)
                } catch (e: Exception) {
                    e.printStackTrace()
                } finally {
                    view.findNavController().popBackStack()
                }
            }
        }.create().show()
    }


    override fun onDestroy() {
        super.onDestroy()
        _bindingIncomeDetailFragment = null
    }
}