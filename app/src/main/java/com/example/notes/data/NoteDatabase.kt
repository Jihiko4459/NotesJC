package com.example.notes.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(//внутри скобок сообщаем какие сущности
// будут в нашей бд и версию
    entities = [Note::class],
    version = 1
)
abstract class NoteDatabase:RoomDatabase() {
    abstract val dao:NoteDao
}