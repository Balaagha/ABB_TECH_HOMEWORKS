package com.example.todoapp.data.feature.movie.repository

import androidx.lifecycle.LiveData
import com.example.todoapp.data.database.feature.note.model.Note
import com.example.todoapp.data.database.feature.note.source.NoteLocalDataSource
import javax.inject.Inject

class NoteOperationRepositoryImpl @Inject constructor(
    private val dataSource: NoteLocalDataSource,
) : NoteOperationRepository {

    override fun getAllNotesData():LiveData<List<Note>> = dataSource.getAllNotes()


    override suspend fun insertNote(note: Note) {
        dataSource.insertNote(note)
    }

    override suspend fun updateNote(note: Note) {
        dataSource.updateNote(note)
    }

    override suspend fun deleteNote(note: Note){
        dataSource.deleteNote(note)
    }

}