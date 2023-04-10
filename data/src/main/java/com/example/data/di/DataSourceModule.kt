package com.example.data.di

import com.example.data.repository.datasource.StudentListRemoteDataSource
import com.example.data.repository.datasource.StudentListRemoteDataSourceImpl
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

    @Binds
    @Singleton
    fun StudentListRemoteDataSourceImpl(dataSource: StudentListRemoteDataSourceImpl):StudentListRemoteDataSource
}