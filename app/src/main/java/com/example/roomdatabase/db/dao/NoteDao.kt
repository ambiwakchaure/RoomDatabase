package com.example.roomdatabase.db.dao

import androidx.room.*
import com.example.roomdatabase.db.entities.Note

@Dao
interface NoteDao
{
    @Insert
    suspend fun addNote(note : Note)

    @Query("SELECT * FROM note ORDER BY id DESC")
    suspend fun getAllNotes() : List<Note>

    @Insert
    suspend fun addMultipleNotes(vararg note: Note)

    @Update
    suspend fun updateNote(note: Note)

    @Delete
    suspend fun deleteNote(note: Note)
}