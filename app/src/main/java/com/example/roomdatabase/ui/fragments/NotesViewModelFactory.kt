package com.example.roomdatabase.ui.fragments

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.roomdatabase.db.data.repositories.NoteRepository

class NotesViewModelFactory(private val notesRepository: NoteRepository) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return NotesViewModel(notesRepository) as T
    }
}