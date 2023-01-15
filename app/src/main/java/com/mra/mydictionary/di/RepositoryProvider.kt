package com.mra.mydictionary.di

import com.mra.mydictionary.data.Repository
import com.mra.mydictionary.data.RepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryProvider {

    @Binds
    abstract fun provideRepository(impl: RepositoryImpl): Repository

}