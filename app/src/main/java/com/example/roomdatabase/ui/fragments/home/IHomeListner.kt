package com.example.roomdatabase.ui.fragments.home

import android.view.View
import com.example.roomdatabase.db.entities.Note

interface IHomeListner
{
    fun onAddNotesButtonClicked(view : View)
    fun allNotesResult(notes : List<Note>)
    fun refreshList()
    fun showToast(message : String)
}