package com.example.data.di

import com.example.data.repository.datasource.*
import com.example.data.repository.remote.datasources.UserRemoteDataSource
import com.example.data.repository.remote.datasources.UserRemoteDataSourceImpl
import com.example.data.util.PreferenceUtil
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface DataSourceModule {
    @Binds
    @Singleton
    fun bindUserDataSourceImpl(dataSourceImpl: UserRemoteDataSourceImpl): UserRemoteDataSource

    @Binds
    @Singleton
    fun bindTokenLocalDataSourceImpl(dataSourceImpl: TokenLocalDataSourceImpl):TokenLocalDataSource

    @Binds
    @Singleton
    fun bindTokenRemoteDataSourceImpl(dataSourceImpl: TokenRemoteSourceImpl):TokenRemoteDataSource

}