package com.example.notes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import com.example.notes.data.NoteDatabase
import com.example.notes.presentation.AddNoteScreen
import com.example.notes.presentation.NotesScreen
import com.example.notes.presentation.NotesViewModel
import com.example.notes.ui.theme.NotesTheme

class MainActivity : ComponentActivity() {

    private val database by lazy {
        Room.databaseBuilder(
            applicationContext,//передаем контекст приложения
            NoteDatabase::class.java,//и класс бд
            "notes.db"//название бд
        ).build()
    }//создаем объект бд

    private val viewModel by viewModels<NotesViewModel>(
        factoryProducer = {
            object :ViewModelProvider.Factory{
                override fun<T: ViewModel> create(modelClass:Class<T>):T{
                    return NotesViewModel(database.dao) as T
                }
            }
        }
    )//создаем экземпляр viewModel и подключаем к ней бд

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NotesTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val state by viewModel.state.collectAsState()
                    val navController= rememberNavController()

                    NavHost(navController = navController, startDestination = "NotesScreen" ){
                        composable("NotesScreen"){
                            NotesScreen(
                                state = state,
                                navController = navController,
                                onEvent=viewModel::onEvent
                            )
                        }
                        composable("AddNoteScreen"){
                            AddNoteScreen(
                                state = state,
                                navController = navController,
                                onEvent=viewModel::onEvent
                            )
                        }
                    }
                }
            }
        }
    }
}
