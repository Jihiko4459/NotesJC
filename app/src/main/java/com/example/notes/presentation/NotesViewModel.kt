package com.example.notes.presentation

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notes.data.Note
import com.example.notes.data.NoteDao
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

//это модель представления, которая реагироать на все действия
// пользователя и выполнять соответсующие операции, связанные с бд
class NotesViewModel(private val dao:NoteDao):ViewModel() {

    private val isSortedByDateAdded= MutableStateFlow(true)

    private var notes=
        isSortedByDateAdded.flatMapLatest {sort->
            if(sort){
                dao.getNotesOrderByDateAdded()
            }
            else{
                dao.getNotesOrderByTitled()
            }
        }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())

    val _state= MutableStateFlow(NoteState())
    val state=
        combine(_state, isSortedByDateAdded, notes){state, isSortedByDateAdded, notes->
            state.copy(
                notes = notes
            )
        }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), NoteState())

    fun onEvent(event: NotesEvent){
        when(event){
            is NotesEvent.DeleteNote ->
            {
                viewModelScope.launch {
                    dao.deleteNote(event.note)
                }

            }
            is NotesEvent.SaveNote -> {
                val note = Note(
                        title = state.value.title.value,
                        description=state.value.description.value,
                        dateAdded = System.currentTimeMillis()
                    )
                viewModelScope.launch {
                    dao.upsertNote(note)
                }
                _state.update {
                    it.copy(
                        title = mutableStateOf(""),
                        description = mutableStateOf("")
                    )
                }
            }
            NotesEvent.SortNotes -> {
                isSortedByDateAdded.value= !isSortedByDateAdded.value
            }
        }
    }
}