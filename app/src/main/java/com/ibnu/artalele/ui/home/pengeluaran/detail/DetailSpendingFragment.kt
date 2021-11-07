package com.ibnu.artalele.ui.home.pengeluaran.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.ibnu.artalele.databinding.DetailSpendingFragmentBinding
import com.ibnu.artalele.di.ViewModelFactory
import com.ibnu.artalele.ui.home.pemasukan.detail.DetailIncomeFragmentArgs
import com.ibnu.artalele.utils.ArtaLeleHelper

class DetailSpendingFragment : Fragment() {

    private val viewModel by lazy {
        val factory = context?.applicationContext?.let { ViewModelFactory.getInstance(it) }
        factory?.let { ViewModelProvider(this, it) }?.get(DetailSpendingViewModel::class.java)
    }

    private var _bindingDetailSpendingFragment: DetailSpendingFragmentBinding? = null
    private val binding get() = _bindingDetailSpendingFragment

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _bindingDetailSpendingFragment = DetailSpendingFragmentBinding.inflate(inflater, container, false)
        return _bindingDetailSpendingFragment?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val safeArgs = arguments?.let { DetailIncomeFragmentArgs.fromBundle(it) }
        val id = safeArgs?.passId ?: 0

        initiateViews(id)
        initiateToolbarButton(id)
    }

    private fun initiateViews(id: Int) {
        viewModel?.getDetailSpending(id)?.observe(viewLifecycleOwner, Observer {
            binding?.tvDate?.text = "${it.day} ${it.month} ${it.year}"
            binding?.tvGrandTotal?.text = ArtaLeleHelper.addRupiahToAmount(it.total)
            binding?.tvNote?.text = it.description
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
            deleteSpending(id, it)
        }
    }

    private fun deleteSpending(id: Int, view: View) {
        AlertDialog.Builder(requireContext()).apply {
            setTitle("Hapus Transaksi")
            setMessage("Apakah Anda yakin untuk transaksi ini?")
            setNegativeButton("Tidak") { p0, _ ->
                p0.dismiss()
            }
            setPositiveButton("IYA") { _, _ ->
                try {
                    viewModel?.deleteSpending(id)
                } catch (e: Exception) {
                    e.printStackTrace()
                } finally {
                    view.findNavController().popBackStack()
                }
            }
        }.create().show()
    }

}