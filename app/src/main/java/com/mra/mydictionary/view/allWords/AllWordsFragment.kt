package com.mra.mydictionary.view.allWords

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.PopupWindow
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.mra.mydictionary.R
import com.mra.mydictionary.databinding.AllWordsEmptyLayoutBinding
import com.mra.mydictionary.databinding.AllWordsFragmentBinding
import com.mra.mydictionary.databinding.AllWordsListLayoutBinding
import com.mra.mydictionary.model.WordEntity
import com.mra.mydictionary.utils.Constance.FILTER_KEY
import com.mra.mydictionary.utils.launchFlowWhenResumed
import com.mra.mydictionary.view.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AllWordsFragment : BaseFragment<AllWordsFragmentBinding>(AllWordsFragmentBinding::inflate) {

    private val viewModel: AllWordsViewModel by viewModels()
    private var startPoint = 0
    private var filterType = FilterType.NEWEST
    private var wordsRecyclerViewAdapter: WordsRecyclerViewAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        findNavController().currentBackStackEntry?.savedStateHandle
            ?.getLiveData<String>(FILTER_KEY)?.observe(viewLifecycleOwner) { result ->
                filterType = toFilterType(result)
                viewModel.getAllWords(startPoint, filterType)
        }

        viewModel.getAllWords(startPoint, filterType)

        launchFlowWhenResumed(viewModel.allWordsStateFlow) { response ->

            response.data?.let {
                handleWordsListScreen(it)
            }

            response.errorMessage?.let {
                handleEmptyScreen(it)
            }

        }
    }

    private fun handleWordsListScreen(wordList: List<WordEntity>) {
        val wordsListViewBinding = AllWordsListLayoutBinding.inflate(LayoutInflater.from(context))
        binding?.root?.apply {
            removeAllViews()
            addView(wordsListViewBinding.root)
        }

        wordsListViewBinding.selectedFilter.apply {
            text = filterType.name
            setOnClickListener {
                openFilterMenu()
            }
        }


        wordsListViewBinding.createNewWordBtn.setOnClickListener {
            findNavController().navigate(R.id.action_allWordsFragment_to_insertNewWordFragment)
        }


        wordsRecyclerViewAdapter = WordsRecyclerViewAdapter()

        wordsListViewBinding.allWordsRecyclerView.apply {
            adapter = wordsRecyclerViewAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        }

        wordsRecyclerViewAdapter?.submitData(wordList)
    }

    private fun handleEmptyScreen(message: String) {
        val emptyViewBinding = AllWordsEmptyLayoutBinding.inflate(LayoutInflater.from(context))

        emptyViewBinding.emptyMessage.text = message

        emptyViewBinding.addNewWordBtn.setOnClickListener {
            findNavController().navigate(R.id.action_allWordsFragment_to_insertNewWordFragment)
        }

        binding?.root?.addView(emptyViewBinding.root)
    }


    private fun openFilterMenu() {
        findNavController().navigate(R.id.action_allWordsFragment_to_filterBottomSheet)
    }
}