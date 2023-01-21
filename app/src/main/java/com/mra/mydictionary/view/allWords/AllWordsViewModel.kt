package com.mra.mydictionary.view.allWords

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mra.mydictionary.domin.GetAllWordsUseCase
import com.mra.mydictionary.domin.GetWordsNumberUseCase
import com.mra.mydictionary.model.WordEntity
import com.mra.mydictionary.utils.BaseDataModelState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.util.Collections.addAll
import javax.inject.Inject

@HiltViewModel
class AllWordsViewModel @Inject constructor(
    private val getAllWordsUseCase: GetAllWordsUseCase,
) : ViewModel() {

    var recyclerViewPosition = 0
    var startPoint = 0
    private var job: Job? = null
    var isLoadMore = false

    data class AllWordsState(
        override val data: List<WordEntity>? = null,
        override val errorMessage: String? = null
    ) : BaseDataModelState<List<WordEntity>>

    private val _allWordsStateFlow: MutableStateFlow<AllWordsState> =
        MutableStateFlow(AllWordsState())
    val allWordsStateFlow: StateFlow<AllWordsState> = _allWordsStateFlow

    fun getAllWords(filterType: FilterType) {
        job = viewModelScope.launch {
            getAllWordsUseCase(0, filterType).collect { response ->
                if (response.isEmpty() && null == allWordsStateFlow.value.data)
                    _allWordsStateFlow.value =
                        AllWordsState(errorMessage = "There is no any words yeet")
                else if (response.isNotEmpty())
                    _allWordsStateFlow.value = AllWordsState(data = response.toMutableList())
            }
        }
    }

    fun loadMoreWords(filterType: FilterType) {
        job = viewModelScope.launch {
            getAllWordsUseCase(startPoint, filterType).collect { response ->
                if (response.isEmpty() && null == allWordsStateFlow.value.data)
                    _allWordsStateFlow.value =
                        AllWordsState(errorMessage = "There is no any words yeet")
                else if (response.isNotEmpty()){
                    _allWordsStateFlow.value =
                        _allWordsStateFlow.value.copy(data = mutableListOf<WordEntity>().apply {
                            addAll(_allWordsStateFlow.value.data ?: emptyList())
                            addAll(response)
                        })
                }
            }
        }
    }

    fun clearAllWordsState() {
        _allWordsStateFlow.update {
            it.copy(errorMessage = null)
        }
    }

    override fun onCleared() {
        job = null
        super.onCleared()
    }

}