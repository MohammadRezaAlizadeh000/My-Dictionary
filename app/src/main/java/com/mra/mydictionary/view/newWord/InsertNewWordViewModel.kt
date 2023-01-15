package com.mra.mydictionary.view.newWord

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mra.mydictionary.domin.InsertNewWordUseCase
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
class InsertNewWordViewModel @Inject constructor(
    private val insertNewWordUseCase: InsertNewWordUseCase
): ViewModel() {

    private var job: Job? = null

    data class InsertNewWordState(
        override val data: Boolean? = null,
        override val errorMessage: String? = null
    ): BaseDataModelState<Boolean>

    private val _insertNewWordState: MutableStateFlow<InsertNewWordState> = MutableStateFlow(InsertNewWordState())
    val insertNewWordState: StateFlow<InsertNewWordState> = _insertNewWordState

    fun insertNewWord(wordEntity: WordEntity) {
        job = viewModelScope.launch {
            insertNewWordUseCase(wordEntity).collect {
                _insertNewWordState.value = InsertNewWordState(data = it > 0)
            }
        }
    }

    fun clearInsertWordState() {
        _insertNewWordState.update {
            it.copy(data = null, errorMessage = null)
        }
    }

}