package com.example.notes.data

import androidx.room.Entity
import android.accounts.AuthenticatorDescription
import androidx.room.PrimaryKey

@Entity//эта аннотация нужна для добавления сущностей с бд Room
data class Note(
    //добавляем столбцы
    val title:String,
    val description: String,
    val dateAdded:Long,

    @PrimaryKey(autoGenerate=true)//Добавление первичного ключа с автоматической генерацией
    val id:Int=0
)
