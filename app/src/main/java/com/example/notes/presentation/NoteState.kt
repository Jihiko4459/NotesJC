package com.example.notes.presentation

import android.accounts.AuthenticatorDescription
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.example.notes.data.Note

data class NoteState(
    val notes:List<Note> = emptyList(),//список заметок, по умолчанию пустой
    val title: MutableState<String> = mutableStateOf(""),
    val description: MutableState<String> = mutableStateOf(""),

)