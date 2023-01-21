package com.mra.mydictionary.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.mra.mydictionary.model.WordEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MyDictionaryDbDao {

    @Query("SELECT COUNT(*) FROM wordentity")
    suspend fun dictionaryWordNumber(): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNewWord(newWord: WordEntity?): Long

    @Query("SELECT * FROM wordentity ORDER BY creationDate DESC LIMIT :startPoint, 20")
    fun getWordsNewest(startPoint: Int): Flow<List<WordEntity>>

    @Query("SELECT * FROM wordentity ORDER BY creationDate LIMIT :startPoint, 20")
    fun getWordsOldest(startPoint: Int): Flow<List<WordEntity>>

    @Query("SELECT * FROM wordentity ORDER BY lower(word) ASC LIMIT :startPoint, 20")
    fun getWordsAToZ(startPoint: Int): Flow<List<WordEntity>>

    @Query("SELECT * FROM wordentity ORDER BY lower(word) DESC LIMIT :startPoint, 20")
    fun getWordsZToA(startPoint: Int): Flow<List<WordEntity>>
}