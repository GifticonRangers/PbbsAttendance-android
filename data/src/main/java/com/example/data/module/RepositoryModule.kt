package com.example.data.module

import com.example.data.repository.StudentListRepositoryImpl
import com.example.data.repository.UserRepositoryImpl
import com.example.domain.repository.StudentListRepository
import com.example.domain.repository.UserRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    @Singleton
    fun bindUserRepositoryImpl(repository: UserRepositoryImpl):UserRepository

    @Binds
    @Singleton
    fun bindStudentListRepositoryImpl(repository: StudentListRepositoryImpl):StudentListRepository
}