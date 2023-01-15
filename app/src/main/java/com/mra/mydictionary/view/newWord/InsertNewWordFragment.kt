package com.mra.mydictionary.view.newWord

import android.os.Bundle
import android.view.View
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.mra.mydictionary.R
import com.mra.mydictionary.databinding.InsertNewWordFragmentBinding
import com.mra.mydictionary.model.WordEntity
import com.mra.mydictionary.utils.backgroundBuilder
import com.mra.mydictionary.utils.launchFlowWhenResumed
import com.mra.mydictionary.utils.toast
import com.mra.mydictionary.view.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import java.util.Date

@AndroidEntryPoint
class InsertNewWordFragment :
    BaseFragment<InsertNewWordFragmentBinding>(InsertNewWordFragmentBinding::inflate) {

    private val viewModel: InsertNewWordViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.run {

            wordLayout.editText?.doOnTextChanged { text, start, before, count ->
                text?.let {
                    if (wordLayout.isErrorEnabled && text.isNotEmpty()) {
                        disableWordError()
                    } else if (text.isEmpty() && !wordLayout.isErrorEnabled)
                        enableWordError()
                }
            }

            insertWordBtn.apply {
//                background = backgroundBuilder(R.color.colorPrimary, corner = R.dimen.corner15)

                setOnClickListener {
                    val wordString = word.text.toString()
                    val descriptionString = description.text.toString()

                    if (wordString.isEmpty()) {
                        enableWordError()
//                        toast(R.string.emptyWordFieldErrorMessage)
                    } else
                        insertNewWord(
                            WordEntity(
                                word = wordString,
                                description = descriptionString,
                                creationDate = Date().time
                            )
                        )
                }
            }
        }


        launchFlowWhenResumed(viewModel.insertNewWordState) { response ->
            response.data?.let {
                toast(R.string.successfulWordInsertionMessage)
                findNavController().popBackStack()
            }

            response.errorMessage?.let {
                toast(it)
            }
        }
    }

    private fun insertNewWord(wordEntity: WordEntity) {
        viewModel.insertNewWord(wordEntity)
    }

    private fun enableWordError() {
        binding?.apply {
            context?.let { _context ->
                wordLayout.error = _context.getString(R.string.emptyWordFieldErrorMessage)
                wordLayout.isErrorEnabled = true
            }
        }
    }

    private fun disableWordError() {
        binding?.apply {
            wordLayout.isErrorEnabled = false
        }

    }

}