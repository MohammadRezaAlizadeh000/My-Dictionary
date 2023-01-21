package com.mra.mydictionary.view.allWords.screen

import android.content.Context
import android.os.Handler
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mra.mydictionary.databinding.AllWordsListLayoutBinding
import com.mra.mydictionary.model.WordEntity
import com.mra.mydictionary.view.allWords.FilterType
import com.mra.mydictionary.view.allWords.WordsRecyclerViewAdapter

class AllWordsListView(
    context: Context,
    private val recyclerViewPosition: (Int?) -> Int,
    private val createNewWordClickListener: () -> Unit,
    private val openFilterMenuClickListener: () -> Unit,
    private val loadMore: (Boolean) -> Unit,
) : FrameLayout(context) {

    private var currentScrollPosition = 0
    private val binding =
        AllWordsListLayoutBinding.inflate(LayoutInflater.from(context), this, true)
    private var wordsRecyclerViewAdapter: WordsRecyclerViewAdapter? = null

    init {
        initData()
    }

    fun setFilterTitle(filterType: FilterType) {
        binding.selectedFilter.text = filterType.name
    }

    fun createNewAdapter() {
        wordsRecyclerViewAdapter = null
        wordsRecyclerViewAdapter = WordsRecyclerViewAdapter()
        binding.allWordsRecyclerView.adapter = wordsRecyclerViewAdapter
    }

    private fun initData() {
        with(binding) {
            selectedFilter.apply {
                setOnClickListener {
                    openFilterMenuClickListener.invoke()
                }
            }


            createNewWordBtn.setOnClickListener {
                createNewWordClickListener.invoke()
            }


            if (null == wordsRecyclerViewAdapter) {
                wordsRecyclerViewAdapter = WordsRecyclerViewAdapter()/* {
                    loadMore.invoke()
                }*/

                allWordsRecyclerView.apply {
                    adapter = wordsRecyclerViewAdapter
                    layoutManager =
                        LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

                }
            }
        }
    }

    fun recyclerViewLayoutManager() =
        (binding.allWordsRecyclerView.layoutManager as LinearLayoutManager)

    fun setData(
        wordList: List<WordEntity>,
        isLoadMore: Boolean,
        turnOffLoadMode: (Boolean) -> Unit
    ) {
        if (isLoadMore) {
            wordsRecyclerViewAdapter?.addData(wordList)
            turnOffLoadMode.invoke(false)
        } else
            wordsRecyclerViewAdapter?.submitData(wordList)

        currentScrollPosition = recyclerViewPosition.invoke(null)
        if (currentScrollPosition != 0)
            Handler().postDelayed({
                recyclerViewLayoutManager().scrollToPosition(currentScrollPosition)
            }, 2000)

        binding.allWordsRecyclerView
            .setOnScrollListener(object : RecyclerView.OnScrollListener() {

                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    currentScrollPosition += dy
                    recyclerViewPosition.invoke(currentScrollPosition)
                }

                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    super.onScrollStateChanged(recyclerView, newState)

                    if (recyclerView.layoutManager != null
                        && (recyclerView.layoutManager as LinearLayoutManager).findLastCompletelyVisibleItemPosition()
                        == wordsRecyclerViewAdapter?.itemCount!! - 1
                    ) {
                        loadMore.invoke(true)
                    }
                }
            })
    }

}