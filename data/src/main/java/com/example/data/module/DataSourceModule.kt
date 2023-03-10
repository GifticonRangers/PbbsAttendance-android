package com.example.data.module

import com.example.data.api.UserService
import com.example.data.repository.remote.datasources.UserRemoteDataSource
import com.example.data.repository.remote.datasources.UserRemoteDataSourceImpl
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
    fun bindUserDataSourceImpl(dataSource: UserRemoteDataSourceImpl): UserRemoteDataSource
}