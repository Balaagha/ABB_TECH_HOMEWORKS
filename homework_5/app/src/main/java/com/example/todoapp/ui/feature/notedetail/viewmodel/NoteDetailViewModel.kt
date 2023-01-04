package com.example.todoapp.ui.feature.notedetail.viewmodel

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
class NoteDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    applicationData: Application,
    private val noteOperationRepository: NoteOperationRepository
) : BaseViewModel(savedStateHandle, applicationData) {

    fun getNote(noteId: Long): Note  {
        return try {
            noteOperationRepository.getSelectedNoteData(noteId)
        } catch (e: Exception){
            Note(noteId,"Default Title","Default Body")
        }
    }

}