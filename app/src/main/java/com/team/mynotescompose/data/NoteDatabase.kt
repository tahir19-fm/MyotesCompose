package com.team.mynotescompose.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.team.mynotescompose.model.Note
import com.team.mynotescompose.util.DateConverter


@Database(entities = [Note::class], version = 1, exportSchema = false)
@TypeConverters(DateConverter::class)
abstract class NoteDatabase:RoomDatabase() {
abstract fun noteDao():NoteDatabaseDao
}