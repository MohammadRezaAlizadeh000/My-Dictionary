package com.mra.mydictionary.domin

import com.mra.mydictionary.data.Repository
import com.mra.mydictionary.model.WordEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface GetAllWordsUseCase {
    suspend operator fun invoke(startPoint: Int): Flow<List<WordEntity>>
}

class GetAllWordsUseCaseImpl @Inject constructor(
    private val repository: Repository
): GetAllWordsUseCase {

    override suspend fun invoke(startPoint: Int): Flow<List<WordEntity>> {
        return repository.getAllWords(startPoint)
    }

}