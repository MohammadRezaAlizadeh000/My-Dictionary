package com.mra.mydictionary.domin

import com.mra.mydictionary.data.Repository
import com.mra.mydictionary.model.WordEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface InsertNewWordUseCase {

    suspend operator fun invoke(wordEntity: WordEntity): Flow<Long>

}

class InsertNewWordUseCaseImpl @Inject constructor(
    private val repository: Repository
): InsertNewWordUseCase {

    override suspend fun invoke(wordEntity: WordEntity): Flow<Long> {
        return repository.insertNewWord(wordEntity)
    }

}