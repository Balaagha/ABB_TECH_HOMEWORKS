package com.example.todoapp.data.database.di

import com.example.todoapp.data.database.feature.note.source.NoteLocalDataSource
import com.example.todoapp.data.database.feature.note.source.NoteLocalDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class BindDataSourceModule {

    @Singleton
    @Binds
    abstract fun bindNoteLocalDataSource(noteLocalDataSource: NoteLocalDataSourceImpl): NoteLocalDataSource

}