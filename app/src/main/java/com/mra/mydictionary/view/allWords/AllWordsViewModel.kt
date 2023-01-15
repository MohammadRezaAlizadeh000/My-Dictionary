package com.mra.mydictionary.view.allWords

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mra.mydictionary.domin.GetAllWordsUseCase
import com.mra.mydictionary.model.WordEntity
import com.mra.mydictionary.utils.BaseDataModelState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AllWordsViewModel @Inject constructor(
    private val getAllWordsUseCase: GetAllWordsUseCase
): ViewModel() {

    private var job: Job? = null

    data class AllWordsState(
        override val data: List<WordEntity>? = null,
        override val errorMessage: String? = null
    ): BaseDataModelState<List<WordEntity>>

    private val _allWordsStateFlow: MutableStateFlow<AllWordsState> = MutableStateFlow(AllWordsState())
    val allWordsStateFlow: StateFlow<AllWordsState> = _allWordsStateFlow

    fun getAllWords(startPoint: Int) {
        job = viewModelScope.launch {
            getAllWordsUseCase(startPoint).collect { response ->
                if (response.isEmpty())
                    _allWordsStateFlow.value = AllWordsState(errorMessage = "There is no any words yeet")
                else
                    _allWordsStateFlow.value = AllWordsState(data = response)
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