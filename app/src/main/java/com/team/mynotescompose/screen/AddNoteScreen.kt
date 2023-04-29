package com.team.mynotescompose.screen

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.team.mynotescompose.R
import com.team.mynotescompose.components.NoteButton
import com.team.mynotescompose.components.NoteInputText
import com.team.mynotescompose.model.Note
import com.team.mynotescompose.navigation.NoteScreens
import com.team.mynotescompose.ui.theme.appColor

@Composable
fun AddNoteScreen(viewModel: NoteViewModel = hiltViewModel(), navController: NavHostController) {
    var title by remember {
        mutableStateOf("")
    }
    var description by remember {
        mutableStateOf("")
    }
    val context = LocalContext.current

    Column(modifier = Modifier) {
        TopAppBar(title = {
            Text(text = stringResource(id = R.string.app_name))
        }, actions = {
            Icon(imageVector = Icons.Rounded.Notifications, contentDescription = "LOGO")
        }, backgroundColor = appColor)

        //content
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(6.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            NoteInputText(modifier = Modifier
                .fillMaxWidth()
                .padding(
                    9.dp
                ), imeAction = ImeAction.Next, text = title, label = "Title", onTextChange = {
                if (it.all { char ->
                        char.isLetter() || char.isWhitespace()
                    })
                    title = it
            })

            NoteInputText(modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(
                    9.dp
                ), maxLine = 10, text = description, label = "Add a note", onTextChange = {
                if (it.all { char ->
                        char.isLetter() || char.isWhitespace()
                    })
                    description = it
            })

            NoteButton(text = "Save", onClick = {
                if (title.isNotEmpty() && description.isNotEmpty()) {
                    // save yor data
                    viewModel.addNote(Note(title = title, description = description))
                    title = ""
                    description = ""
                    Toast.makeText(context, "Note Saved Successfully", Toast.LENGTH_SHORT).show()
                    navController.navigate(NoteScreens.NoteScreen.name){
                        popUpTo(NoteScreens.AddNoteScreen.name){
                            inclusive=true
                        }
                    }

                }
            })
        }
    }
}