package com.ibnu.artalele.ui.extra.category.tambah

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.navigation.findNavController
import com.ibnu.artalele.R
import com.ibnu.artalele.databinding.FragmentIconsBinding
import com.ibnu.artalele.databinding.TambahTransaksiFragmentBinding
import com.ibnu.artalele.utils.ConstValue

class IconsFragment : Fragment(), View.OnClickListener {

    private var _bindingIconFragment: FragmentIconsBinding? = null
    private val binding get() = _bindingIconFragment

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _bindingIconFragment = FragmentIconsBinding.inflate(inflater, container, false)
        return _bindingIconFragment?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initiateViews()
    }

    private fun initiateViews() {
        binding?.imgFish?.setOnClickListener(this)
        binding?.imgEel?.setOnClickListener(this)
        binding?.imgPaid?.setOnClickListener(this)
        binding?.imgPills?.setOnClickListener(this)
        binding?.imgGrocery?.setOnClickListener(this)
        binding?.imgPerson?.setOnClickListener(this)
        binding?.imgWeight?.setOnClickListener(this)
        binding?.imgDocument?.setOnClickListener(this)
        binding?.imgInvoice?.setOnClickListener(this)
        binding?.imgMeat?.setOnClickListener(this)
        binding?.imgSheep?.setOnClickListener(this)
        binding?.imgParasite?.setOnClickListener(this)
        binding?.imgDelivery?.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.img_fish -> {
                getImageClickAction(R.drawable.ic_tuna)
            }
            R.id.img_eel -> {
                getImageClickAction(R.drawable.ic_eel)
            }
            R.id.img_paid -> {
                getImageClickAction(R.drawable.ic_paid)
            }
            R.id.img_pills -> {
                getImageClickAction(R.drawable.ic_pills)
            }
            R.id.img_grocery -> {
                getImageClickAction(R.drawable.ic_pushcart)
            }
            R.id.img_person -> {
                getImageClickAction(R.drawable.ic_tax)
            }
            R.id.img_weight -> {
                getImageClickAction(R.drawable.ic_weight)
            }
            R.id.img_document -> {
                getImageClickAction(R.drawable.ic_documents)
            }
            R.id.img_invoice -> {
                getImageClickAction(R.drawable.ic_invoice)
            }
            R.id.img_meat -> {
                getImageClickAction(R.drawable.ic_meat)
            }
            R.id.img_sheep -> {
                getImageClickAction(R.drawable.ic_sheep)
            }
            R.id.img_parasite -> {
                getImageClickAction(R.drawable.ic_loupe)
            }
            R.id.img_delivery -> {
                getImageClickAction(R.drawable.ic_delivery)
            }
        }
    }

    private fun getImageClickAction(path: Int) {
        setFragmentResult(
            ConstValue.CATEGORY_IMAGE_REQUEST_KEY,
            bundleOf(ConstValue.CATEGORY_IMAGE_RESULT_KEY to path)
        )
        view?.findNavController()?.popBackStack()
    }


}