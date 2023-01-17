package com.mra.mydictionary.domin

import com.mra.mydictionary.data.Repository
import com.mra.mydictionary.model.WordEntity
import com.mra.mydictionary.view.allWords.FilterType
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface GetAllWordsUseCase {
    suspend operator fun invoke(startPoint: Int, filterType: FilterType): Flow<List<WordEntity>>
}

class GetAllWordsUseCaseImpl @Inject constructor(
    private val repository: Repository
): GetAllWordsUseCase {

    override suspend fun invoke(startPoint: Int, filterType: FilterType): Flow<List<WordEntity>> {
        return when(filterType) {
            FilterType.NEWEST -> repository.getAllNewestWords(startPoint)
            FilterType.OLDEST -> repository.getAllOldestWords(startPoint)
            FilterType.A_TO_Z -> repository.getAllWordsAToZ(startPoint)
            FilterType.Z_TO_A -> repository.getAllWordsZToA(startPoint)
        }
    }

}