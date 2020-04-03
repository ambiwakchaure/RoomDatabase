package com.example.roomdatabase.ui.fragments.home

import androidx.navigation.Navigation
import com.example.roomdatabase.R
import com.example.roomdatabase.databinding.NotesInfoItemBinding
import com.example.roomdatabase.db.entities.Note
import com.example.roomdatabase.ui.fragments.NotesViewModel
import com.xwray.groupie.databinding.BindableItem

class NotesInfoItem(private val note : Note,private val notesViewModel: NotesViewModel) : BindableItem<NotesInfoItemBinding>() {
    override fun getLayout(): Int = R.layout.notes_info_item

    override fun bind(viewBinding: NotesInfoItemBinding, position: Int) {
        viewBinding.note = note


        viewBinding.noteLi.setOnClickListener {
            val action = HomeFragmentDirections.actionAddNote()
            action.note = note
            Navigation.findNavController(it).navigate(action)
        }
        viewBinding.deleteBtn.setOnClickListener {
            notesViewModel.deleteNote(note)
        }
    }
}