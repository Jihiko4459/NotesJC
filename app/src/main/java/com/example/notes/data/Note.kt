package com.example.notes.data

import androidx.room.Entity
import android.accounts.AuthenticatorDescription
import androidx.room.PrimaryKey

@Entity
data class Note(
    val title:String,
    val description: String,
    val dateAdded:Long,

    @PrimaryKey(autoGenerate=true)
    val id:Int=0
)
