package com.mra.mydictionary.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mra.mydictionary.model.WordEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MyDictionaryDbDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNewWord(newWord: WordEntity?): Long

    @Query("SELECT * FROM wordentity LIMIT :startPoint, 20")
    fun getWords(startPoint: Int): Flow<List<WordEntity>>

}