package com.mra.mydictionary.view.allWords

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.mra.mydictionary.databinding.FilterBottomSheetBinding

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


    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}