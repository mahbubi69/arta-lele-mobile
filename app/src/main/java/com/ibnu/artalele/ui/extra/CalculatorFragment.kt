package com.ibnu.artalele.ui.extra

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.navigation.findNavController
import com.ibnu.artalele.databinding.FragmentCalculatorBinding
import com.ibnu.artalele.utils.ArtaLeleHelper
import com.ibnu.artalele.utils.ConstValue.CALCULATOR_HUTANG
import com.ibnu.artalele.utils.ConstValue.CALCULATOR_INCOME
import com.ibnu.artalele.utils.ConstValue.CALCULATOR_SPENDING
import com.ibnu.artalele.utils.ConstValue.DEBT_REQUEST_KEY
import com.ibnu.artalele.utils.ConstValue.DEBT_RESULT_KEY
import com.ibnu.artalele.utils.ConstValue.INCOME_REQUEST_KEY
import com.ibnu.artalele.utils.ConstValue.INCOME_RESULT_KEY
import com.ibnu.artalele.utils.ConstValue.SPENDING_REQUEST_KEY
import com.ibnu.artalele.utils.ConstValue.SPENDING_RESULT_KEY
import com.ibnu.artalele.utils.SharedPreferencesManager
import net.objecthunter.exp4j.ExpressionBuilder
import timber.log.Timber

class CalculatorFragment : Fragment() {

    private var total: String = "0"
    private var realResult = 0.0
    private var isNew: Boolean = true
    private var isComma: Boolean = false


    private var _calculatorFragment: FragmentCalculatorBinding? = null
    private val binding get() = _calculatorFragment

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _calculatorFragment = FragmentCalculatorBinding.inflate(inflater, container, false)
        return _calculatorFragment?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val safeArgs = arguments?.let { CalculatorFragmentArgs.fromBundle(it) }

        numberClickEvent()
        initiateToolbar(safeArgs?.passToolbarName ?: "-")

    }

    private fun initiateToolbar(title: String) {
        binding?.toolbarCalculator?.tvToolbarTitle?.text = title

        binding?.toolbarCalculator?.btnSave?.setOnClickListener {
            saveTotal(it, title)
        }

        binding?.btnGo?.setOnClickListener {
            saveTotal(it, title)
        }

        binding?.toolbarCalculator?.imgBack?.setOnClickListener {
            it.findNavController().popBackStack()
        }
    }

    private fun numberClickEvent() {
        if (isNew) {
            total = ""
            binding?.tvResult?.text = total
        }
        isNew = false

        binding?.btnZero?.setOnClickListener {
            appendNumberAndDisplay("0", true)
        }

        binding?.btnOne?.setOnClickListener {
            appendNumberAndDisplay("1", true)
        }
        binding?.btnTwo?.setOnClickListener {
            appendNumberAndDisplay("2", true)
        }
        binding?.btnThree?.setOnClickListener {
            appendNumberAndDisplay("3", true)
        }
        binding?.btnFour?.setOnClickListener {
            appendNumberAndDisplay("4", true)
        }
        binding?.btnFive?.setOnClickListener {
            appendNumberAndDisplay("5", true)
        }
        binding?.btnSix?.setOnClickListener {
            appendNumberAndDisplay("6", true)
        }
        binding?.btnSeven?.setOnClickListener {
            appendNumberAndDisplay("7", true)
        }
        binding?.btnEight?.setOnClickListener {
            appendNumberAndDisplay("8", true)
        }
        binding?.btnNine?.setOnClickListener {
            appendNumberAndDisplay("9", true)
        }
        binding?.btnTripleZeros?.setOnClickListener {
            appendNumberAndDisplay("000", true)
        }

        //operator
        binding?.btnComma?.setOnClickListener {
            isComma = true
            appendNumberAndDisplay(".", false)
        }

        binding?.btnBagi?.setOnClickListener {
            binding?.btnEqual?.visibility = View.VISIBLE
            appendNumberAndDisplay("/", false)
        }

        binding?.btnPlus?.setOnClickListener {
            binding?.btnEqual?.visibility = View.VISIBLE
            appendNumberAndDisplay("+", false)

        }
        binding?.btnMinus?.setOnClickListener {
            binding?.btnEqual?.visibility = View.VISIBLE
            appendNumberAndDisplay("-", false)

        }

        binding?.btnKali?.setOnClickListener {
            binding?.btnEqual?.visibility = View.VISIBLE
            appendNumberAndDisplay("*", false)
        }

        binding?.btnEqual?.setOnClickListener {
            val expression = ExpressionBuilder(binding?.tvExpression?.text.toString()).build()
            val result = expression.evaluate()
            val longResult = result.toLong()
            if (result == longResult.toDouble()) {
                realResult = result
                binding?.tvResult?.text = result.toString()
            } else {
                realResult = result
                binding?.tvResult?.text = result.toString()
            }
            binding?.btnEqual?.visibility = View.GONE
        }

        binding?.btnDelete?.setOnClickListener {
            val dlt = binding?.tvExpression?.text.toString()
            if (dlt.isNotEmpty()) {
                binding?.tvExpression?.text = dlt.substring(0, dlt.length - 1)
            }
            binding?.tvResult?.text = ""
        }

        binding?.btnReset?.setOnClickListener {
            binding?.tvExpression?.text = ""
            binding?.tvResult?.text = ""
        }
    }

    private fun saveTotal(view: View, type: String) {
        val expressionText = binding?.tvExpression?.text.toString()
        val resultText = binding?.tvResult?.text.toString()

        if (resultText.isEmpty() || resultText.isBlank()) {
            validateSaveTotal(expressionText, type)
        } else {
            validateSaveTotal(resultText, type)
        }
    }

    private fun validateSaveTotal(result: String, type: String){
        val saver = SharedPreferencesManager(requireContext())
        val helper = ArtaLeleHelper
        when (type) {
            CALCULATOR_INCOME -> {
                val weight = helper.convertStringToLong(result)
                saver.setTransactionWeight(weight)
                saver.deleteResult()
                view?.findNavController()?.popBackStack()
            }
            CALCULATOR_SPENDING -> {
                val spending = helper.convertStringToNumberOnly(result)
                saver.setTransactionResult(spending)
                saver.deleteWeight()
                view?.findNavController()?.popBackStack()
            }
            CALCULATOR_HUTANG -> {
                setFragmentResult(DEBT_REQUEST_KEY, bundleOf(DEBT_RESULT_KEY to result))
                view?.findNavController()?.popBackStack()
            }
            else -> {
                Timber.e("Unkown type")
            }
        }
    }

    private fun appendNumberAndDisplay(value: String, canClear: Boolean) {
        if (canClear) {
            binding?.tvResult?.text = ""
            binding?.tvExpression?.append(value)
        } else {
            binding?.tvExpression?.append(value)
            binding?.tvResult?.text = ""
        }
    }
}
