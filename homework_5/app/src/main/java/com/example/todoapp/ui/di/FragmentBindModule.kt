package com.example.todoapp.ui.di

import com.example.todoapp.ui.adapter.NoteListAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.android.scopes.FragmentScoped
import javax.inject.Named

@Module
@InstallIn(FragmentComponent::class)
object FragmentBindModule {
    @Provides
    @FragmentScoped
    @Named("home_fragment")
    fun provideNoteAdapterToHomeFragment() = NoteListAdapter()
}