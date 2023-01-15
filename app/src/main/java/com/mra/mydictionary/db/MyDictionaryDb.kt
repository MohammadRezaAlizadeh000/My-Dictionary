package com.mra.mydictionary.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mra.mydictionary.model.WordEntity
import com.mra.mydictionary.utils.DATABASE_VERSION

@Database(
    entities = [WordEntity::class],
    version = DATABASE_VERSION,
)
abstract class MyDictionaryDb: RoomDatabase() {

    abstract fun accessMyDictionaryDbDao(): MyDictionaryDbDao

}