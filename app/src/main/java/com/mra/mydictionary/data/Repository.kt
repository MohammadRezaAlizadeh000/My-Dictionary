package com.mra.mydictionary.data

import com.mra.mydictionary.model.WordEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface Repository {

    suspend fun getWordsNumber(): Int
    suspend fun insertNewWord(wordEntity: WordEntity): Flow<Long>
    suspend fun getAllNewestWords(startPoint: Int): Flow<List<WordEntity>>
    suspend fun getAllOldestWords(startPoint: Int): Flow<List<WordEntity>>
    suspend fun getAllWordsAToZ(startPoint: Int): Flow<List<WordEntity>>
    suspend fun getAllWordsZToA(startPoint: Int): Flow<List<WordEntity>>
}

class RepositoryImpl @Inject constructor(
    private val localDataSource: LocalDataSource
): Repository {
    override suspend fun getWordsNumber(): Int {
        return localDataSource.getWordsNumber()
    }

    override suspend fun insertNewWord(wordEntity: WordEntity): Flow<Long> {
        return localDataSource.insertNewWord(wordEntity)
    }

    override suspend fun getAllNewestWords(startPoint: Int): Flow<List<WordEntity>> {
        return localDataSource.getAllNewestWords(startPoint)
    }

    override suspend fun getAllOldestWords(startPoint: Int): Flow<List<WordEntity>> {
        return localDataSource.getAllOldestWords(startPoint)
    }

    override suspend fun getAllWordsAToZ(startPoint: Int): Flow<List<WordEntity>> {
        return localDataSource.getAllWordsAToZ(startPoint)
    }

    override suspend fun getAllWordsZToA(startPoint: Int): Flow<List<WordEntity>> {
        return localDataSource.getAllWordsZToA(startPoint)
    }

}