package com.team.mynotescompose.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.team.mynotescompose.screen.AddNoteScreen
import com.team.mynotescompose.screen.NoteScreen

@Composable
fun NotesNavigation(){
    val navController= rememberNavController()
    NavHost(navController = navController, startDestination = NoteScreens.NoteScreen.name ){
        composable(NoteScreens.NoteScreen.name){
            NoteScreen(navController = navController)
        }
        composable(NoteScreens.AddNoteScreen.name){
            AddNoteScreen(navController=navController)
        }

    }
}