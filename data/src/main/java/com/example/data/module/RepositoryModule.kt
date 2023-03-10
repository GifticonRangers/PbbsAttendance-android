package com.example.data.module

import com.example.data.repository.UserRepositoryImpl
import com.example.data.repository.remote.datasources.UserRemoteDataSource
import com.example.domain.repository.UserRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    @Singleton
    fun provideUserRepositoryImpl(repository: UserRepositoryImpl):UserRepository
}