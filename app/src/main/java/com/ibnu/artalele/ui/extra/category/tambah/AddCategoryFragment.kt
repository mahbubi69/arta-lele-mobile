package com.ibnu.artalele.ui.extra.category.tambah

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.setFragmentResultListener
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.ibnu.artalele.R
import com.ibnu.artalele.data.entities.CategoryEntity
import com.ibnu.artalele.databinding.AddCategoryFragmentBinding
import com.ibnu.artalele.di.ViewModelFactory
import com.ibnu.artalele.utils.ArtaLeleHelper
import com.ibnu.artalele.utils.ConstValue
import com.ibnu.artalele.utils.ConstValue.INCOME
import com.ibnu.artalele.utils.ConstValue.SPENDING

class AddCategoryFragment : Fragment() {

    private var categoryType = ""
    private var imagePath = 0

    private var _bindingAddCategoryFragment: AddCategoryFragmentBinding? = null
    private val binding get() = _bindingAddCategoryFragment

    private val viewModel by lazy {
        val factory = context?.applicationContext?.let { ViewModelFactory.getInstance(it) }
        factory?.let { ViewModelProvider(this, it) }?.get(AddCategoryViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _bindingAddCategoryFragment = AddCategoryFragmentBinding.inflate(inflater, container, false)
        return _bindingAddCategoryFragment?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setFragmentResultListener(ConstValue.CATEGORY_IMAGE_REQUEST_KEY) { _, bundle ->
            imagePath = bundle.getInt(ConstValue.CATEGORY_IMAGE_RESULT_KEY)
            binding?.imgCategory?.setImageResource(imagePath)
        }

        binding?.toolbar?.tvToolbarTitle?.text = "Tambah kategori baru"

        binding?.imgCategory?.setOnClickListener {
            it.findNavController().navigate(R.id.action_addCategoryFragment_to_iconsFragment)
        }

        binding?.rdCategory?.setOnCheckedChangeListener { _, p1 ->
            when (p1) {
                R.id.radio_income -> {
                    categoryType = INCOME
                }
                R.id.radio_spending -> {
                    categoryType = SPENDING
                }
            }
        }
        binding?.toolbar?.btnSave?.setOnClickListener {
            val categoryName = binding?.edtKeperluan?.text.toString()
            when {
                categoryName.isEmpty() -> {
                    Toast.makeText(requireContext(), "Isi nama kategori", Toast.LENGTH_SHORT).show()
                }
                categoryType.isEmpty() -> {
                    Toast.makeText(requireContext(), "Pilih tipe kategory", Toast.LENGTH_SHORT).show()
                }
                imagePath == 0 -> {
                    Toast.makeText(requireContext(), "Belum memilih icon kategori!", Toast.LENGTH_SHORT).show()
                }
                else -> {
                    addCategory(
                        CategoryEntity(
                            categoryName = categoryName,
                            categoryGroup = categoryType,
                            categoryImage = imagePath
                        )
                    )
                }
            }
        }

        binding?.toolbar?.imgBack?.setOnClickListener {
            this.view?.findNavController()?.popBackStack()
        }
    }


    private fun addCategory(category: CategoryEntity) {
        try {
            viewModel?.addCategory(category)
        } catch (e: Exception){
            e.printStackTrace()
        } finally {
            view?.findNavController()?.popBackStack()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _bindingAddCategoryFragment = null
    }

}