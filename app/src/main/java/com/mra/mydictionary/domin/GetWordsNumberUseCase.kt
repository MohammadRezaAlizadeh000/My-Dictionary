package com.mra.mydictionary.domin

import com.mra.mydictionary.data.Repository
import javax.inject.Inject

interface GetWordsNumberUseCase {
    suspend fun getWordsNumber(): Int
}

class GetWordsNumberUseCaseImpl @Inject constructor(
    private val repository: Repository
): GetWordsNumberUseCase {
    override suspend fun getWordsNumber(): Int {
        return repository.getWordsNumber()
    }

}