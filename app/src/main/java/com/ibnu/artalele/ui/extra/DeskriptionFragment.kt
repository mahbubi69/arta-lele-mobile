package com.ibnu.artalele.ui.extra

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.navigation.findNavController
import com.ibnu.artalele.R
import com.ibnu.artalele.databinding.FragmentDeskriptionBinding
import com.ibnu.artalele.utils.ConstValue
import com.ibnu.artalele.utils.SharedPreferencesManager

class DeskriptionFragment : Fragment() {

    private var _bindingFragmentDeskription: FragmentDeskriptionBinding? = null
    private val binding get() = _bindingFragmentDeskription

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _bindingFragmentDeskription = FragmentDeskriptionBinding.inflate(inflater, container, false)
        return _bindingFragmentDeskription?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initiateToolbarActions()
    }

    private fun initiateToolbarActions() {
        binding?.toolbarDescription?.tvToolbarTitle?.text = ""

        binding?.toolbarDescription?.btnSave?.setOnClickListener {
            val note = binding?.edtDescription?.text.toString()
            if (note.isEmpty()) {
                Toast.makeText(requireContext(), "Masih kosong", Toast.LENGTH_SHORT).show()
            } else {
                SharedPreferencesManager(requireContext()).setTransactionDescription(note)
                it.findNavController().popBackStack()
            }
        }
    }

}