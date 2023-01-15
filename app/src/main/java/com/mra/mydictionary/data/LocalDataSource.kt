package com.mra.mydictionary.data

import com.mra.mydictionary.db.MyDictionaryDbDao
import com.mra.mydictionary.model.WordEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

interface LocalDataSource {
    suspend fun insertNewWord(wordEntity: WordEntity): Flow<Long>

    suspend fun getAllNewestWords(startPoint: Int): Flow<List<WordEntity>>
    suspend fun getAllOldestWords(startPoint: Int): Flow<List<WordEntity>>
    suspend fun getAllWordsAToZ(startPoint: Int): Flow<List<WordEntity>>
    suspend fun getAllWordsZToA(startPoint: Int): Flow<List<WordEntity>>
}

class LocalDataSourceImpl @Inject constructor(
    private val dbDao: MyDictionaryDbDao
) : LocalDataSource {

    override suspend fun insertNewWord(wordEntity: WordEntity): Flow<Long> {
        return flow { emit(dbDao.insertNewWord(wordEntity)) }
    }

    override suspend fun getAllNewestWords(startPoint: Int): Flow<List<WordEntity>> {
        return dbDao.getWordsNewest(startPoint)
    }

    override suspend fun getAllOldestWords(startPoint: Int): Flow<List<WordEntity>> {
        return dbDao.getWordsOldest(startPoint)
    }

    override suspend fun getAllWordsAToZ(startPoint: Int): Flow<List<WordEntity>> {
        return dbDao.getWordsAToZ(startPoint)
    }

    override suspend fun getAllWordsZToA(startPoint: Int): Flow<List<WordEntity>> {
        return dbDao.getWordsZToA(startPoint)
    }

}