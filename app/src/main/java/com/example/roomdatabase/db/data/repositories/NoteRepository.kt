package com.example.roomdatabase.db.data.repositories

import com.example.roomdatabase.db.database.NoteDatabase
import com.example.roomdatabase.db.entities.Note

class NoteRepository(private val db : NoteDatabase) {

    suspend fun saveNote(note : Note) = db.getNoteDao().addNote(note)

    suspend fun getAllNotes() : List<Note> = db.getNoteDao().getAllNotes()

    suspend fun updateNote(note : Note) = db.getNoteDao().updateNote(note)

    suspend fun deleteNote(note : Note) = db.getNoteDao().deleteNote(note)
}