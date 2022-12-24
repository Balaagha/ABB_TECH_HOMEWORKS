package com.example.todoapp.data.database.base

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.todoapp.data.database.feature.note.dao.NoteDao
import com.example.todoapp.data.database.feature.note.model.Note
import com.example.todoapp.data.database.utils.DatabaseConstant

@Database(
    entities = [Note::class],
    version = DatabaseConstant.ROOM_DATABASE_VERSION,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun noteDao(): NoteDao
}