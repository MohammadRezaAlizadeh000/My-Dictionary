package com.mra.mydictionary.view.newWord

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.mra.mydictionary.databinding.InsertNewWordFragmentBinding
import com.mra.mydictionary.model.WordEntity
import com.mra.mydictionary.utils.launchFlowWhenResumed
import com.mra.mydictionary.utils.toast
import com.mra.mydictionary.view.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class InsertNewWordFragment :
    BaseFragment<InsertNewWordFragmentBinding>(InsertNewWordFragmentBinding::inflate) {

    private val viewModel: InsertNewWordViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.run {
            insertWordBtn.setOnClickListener {
                val wordString = word.text.toString()
                val descriptionString = description.text.toString()

                if (wordString.isEmpty())
                    toast("You should fill Word Input")
                else
                    insertNewWord(WordEntity(word = wordString, description = descriptionString))
            }
        }


        launchFlowWhenResumed(viewModel.insertNewWordState) { response ->
            response.data?.let {
                toast("Word inserted successfully")
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

}