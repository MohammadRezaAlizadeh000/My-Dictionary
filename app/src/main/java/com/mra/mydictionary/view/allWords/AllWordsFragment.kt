package com.mra.mydictionary.view.allWords

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.PopupWindow
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.lifecycle.coroutineScope
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
import com.mra.mydictionary.view.allWords.screen.AllWordsListView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class AllWordsFragment : BaseFragment<AllWordsFragmentBinding>(AllWordsFragmentBinding::inflate) {

    private val viewModel: AllWordsViewModel by viewModels()
    private var filterType = FilterType.NEWEST
    private var allWordsListView: AllWordsListView? = null


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        findNavController().currentBackStackEntry?.savedStateHandle
            ?.getLiveData<String>(FILTER_KEY)?.observe(viewLifecycleOwner) { onFilterChanged(it) }

        viewModel.getAllWords(filterType)

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
        context?.let { _context ->
            if (null == allWordsListView) {
                allWordsListView = AllWordsListView(
                    _context,
                    recyclerViewPosition = { newPosition ->
                        viewModel.recyclerViewPosition = newPosition ?: viewModel.recyclerViewPosition
                        viewModel.recyclerViewPosition
                    },
                    createNewWordClickListener = {
                        findNavController().navigate(R.id.action_allWordsFragment_to_insertNewWordFragment)
                    },
                    openFilterMenuClickListener = {
                        findNavController().navigate(R.id.action_allWordsFragment_to_filterBottomSheet)
                        filterType
                    },
                    loadMore = {
                        if (!viewModel.isLoadMore) {
                            viewModel.startPoint += 20
                            viewModel.loadMoreWords(filterType)
                            viewModel.isLoadMore = it
                        }
                    }
                )
            }
        }

        allWordsListView?.setFilterTitle(filterType)

        binding?.root?.apply {
            removeAllViews()
            addView(allWordsListView)
            allWordsListView?.setData(wordList, viewModel.isLoadMore) {
                viewModel.isLoadMore = it
            }
        }


    }

    private fun handleEmptyScreen(message: String) {
        val emptyViewBinding = AllWordsEmptyLayoutBinding.inflate(LayoutInflater.from(context))

        emptyViewBinding.emptyMessage.text = message

        emptyViewBinding.addNewWordBtn.setOnClickListener {
            findNavController().navigate(R.id.action_allWordsFragment_to_insertNewWordFragment)
        }

        binding?.root?.addView(emptyViewBinding.root)
    }

    private fun onFilterChanged(selectedFilter: String) {
        filterType = toFilterType(selectedFilter)
        viewModel.startPoint = 0
        allWordsListView?.setFilterTitle(filterType)
        allWordsListView?.createNewAdapter()
        viewModel.getAllWords(filterType)
    }

}