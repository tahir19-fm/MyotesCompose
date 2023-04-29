package com.team.mynotescompose.data

import com.team.mynotescompose.model.Note

class NoteDataSource{
    fun loadNotes():List<Note>{
        return listOf(
            Note(title = "A good day", description = "A good day with dooday"),
            Note(title = "A good day", description = "A good day with dooday"),
            Note(title = "A good day", description = "A good day with dooday"),
            Note(title = "A good day", description = "A good day with dooday"),
            Note(title = "A good day", description = "A good day with dooday"),
            Note(title = "A good day", description = "A good day with dooday"),
            Note(title = "A good day", description = "A good day with dooday"),
            Note(title = "A good day", description = "A good day with dooday"),
            Note(title = "A good day", description = "A good day with dooday"),
            Note(title = "A good day", description = "A good day with dooday"),
            Note(title = "A good day", description = "A good day with dooday"),
            Note(title = "A good day", description = "A good day with dooday"),
            Note(title = "A good day", description = "A good day with dooday"),
            Note(title = "A good day", description = "A good day with dooday"),
        )
    }
}