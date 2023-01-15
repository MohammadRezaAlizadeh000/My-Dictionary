package com.mra.mydictionary.di

import android.content.Context
import androidx.room.Room
import com.mra.mydictionary.db.MyDictionaryDb
import com.mra.mydictionary.utils.DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DBModule {

    @Singleton
    @Provides
    fun provideDatabase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(
        context,
        MyDictionaryDb::class.java,
        DATABASE_NAME
    ).build()

    @Singleton
    @Provides
    fun providePopularMovieDao(database: MyDictionaryDb) = database.accessMyDictionaryDbDao()
}