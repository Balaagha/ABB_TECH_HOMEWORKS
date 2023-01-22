package com.example.todoapp.data.di

import com.example.todoapp.data.feature.download.repository.DownloadOperationRepository
import com.example.todoapp.data.feature.download.repository.DownloadOperationRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
abstract class BindModule {

    @Singleton
    @Binds
    abstract fun bindDownloadOperationRepository(repo: DownloadOperationRepositoryImpl): DownloadOperationRepository

}