package com.example.todoapp.ui.feature.processnote.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.SavedStateHandle
import com.example.todoapp.data.database.feature.note.model.Note
import com.example.todoapp.data.feature.movie.repository.NoteOperationRepository
import com.example.todoapp.framework.BaseViewModel
import com.example.todoapp.framework.GenericUiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject


@HiltViewModel
class ProcessNoteViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    applicationData: Application,
    private val noteOperationRepository: NoteOperationRepository
) : BaseViewModel(savedStateHandle, applicationData) {

    fun updateNote(updatedItem: Note) {
        launchSafe(Dispatchers.IO) {
            noteOperationRepository.updateNote(updatedItem)
        }
        Log.d("myTag","updateNoteById is called with ${updatedItem.id}")
    }

    fun addNote(addedNote: Note) {
        launchSafe(Dispatchers.IO) {
            noteOperationRepository.insertNote(addedNote)
        }
        Log.d("myTag","saveNote is called with ${addedNote.id}")
    }

    fun showSnackBar(value: String) {
        singleTimeUiEvent.postValue(
            GenericUiEvent.SnackBar(value)
        )
    }

}