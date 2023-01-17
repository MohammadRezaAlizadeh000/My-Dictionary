package com.mra.mydictionary.view.allWords

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.radiobutton.MaterialRadioButton
import com.mra.mydictionary.databinding.FilterBottomSheetBinding
import com.mra.mydictionary.utils.Constance
import com.mra.mydictionary.utils.Constance.FILTER_KEY
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FilterBottomSheet: BottomSheetDialogFragment() {

    private var _binding: FilterBottomSheetBinding? = null
    private val binding  get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FilterBottomSheetBinding.inflate(inflater)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.run {
            filterRadioGroup.setOnCheckedChangeListener { radioGroup, i ->

                val filter = root.findViewById<MaterialRadioButton>(radioGroup.checkedRadioButtonId).text
                findNavController().previousBackStackEntry?.savedStateHandle
                    ?.set(FILTER_KEY, filter)
                dismiss()
            }
        }
    }


    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}