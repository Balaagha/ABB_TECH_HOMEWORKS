package com.example.todoapp.ui.feature.home.viewmodel

import android.app.Application
import androidx.lifecycle.SavedStateHandle
import com.example.todoapp.data.database.feature.note.model.Note
import com.example.todoapp.data.feature.movie.repository.NoteOperationRepository
import com.example.todoapp.framework.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    applicationData: Application,
    private val noteOperationRepository: NoteOperationRepository
) : BaseViewModel(savedStateHandle, applicationData) {

    fun getAllNote() = noteOperationRepository.getAllNotesData()
    fun deleteNote(note: Note) {
        launchSafe(Dispatchers.IO) {
            noteOperationRepository.deleteNote(note)
        }
    }

}