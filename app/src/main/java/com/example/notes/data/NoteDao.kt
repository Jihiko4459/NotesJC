package com.example.notes.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao//пишем для того, чтобы сообщить Room,
// что это нащ обьект доступа к данным Note
interface NoteDao {
    //внутри пишем функции для выполнения операций с бд Room

    @Upsert//Upsert=Insert+Update, т.е. добавление и обновлнение данных
    suspend fun upsertNote(note:Note)//suspend нужен для того, чтобы сделать функцию асинхронной

    @Delete
    suspend fun deleteNote(note:Note)

    @Query("SELECT * FROM note ORDER BY dateAdded")//Запрос для сортировки данных по дате добавления
    fun getNotesOrderByDateAdded(): Flow<List<Note>>

    @Query("SELECT * FROM note ORDER BY title ASC")//Запрос для сортировки данных по имени
    fun getNotesOrderByTitled(): Flow<List<Note>>
}