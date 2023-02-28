package com.example.pbbsattendance.di

import com.example.data.repository.remote.datasources.UserRemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataSourceImplModule {

    @Provides
    @Singleton
    fun provideRemoteDataSource() = UserRemoteDataSourceImpl()
}