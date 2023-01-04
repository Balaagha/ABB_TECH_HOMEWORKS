package com.example.todoapp.data.database.feature.note.source

import androidx.lifecycle.LiveData
import com.example.todoapp.data.database.feature.note.model.Note

interface NoteLocalDataSource {

    fun getAllNotes(): LiveData<List<Note>>

    fun getNote(noteId: Long): Note

    suspend fun insertAll(vararg notes: Note): List<Long>

    suspend fun insertNote(note: Note)

    suspend fun insertNoteList(noteList: List<Note>)

    suspend fun updateNote(note: Note)

    suspend fun deleteNote(note: Note)

}