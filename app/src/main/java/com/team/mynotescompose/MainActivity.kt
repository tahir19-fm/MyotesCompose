package com.team.mynotescompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.team.mynotescompose.data.NoteDataSource
import com.team.mynotescompose.model.Note
import com.team.mynotescompose.navigation.NotesNavigation
import com.team.mynotescompose.screen.NoteScreen
import com.team.mynotescompose.screen.NoteViewModel
import com.team.mynotescompose.ui.theme.MyNotesComposeTheme
import com.team.mynotescompose.ui.theme.backgroundColor
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val notes= remember {
                mutableStateListOf<Note>()
            }
            MyNotesComposeTheme {
                // A surface container using the 'background' color from the theme
                NotesApp()
            }
        }
    }
}

@Composable
fun NotesApp(){
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = backgroundColor
    ) {
        NotesNavigation()
    }
}

