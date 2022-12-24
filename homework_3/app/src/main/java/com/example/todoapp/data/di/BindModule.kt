package com.example.todoapp.data.di

import com.example.todoapp.data.feature.movie.repository.NoteOperationRepository
import com.example.todoapp.data.feature.movie.repository.NoteOperationRepositoryImpl
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
    abstract fun bindNoteOperationRepository(repo: NoteOperationRepositoryImpl): NoteOperationRepository

}