package com.example.roomdatabase.db.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.roomdatabase.db.dao.NoteDao
import com.example.roomdatabase.db.entities.Note

@Database(
    entities = [Note::class],
    version = 1
)
abstract class NoteDatabase : RoomDatabase(){

    //get dao instance
    abstract fun getNoteDao() : NoteDao

    //build the room database
    companion object
    {
        //@Volatile : I want to available this instance immediate to all threads
        @Volatile private var instance : NoteDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK){
            instance ?: buildDatabase(context).also {
                instance = it
            }
        }
        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            NoteDatabase::class.java,
            "NoteDatabase"
        ).build()
    }
}