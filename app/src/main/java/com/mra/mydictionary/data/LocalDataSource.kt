package com.mra.mydictionary.data

import com.mra.mydictionary.db.MyDictionaryDbDao
import com.mra.mydictionary.model.WordEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

interface LocalDataSource {
    suspend fun insertNewWord(wordEntity: WordEntity): Flow<Long>

    suspend fun getAllWords(startPoint: Int): Flow<List<WordEntity>>
}

class LocalDataSourceImpl @Inject constructor(
    private val dbDao: MyDictionaryDbDao
) : LocalDataSource {

    override suspend fun insertNewWord(wordEntity: WordEntity): Flow<Long> {
        return flow { emit(dbDao.insertNewWord(wordEntity)) }
    }

    override suspend fun getAllWords(startPoint: Int): Flow<List<WordEntity>> {
        return dbDao.getWords(startPoint)
    }

}