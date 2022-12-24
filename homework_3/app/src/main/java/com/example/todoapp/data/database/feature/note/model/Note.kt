package com.example.todoapp.data.database.feature.note.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.todoapp.data.database.utils.DatabaseConstant.CACHED_NOTE_TABLE
import java.io.Serializable

@Entity(tableName = CACHED_NOTE_TABLE)
data class Note(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val title: String,
    val body: String
) : Serializable
