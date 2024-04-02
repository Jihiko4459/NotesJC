package com.example.notes.presentation

import com.example.notes.data.Note

//в окне создания класса котлин
// в версии Игуана с списке нет sealed class
//поэтому нужно сначала создать обычный класс,
// а потом дописать, а затем class поменять на interface
sealed interface NotesEvent {
    object SortNotes:NotesEvent

    data class DeleteNote(val note:Note):NotesEvent

    data class SaveNote(
        val title:String,
        val description: String
    ):NotesEvent
}