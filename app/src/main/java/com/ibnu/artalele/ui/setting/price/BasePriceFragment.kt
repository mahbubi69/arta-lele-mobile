package com.ibnu.artalele.ui.setting.price

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.ibnu.artalele.R
import com.ibnu.artalele.databinding.FragmentBasePriceBinding
import com.ibnu.artalele.databinding.HomeFragmentBinding
import com.ibnu.artalele.utils.ArtaLeleHelper
import com.ibnu.artalele.utils.SharedPreferencesManager

class BasePriceFragment : Fragment(), View.OnClickListener {

    private var _bindingBasePriceFragment: FragmentBasePriceBinding? = null
    private val binding get() = _bindingBasePriceFragment

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _bindingBasePriceFragment = FragmentBasePriceBinding.inflate(inflater, container, false)
        return _bindingBasePriceFragment?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.toolbar?.imgBack?.setOnClickListener(this)
        binding?.toolbar?.btnSave?.setOnClickListener(this)
        binding?.toolbar?.tvToolbarTitle?.text = "Base Harga"
        binding?.tvCurrentPrice?.text =
            ArtaLeleHelper.addRupiahToAmount(SharedPreferencesManager(requireContext()).getPrice)
    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.img_back -> {
                view?.findNavController()?.popBackStack()
            }
            R.id.btn_save -> {
                val price = Integer.parseInt(binding?.edtBasePrice?.text.toString())

                SharedPreferencesManager(requireContext()).setBasePrice(price)
            }
        }
    }

}