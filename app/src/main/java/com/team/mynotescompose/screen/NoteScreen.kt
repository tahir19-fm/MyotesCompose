package com.team.mynotescompose.screen

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.animation.*
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.team.mynotescompose.R
import com.team.mynotescompose.components.NoteButton
import com.team.mynotescompose.components.NoteInputText
import com.team.mynotescompose.model.Note
import com.team.mynotescompose.navigation.NoteScreens
import com.team.mynotescompose.ui.theme.appColor
import com.team.mynotescompose.util.formatDate
import java.time.format.DateTimeFormatter

@SuppressLint("SuspiciousIndentation")
@Composable
fun NoteScreen(viewModel: NoteViewModel= hiltViewModel(),
               navController: NavController
){
    val notes=viewModel.noteList.collectAsState().value

        //lazy column
    Scaffold(floatingActionButton = { FloatingActionButton(
        onClick = {
            navController.navigate(NoteScreens.AddNoteScreen.name){
                popUpTo(NoteScreens.NoteScreen.name){
                    inclusive=true
                }
            }
        },
        backgroundColor = Color.Red,
        content = {
            Icon(
               Icons.Default.AddCircle,
                contentDescription = null,
                tint = Color.White
            )})
    }, topBar = {
        TopAppBar(title = {
            Text(text = stringResource(id = R.string.app_name))
        }, actions = {
            Icon(imageVector = Icons.Rounded.Notifications, contentDescription = "LOGO")
        }, backgroundColor = appColor, modifier = Modifier.padding(2.dp))
    }) {
        LazyColumn(Modifier.padding(it)){
            items(notes){note->
                NoteRow(note = note, onNoteClick = {
                    viewModel.deleteNote(note)
                })
            }
        }
    }


    }

@Composable
fun NoteRow(modifier: Modifier=Modifier,
note: Note,
onNoteClick:(Note)->Unit) {
    val bottomBarState = remember {
        mutableStateOf(false)
    }

    Surface(modifier = modifier
        .padding(4.dp)
        .clip(RoundedCornerShape(22.dp))
        .fillMaxWidth(),
    color = appColor,
    elevation = 6.dp) {
        Column(modifier = modifier
            .clickable {
                bottomBarState.value=!bottomBarState.value
            }
            .padding(horizontal = 14.dp, vertical = 6.dp),
        horizontalAlignment = Alignment.Start) {
            val density = LocalDensity.current
            Text(text = note.title,
            style = MaterialTheme.typography.subtitle2, fontSize = if (bottomBarState.value) 22.sp else 44.sp)
            AnimatedVisibility(
                visible = bottomBarState.value,
                enter = slideInVertically {
                    with(density) { -40.dp.roundToPx() }
                } + expandVertically(
                    expandFrom = Alignment.Top
                ) + fadeIn(
                    initialAlpha = 0.3f
                ),
                exit = slideOutVertically() + shrinkVertically() + fadeOut()){
                Text(text = note.description,style=MaterialTheme.typography.subtitle1)

            }

            Text(text = formatDate( note.entryDate.time),
            style = MaterialTheme.typography.caption)

        }


    }

}

