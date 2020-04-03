package com.example.roomdatabase

import android.app.Application
import android.content.Context
import com.example.roomdatabase.db.data.repositories.NoteRepository
import com.example.roomdatabase.db.database.NoteDatabase
import com.example.roomdatabase.ui.fragments.NotesViewModelFactory

class MyApplication : Application() {

    companion object{
        lateinit var context: Context
        lateinit var db: NoteDatabase
        lateinit var noteRepository: NoteRepository
        lateinit var notesViewModelFactory: NotesViewModelFactory
    }
    override fun onCreate() {
        super.onCreate()

        context = applicationContext

        //create database instance
        db = NoteDatabase(context)
        noteRepository = NoteRepository(db)
        notesViewModelFactory = NotesViewModelFactory(noteRepository)

    }
}