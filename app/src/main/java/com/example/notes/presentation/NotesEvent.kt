package com.example.notes.presentation

import com.example.notes.data.Note

//в окне создания класса котлин
// в версии Игуана с списке нет sealed class
//поэтому нужно сначала создать обычный класс,
// а потом дописать, а затем class поменять на interface
sealed interface NotesEvent {//sealed - закрытый
    object SortNotes:NotesEvent//объект сортировки

    data class DeleteNote(val note:Note):NotesEvent//нужно для удаления заметки Note в бд

    data class SaveNote(
        val title:String,
        val description: String
    ):NotesEvent//сохранение заметки Note
}