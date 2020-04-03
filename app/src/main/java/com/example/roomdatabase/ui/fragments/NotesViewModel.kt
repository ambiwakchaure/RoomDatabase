package com.example.roomdatabase.ui.fragments

import android.view.View
import androidx.lifecycle.ViewModel
import com.example.roomdatabase.MyApplication
import com.example.roomdatabase.db.data.repositories.NoteRepository
import com.example.roomdatabase.db.entities.Note
import com.example.roomdatabase.ui.fragments.add_note.IAddNotesListner
import com.example.roomdatabase.ui.fragments.home.IHomeListner
import com.example.roomdatabase.viewUtils.Coroutines

class NotesViewModel(private val notesRepository: NoteRepository) : ViewModel()
{

    var title : String? = null
    var note : String? = null
    var note_id : Int? = null

    var saveUpdateFlag : String = "save"

    var iHomeListner : IHomeListner? = null
    var iAddNotesListner : IAddNotesListner? = null

    fun onAddNoteBtnClicked(v : View)
    {
        iHomeListner!!.onAddNotesButtonClicked(v)
    }
    fun onSubmitNoteBtnClicked(v : View)
    {
        if(title.isNullOrEmpty())
        {
            iAddNotesListner!!.showToast("Enter title")
            return
        }
        if(note.isNullOrEmpty())
        {
            iAddNotesListner!!.showToast("Enter Note")
            return
        }

        if(saveUpdateFlag.equals("save"))
        {
            Coroutines.main{
                var note = Note(title!!,note!!)
                notesRepository.saveNote(note)
                iAddNotesListner!!.showToast("Note saved")

            }
        }
        else
        {
            Coroutines.main{
                var note = Note(title!!,note!!)
                note.id = note_id!!
                notesRepository.updateNote(note)
                iAddNotesListner!!.showToast("Note updated")

            }
        }


    }
    fun deleteNote(note : Note)
    {
        Coroutines.main{
            notesRepository.deleteNote(note)
            iHomeListner!!.showToast("Note deleted")
            iHomeListner!!.refreshList()

        }
    }
    fun getAllNotes()
    {
        Coroutines.main {
            notesRepository.getAllNotes().let {
                iHomeListner!!.allNotesResult(it)
            }
        }
    }
}
