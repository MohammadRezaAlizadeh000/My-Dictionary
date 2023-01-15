package com.mra.mydictionary.data

import com.mra.mydictionary.model.WordEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface Repository {
    suspend fun insertNewWord(wordEntity: WordEntity): Flow<Long>

    suspend fun getAllWords(startPoint: Int): Flow<List<WordEntity>>
}

class RepositoryImpl @Inject constructor(
    private val localDataSource: LocalDataSource
): Repository {

    override suspend fun insertNewWord(wordEntity: WordEntity): Flow<Long> {
        return localDataSource.insertNewWord(wordEntity)
    }

    override suspend fun getAllWords(startPoint: Int): Flow<List<WordEntity>> {
        return localDataSource.getAllWords(startPoint)
    }

}