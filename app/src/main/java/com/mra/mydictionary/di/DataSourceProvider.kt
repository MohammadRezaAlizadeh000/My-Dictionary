package com.mra.mydictionary.di

import com.mra.mydictionary.data.LocalDataSource
import com.mra.mydictionary.data.LocalDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceProvider {

    @Binds
    abstract fun provideLocalDataSource(impl: LocalDataSourceImpl): LocalDataSource
}