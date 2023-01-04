package com.example.todoapp.data.database.feature.note.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.example.todoapp.data.database.base.BaseDao
import com.example.todoapp.data.database.feature.note.model.Note

@Dao
abstract class NoteDao: BaseDao<Note>{
    @Transaction
    @Query("SELECT * FROM cached_note_table" )
    abstract fun getAllNotes(): LiveData<List<Note>>

    @Transaction
    @Query("SELECT * FROM cached_note_table WHERE id = :noteId " )
    abstract fun getNote(noteId: Long): Note

}
