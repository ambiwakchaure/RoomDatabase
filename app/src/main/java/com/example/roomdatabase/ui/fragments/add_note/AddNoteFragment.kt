package com.example.roomdatabase.ui.fragments.add_note

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.roomdatabase.MyApplication
import com.example.roomdatabase.R
import com.example.roomdatabase.databinding.FragmentAddNoteBinding
import com.example.roomdatabase.db.entities.Note
import com.example.roomdatabase.ui.fragments.NotesViewModel
import com.example.roomdatabase.viewUtils.toast

class AddNoteFragment : Fragment(),IAddNotesListner {


    private var note : Note? = null
    private lateinit var viewModel: NotesViewModel
    private lateinit var binnding: FragmentAddNoteBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        binnding = DataBindingUtil.inflate(inflater,R.layout.fragment_add_note, container, false)
        viewModel = ViewModelProviders.of(this,MyApplication.notesViewModelFactory).get(NotesViewModel::class.java)
        binnding.notesViewModel = viewModel
        viewModel.iAddNotesListner = this
        return binnding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        try
        {
            arguments?.let {
                note = AddNoteFragmentArgs.fromBundle(it).note
                viewModel.title = note!!.title
                viewModel.note = note!!.note
                viewModel.note_id = note!!.id
                viewModel.saveUpdateFlag = "update"
                binnding.notesViewModel = viewModel
            }
        }
        catch (e : KotlinNullPointerException)
        {
            viewModel.saveUpdateFlag = "save"
            binnding.notesViewModel = viewModel
        }
    }
    override fun showToast(message: String) {
        activity!!.toast(message)
    }



}
