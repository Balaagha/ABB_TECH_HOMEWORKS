package com.example.todoapp.data.database.feature.note.source

import androidx.lifecycle.LiveData
import com.example.todoapp.data.database.feature.note.dao.NoteDao
import com.example.todoapp.data.database.feature.note.model.Note
import javax.inject.Inject

class NoteLocalDataSourceImpl @Inject constructor(
    private val dao: NoteDao,
) : NoteLocalDataSource {

    override fun getAllNotes():LiveData<List<Note>> = dao.getAllNotes()

    override fun getNote(noteId: Long): Note = dao.getNote(noteId)

    override suspend fun insertAll(vararg notes: Note): List<Long> {
        return dao.insertAll(*notes)
    }

    override suspend fun insertNote(note: Note) {
        dao.insert(note)
    }

    override suspend fun insertNoteList(noteList: List<Note>) {
        dao.insert(noteList)
    }

    override suspend fun updateNote(note: Note) {
        dao.update(note)
    }

    override suspend fun deleteNote(note: Note){
        dao.delete(note)
    }

}