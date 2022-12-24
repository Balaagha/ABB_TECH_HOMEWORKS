package com.example.todoapp.data.database.utils

import com.example.todoapp.BuildConfig

object DatabaseConstant {
    // ROOM Database
    const val DATABASE_NAME = "app_database_note"
    const val CACHED_NOTE_TABLE = "cached_note_table"
    const val ROOM_DATABASE_VERSION = BuildConfig.VERSION_CODE
}