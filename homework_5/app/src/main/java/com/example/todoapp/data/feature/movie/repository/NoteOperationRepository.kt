package com.example.todoapp.data.feature.movie.repository

import androidx.lifecycle.LiveData
import com.example.todoapp.data.database.feature.note.model.Note

interface NoteOperationRepository {

    fun getAllNotesData(): LiveData<List<Note>>

    fun getSelectedNoteData(noteId: Long): Note

    suspend fun insertNote(note: Note)

    suspend fun updateNote(note: Note)

    suspend fun deleteNote(note: Note)

}