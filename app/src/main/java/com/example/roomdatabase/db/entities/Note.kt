package com.example.roomdatabase.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class Note (

    //if we want to use column name diffrent then u can use this
    //but now i dont want diffrent column name
    //@ColumnInfo(name = "note_title")
    val title : String,
    val note : String) : Serializable
{
    @PrimaryKey(autoGenerate = true)
    var id : Int = 0
}