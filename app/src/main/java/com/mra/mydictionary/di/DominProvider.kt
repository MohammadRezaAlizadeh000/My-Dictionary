package com.mra.mydictionary.di

import com.mra.mydictionary.domin.GetAllWordsUseCase
import com.mra.mydictionary.domin.GetAllWordsUseCaseImpl
import com.mra.mydictionary.domin.InsertNewWordUseCase
import com.mra.mydictionary.domin.InsertNewWordUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DominProvider {

    @Binds
    abstract fun bindGetAllWordsUseCase(impl: GetAllWordsUseCaseImpl): GetAllWordsUseCase

    @Binds
    abstract fun bindInsertNewWordUseCase(impl: InsertNewWordUseCaseImpl): InsertNewWordUseCase

}