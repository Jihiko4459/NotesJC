package com.example.notes.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material.icons.rounded.Sort
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.notes.R


@Composable
fun NotesScreen(
    state: NoteState,
    navController: NavController,
    onEvent:(NotesEvent)->Unit
    ){
    Scaffold(
        topBar ={//ToolBar
            Row(
                modifier= Modifier
                    .fillMaxWidth()
                    .height(55.dp)
                    .background(MaterialTheme.colorScheme.primary)
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text= stringResource(id = R.string.app_name),
                    modifier=Modifier.weight(1f),
                    fontSize=17.sp,
                    fontWeight=FontWeight.SemiBold,
                    color=MaterialTheme.colorScheme.onPrimary
                )
                IconButton(onClick = {onEvent(NotesEvent.SortNotes)}) {
                    Icon(imageVector= Icons.Rounded.Sort,
                        contentDescription="Sort Notes",
                        modifier = Modifier.size(35.dp),
                        tint = MaterialTheme.colorScheme.onPrimary)
                    
                }//кнопка сортировки
            }
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                state.title.value =""
                navController.navigate("AddNoteScreen")
            }) {
                Icon(imageVector = Icons.Rounded.Add, contentDescription = "Add new note")

            }
        }//кнопка добавления заметки
    ) {paddingValues ->
        LazyColumn (
            contentPadding = paddingValues,
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)){
            items(state.notes.size){index ->
                NoteItem(state = state,
                    index = index,
                    onEvent=onEvent)
            }
        }//отвечает за выход функции отображения списка заметок

    }
}

@Composable
fun NoteItem(//функции отображения списка заметок
    state: NoteState,
    index: Int,
    onEvent:(NotesEvent)->Unit
){
    Row (
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp))
            .background(MaterialTheme.colorScheme.primaryContainer)
            .padding(12.dp)
    ){
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(//заголовок нашей заметки
                text = state.notes[index].title,
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.onSecondaryContainer
            )
            
            Spacer(modifier = Modifier.height(8.dp))//расстояние м/у заголовком и описанием заметки

            Text(//описание заметки
                text = state.notes[index].description,
                fontSize = 16.sp,
                color = MaterialTheme.colorScheme.onSecondaryContainer
            )
        }
        IconButton(onClick = { onEvent(NotesEvent.DeleteNote(state.notes[index])) }) {
            Icon(imageVector = Icons.Rounded.Delete,
                contentDescription ="Delete Note",
                modifier = Modifier.size(35.dp),
                tint = MaterialTheme.colorScheme.onPrimaryContainer)

        }//кнопка удаления заметки
    }
}