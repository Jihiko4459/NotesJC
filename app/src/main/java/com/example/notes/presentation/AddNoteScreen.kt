package com.example.notes.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun AddNoteScreen(
    state: NoteState,
    navController: NavController,
    onEvent:(NotesEvent)->Unit
){
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = {
                onEvent(NotesEvent.SaveNote(
                    title = state.title.value,
                    description = state.description.value
                ))
                navController.popBackStack()//возврат на предыдущий экран, в нашем случае NotesScreen
            }) {
                Icon(imageVector = Icons.Rounded.Check,
                    contentDescription ="Save Note" )

            }
        }//кнопка сохранения заметки
    ) {paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {
            TextField(
                modifier = Modifier.fillMaxWidth()
                    .padding(16.dp),
                value = state.title.value,
                onValueChange = {
                    state.title.value=it
                },
                textStyle= TextStyle(//настраиваем стиль текста
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 17.sp
                ),
                placeholder={//=hint
                    Text(text="Title")
                }
            )//Текстовое поле для ввода заголовка заметки
            TextField(
                modifier = Modifier.fillMaxWidth()
                    .padding(16.dp),
                value = state.description.value,
                onValueChange = {
                    state.description.value=it
                },
                textStyle= TextStyle(
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 17.sp
                ),
                placeholder={//=hint
                    Text(text="Description")
                }
            )//Текстовое поле для ввода описания заметки


        }
    }
}