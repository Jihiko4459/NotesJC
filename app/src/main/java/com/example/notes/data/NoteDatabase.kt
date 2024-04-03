package com.example.notes.data

import androidx.room.Database
import androidx.room.RoomDatabase

//пишем аннотацию @Database, чтобы указать Room, что это наша бд
@Database(//внутри скобок сообщаем какие сущности
// будут в нашей бд и версию
    entities = [Note::class],//в сущности указываем data class Note
    version = 1
)
abstract class NoteDatabase:RoomDatabase() {
    abstract val dao:NoteDao
}