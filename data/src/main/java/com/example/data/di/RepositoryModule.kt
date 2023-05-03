package com.example.data.di

import com.example.data.repository.*
import com.example.domain.repository.*
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
    fun bindUserRepositoryImpl(repositoryImpl: UserRepositoryImpl):UserRepository

    @Binds
    @Singleton
    fun bindLoginRepositoryImpl(repositoryImpl: LoginRepositoryImpl):LoginRepository

    @Binds
    @Singleton
    fun bindSubjectRepositoryImpl(repositoryImpl: SubjectRepositoryImpl):SubjectRepository

    @Binds
    @Singleton
    fun bindAttendanceRepositoryImpl(repositoryIml: AttendanceRepositoryImpl):AttendanceRepository

    @Binds
    @Singleton
    fun bindNfcRepositoryImpl(repositoryImpl: NfcRepositoryImpl):NfcRepository
}